package Analizadores;
/* -------------------------------------------------------------------*/

class prog
{
  stmtlist statements;		// Source Code. Always keep a copy for gotos.
  env RAM = new env();		// Random Access Memory
  stmtlist PC;			// Program Counter Register
  public prog(stmtlist sl) { statements = sl; }

  private stmtlist findlabel(String l)  // returns subprogram at a label
    { stmtlist current = statements;    // needed for goto statements
      stmtlist answer = null;
      while (current != null)
       {
	 if ((current.car instanceof labelst) &&
	    (((labelst) current.car).label.equals(l)))
	      { answer = current; 
		current = null; }
	   else current = current.cdr;
       }
      return answer;
    }
  public void JUMP(String l)    //  Changes PC
    { PC = findlabel(l); }

  public void run()		// Run the program!
    { env RAM = new env();	// Clear Memory
      PC = statements;		// Set PC to start of source code
      while (PC != null)
	{ PC.car.execute(this, RAM);
	  PC = PC.cdr;		// Increment PC
        }
    }

public static stmtlist cons(stmt h, stmtlist t) 	// Syntactic Sugar
	{ return (new stmtlist(h,t)); }
}

abstract class expr    	// expressions class
{ 
  abstract expr eval(env e);  // every expr subclass has an eval method
}                             // relative to an environment


/* atomic expression classes: */
class boolexp extends expr{  
  public boolean b;          
  public boolexp(boolean x){ 
    super();
    b = x; 
  }
  expr eval(env e){ 
    return this; 
  }    // expression already atomic
  public String toString(){ 
    return ("" + b); 
  }  // only ATOMIC expr's can print
}

class strexp extends expr{   
  public String s;         
    public strexp(String x){ 
    super();
    s = x; 
    }
  expr eval(env e){ 
    return this; 
  }    // expression already atomic
  public String toString(){ 
    return s; 
  }
}

class intexp extends expr
{  public int n;          // public for convenience sake!
   public intexp(int x) { n = x; }
   expr eval(env e) { return this; }    // expression already atomic
   public String toString() { return ("" + n); }
}

/* non-atomic expression classes: */
class sumexp extends expr
{  expr s1, s2;
   public sumexp(expr x, expr y) { s1 = x; s2 = y; }
   expr eval(env e) 
    { if ((s1 instanceof intexp) && (s2 instanceof intexp))
		return new intexp(((intexp) s1).n + ((intexp) s2).n);
	else
	  	return (new sumexp(s1.eval(e), s2.eval(e))).eval(e);
    }
}

class multexp extends expr
{  expr s1, s2;
   public multexp(expr x, expr y) { s1 = x; s2 = y; }
   expr eval(env e) 
    { if ((s1 instanceof intexp) && (s2 instanceof intexp))
		return new intexp(((intexp) s1).n * ((intexp) s2).n);
	else
	  	return (new multexp(s1.eval(e), s2.eval(e))).eval(e);
    }
}

class eqexp extends expr     // equality expressions
{ expr s1, s2;
  public eqexp(expr x, expr y) { s1 = x; s2 = y; }
  expr eval(env e)
   { if ((s1 instanceof intexp) && (s2 instanceof intexp))
	return (new boolexp(((intexp) s1).n == ((intexp) s2).n ));
      else
	return (new eqexp(s1.eval(e), s2.eval(e))).eval(e);
   }
}

class andexp extends expr     // logical AND expressions
{ expr s1, s2;
  public andexp(expr x, expr y) { s1 = x; s2 = y; }
  expr eval(env e)
   { if ((s1 instanceof boolexp) && (s2 instanceof boolexp))
	return (new boolexp(((boolexp) s1).b && ((boolexp) s2).b ));
      else
	return (new andexp(s1.eval(e), s2.eval(e))).eval(e);
   }
}

class negexp extends expr	// logical NOT expressions
{ expr s;
  public negexp(expr x) { s = x; }
  expr eval(env e)
   { if (s instanceof boolexp) 
      return (new boolexp(!(((boolexp) s).b)));
        else return (new negexp(s.eval(e))).eval(e);
   }
}

// And now for the all-important Variable Expression:
class varexp extends expr
{ String v;
  public varexp(String x) { v = x; }
  expr eval(env e) { return e.lookup(v); }
}


///////////////////////// The environment class:
class bindings		  // a list of variable-value bindings
{ String varname;
  expr   varval;    // it is implicit here that varval is atomic
  bindings cdr;
  public bindings(String s, expr v, bindings rest)
	{ varname = s; varval = v; cdr = rest; }
}

class env
{ bindings start; 
  private static expr reclookup(String v, bindings b)
    { if (b == null) return (new intexp(0));  // default value = 0 in jBASIC
        else if (v.equals(b.varname)) return b.varval;
          else return reclookup(v,b.cdr);
    }
  public expr lookup(String v) { return reclookup(v,start); }

  private static bindings change(String v, expr ep, bindings bds)
    { if (bds == null) return (new bindings(v,ep,null));
        else
          if (v.equals(bds.varname))
		{ bds.varval = ep; 
	 	  return bds; }     // binding changed
	   else { bds.cdr = change(v,ep,bds.cdr);
		  return bds; }
    }
  public void bind(String v, expr e) { start = change(v,e,start); }
} // end of class env
//////////////////////////

/* ML-style term trees not possible because of looping pointers */



/* statments */
abstract class stmt  	// statements class
{
  abstract void execute(prog p, env e);  // every subclass can be executed
}					 // prog param needed for goto's

class labelst extends stmt   // labels are statements by themselves
{ String label;
  public labelst(String l) { label = l; }
  void execute(prog p, env e) {/* do nothing! */}
}

class printst extends stmt   // print statements
{ expr pexp;   // stuff to be printed
  public printst(expr e)  { pexp = e; }
  void execute(prog p, env e) { System.out.print(pexp.eval(e)); }
}

class assignst extends stmt  // assignment statements
{ String var;
  expr val;
  public assignst(String v, expr e) { var = v; val = e; }
  void execute(prog p, env e) { e.bind(var,val.eval(e)); }
}

class gotost extends stmt    // goto statement
{ String target;    // target label
  public gotost(String s) { target = s; }
  void execute(prog p, env e) { p.JUMP(target); }
}

class ifst extends stmt	    // if-the statement
{ expr condition;
  stmt st;
  public ifst(expr c, stmt s) { condition = c; st = s; }
  void execute(prog p, env e)
    { boolexp tvalue = (boolexp) condition.eval(e);
      if (tvalue.b) st.execute(p,e);
    }
}

class whilest extends stmt    // while loop statement
{ expr condition;
  stmtlist stl;
  public whilest(expr c, stmtlist s){
    super(); 
    condition = c;
    stl = s;
  }
  void execute(prog p, env e)
    {  stmtlist LPC;   //local program counter
       /* JAVA while used to implement JBASIC while  */
       while (((boolexp) condition.eval(e)).b) 
         for(LPC = stl; LPC != null; LPC = LPC.cdr)
	      LPC.car.execute(p,e);
    }
}  // end of whilest


/*  The program class */
class stmtlist
{ stmt car;
  stmtlist cdr;
  public stmtlist(stmt h, stmtlist t) { car = h; cdr = t; }
}


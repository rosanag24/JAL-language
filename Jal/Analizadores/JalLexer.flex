package Analizadores;
import java_cup.runtime.*;
%%
%class JalScanner
%function next_token
%type Symbol
%cup
%full
%line
%column
L=[a-zA-Z_]+
DIGIT=[0-9]+
STRING = \"([^\"])\"
whitespace=[ \t\r]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%
({whitespace})       {/*Ignore*/}
(",")                   {return new Symbol(sym.Comma, yycolumn, yyline, yytext());}
({STRING})              {return new Symbol(sym.Str, yycolumn, yyline, yytext());}
(alohomora)           {return new Symbol(sym.Alohomora, yychar, yyline, yytext());}
(aberto)              {return new Symbol(sym.Aberto, yycolumn, yyline, yytext());}
(focus)               {return new Symbol(sym.Focus, yycolumn, yyline, yytext());}
(geminio)             {return new Symbol(sym.Geminio, yycolumn, yyline, yytext());}
(giratiempo)          {return new Symbol(sym.Giratiempo, yycolumn, yyline, yytext());}
(finite)              {return new Symbol(sym.Finite, yycolumn, yyline, yytext());}
(examino)             {return new Symbol(sym.Examino, yycolumn, yyline, yytext());}
(aparecium)           {return new Symbol(sym.Aparecium, yycolumn, yyline, yytext());}
(reditus)             {return new Symbol(sym.Reditus, yycolumn, yyline, yytext());}
(avadakedavra)        {return new Symbol(sym.Avadakedavra, yycolumn, yyline, yytext());}
(saltus)              {return new Symbol(sym.Saltus, yycolumn, yyline, yytext());}
(if)                  {return new Symbol(sym.If, yycolumn, yyline, yytext());}
(else)                {return new Symbol(sym.Else, yycolumn, yyline, yytext());}
("in")                  {return new Symbol(sym.In, yycolumn, yyline, yytext());}
("{")                   {return new Symbol(sym.Opencurl, yycolumn, yyline, yytext());}
("}")                   {return new Symbol(sym.Closecurl, yycolumn, yyline, yytext());}
("[")                   {return new Symbol(sym.Openbracket, yycolumn, yyline, yytext());}
("]")                   {return new Symbol(sym.Closebracket, yycolumn, yyline, yytext());}
("(")                   {return new Symbol(sym.Openparent, yycolumn, yyline, yytext());}
(")")                   {return new Symbol(sym.Closeparent, yycolumn, yyline, yytext());}
(";")                   {return new Symbol(sym.Semicolon, yycolumn, yyline, yytext());}
(":")                   {return new Symbol(sym.Colon, yycolumn, yyline, yytext());}
(".")                   {return new Symbol(sym.Dot, yycolumn, yyline, yytext());}
("//"."\n")             {/*comments*/}
(True)                  {return new Symbol(sym.True, yycolumn, yyline, yytext());}
(False)                 {return new Symbol(sym.False, yycolumn, yyline, yytext());}
(char)                  {return new Symbol(sym.Typechar, yycolumn, yyline, yytext());}
(string)                {return new Symbol(sym.Typestring, yycolumn, yyline, yytext());}
(int)                   {return new Symbol(sym.Typeint, yycolumn, yyline, yytext());}
(float)                 {return new Symbol(sym.Typefloat, yycolumn, yyline, yytext());}
(double)                {return new Symbol(sym.Typedouble, yycolumn, yyline, yytext());}
(pointer)               {return new Symbol(sym.Typepointer, yycolumn, yyline, yytext());}
(bool)                  {return new Symbol(sym.Typeboolean, yycolumn, yyline, yytext());}
(ebublio)               {return new Symbol(sym.Typeebublio, yycolumn, yyline, yytext());}
("++")                  {return new Symbol(sym.Autoplus, yycolumn, yyline, yytext());}
("--")                  {return new Symbol(sym.Autominus, yycolumn, yyline, yytext());}
("+")                   {return new Symbol(sym.Plus, yycolumn, yyline, yytext());}
("-")                   {return new Symbol(sym.Minus, yycolumn, yyline, yytext());}
("*")                   {return new Symbol(sym.Mult, yycolumn, yyline, yytext());}
("^")                   {return new Symbol(sym.Pow, yycolumn, yyline, yytext());}
("/")                   {return new Symbol(sym.Div, yycolumn, yyline, yytext());}
("mod")                 {return new Symbol(sym.Mod, yycolumn, yyline, yytext());}
("&")                   {return new Symbol(sym.Andperseand, yycolumn, yyline, yytext());}
("not")                 {return new Symbol(sym.Not, yycolumn, yyline, yytext());}
("and")                 {return new Symbol(sym.And, yycolumn, yyline, yytext());}
("or")                  {return new Symbol(sym.Or, yycolumn, yyline, yytext());}
(">=")                  {return new Symbol(sym.Greaterequal, yycolumn, yyline, yytext());}
("<=")                  {return new Symbol(sym.Lessequal, yycolumn, yyline, yytext());}
(">")                   {return new Symbol(sym.Greater, yycolumn, yyline, yytext());}
("<")                   {return new Symbol(sym.Less, yycolumn, yyline, yytext());}
("==")                  {return new Symbol(sym.Equalto, yycolumn, yyline, yytext());}
("!=")                  {return new Symbol(sym.Differentto, yycolumn, yyline, yytext());}
("=")                   {return new Symbol(sym.Equal, yycolumn, yyline, yytext());}
(\n)                    {return new Symbol(sym.NewLine, yychar, yyline, yytext());}
({DIGIT}.{DIGIT})       {return new Symbol(sym.Float, yycolumn, yyline, yytext());}
({DIGIT})               {return new Symbol(sym.Int, yycolumn, yyline, yytext());}
({L}({L}|{DIGIT})*)     {return new Symbol(sym.Id, yycolumn, yyline, yytext());}
(\'.\')                 {return new Symbol(sym.Char, yycolumn, yyline, yytext());}
(.)                     {return new Symbol(sym.InvalidToken, yycolumn, yyline, yytext());}

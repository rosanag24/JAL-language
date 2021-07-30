package Analizadores;
import static code.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
STRING = \"([^\\\"]|\\.)*\"
DIGIT=[0-9]+
whitespace=[ \t\r]+
%{
    public String lexeme;
%}
%%
{whitespace}       {/*Ignore*/}
(",")             {lexeme=yytext(); return Comma;}
{STRING}        {lexeme=yytext(); return String;}
(alohomora)     {lexeme=yytext(); return Alohomora;}
(aberto)        {lexeme=yytext(); return Aberto;}
(focus)         {lexeme=yytext(); return Focus;}
(geminio)       {lexeme=yytext(); return Geminio;}
(giratiempo)    {lexeme=yytext(); return Giratiempo;}
(finite)        {lexeme=yytext(); return Finite;}
(examino)       {lexeme=yytext(); return Examino;}
(aparecium)     {lexeme=yytext(); return Aparecium;}
(reditus)       {lexeme=yytext(); return Reditus;}
(avadakedavra)  {lexeme=yytext(); return Avadakedavra;}
(saltus)        {lexeme=yytext(); return Saltus;}
(if)            {lexeme=yytext(); return If;}
(else)          {lexeme=yytext(); return Else;}
(in)           {lexeme=yytext(); return In;}
("{")      {lexeme=yytext(); return Opencurl;}
("}")      {lexeme=yytext(); return Closecurl;}
("[")      {lexeme=yytext(); return Openbracket;}
("]")      {lexeme=yytext(); return Closebracket;}
("(")      {lexeme=yytext(); return Openparent;}
(")")      {lexeme=yytext(); return Closeparent;}
(";")      {lexeme=yytext(); return Semicolon;}
(":")      {lexeme=yytext(); return Colon;}
(".")      {lexeme=yytext(); return Dot;}
("//"(.)*) {/*Ignore*/}
(True)  {lexeme=yytext(); return True;}
(False) {lexeme=yytext(); return False;}
(char)          {lexeme=yytext(); return Typechar;}
(string)        {lexeme=yytext(); return Typestring;}
(int)           {lexeme=yytext(); return Typeint;}
(float)         {lexeme=yytext(); return Typefloat;}
(double)        {lexeme=yytext(); return Typedouble;}
(pointer)       {lexeme=yytext(); return Typepointer;}
(bool)          {lexeme=yytext(); return Typeboolean;}
(ebublio)       {lexeme=yytext(); return Typeebublio;}
("++")    {lexeme=yytext(); return Autoplus;}
("--")    {lexeme=yytext(); return Autominus;}
("+")      {lexeme=yytext(); return Plus;}
("-")      {lexeme=yytext(); return Minus;}
("*")      {lexeme=yytext(); return Mult;}
("^")    {lexeme=yytext(); return Pow;}
("\/")      {lexeme=yytext(); return Div;}
(mod)   {lexeme=yytext(); return Mod;}
("&")      {lexeme=yytext(); return Andperseand;}
(not)   {lexeme=yytext(); return Not;}
(and)   {lexeme=yytext(); return And;}
(or)    {lexeme=yytext(); return Or;}
(">=")    {lexeme=yytext(); return Greaterequal;}
("<=")    {lexeme=yytext(); return Lessequal;}
(">")      {lexeme=yytext(); return Greater;}
("<")      {lexeme=yytext(); return Less;}
("==")      {lexeme=yytext(); return Equalto;}
("!=")                    {lexeme=yytext(); return Differentto;}
("=")                     {lexeme=yytext(); return Equal;}
(\n)                    {lexeme=yytext(); return NewLine;}
("\'"(.)"\'")           {lexeme=yytext(); return Char;}
{DIGIT}.{DIGIT}         {lexeme=yytext(); return Float;}
{DIGIT}                 {lexeme=yytext(); return Int;}
{L}({L}|{DIGIT})*       {lexeme=yytext(); return Id;}
(.)                     {lexeme=yytext(); return InvalidToken;}
grammar XQuery;

@header {
package project1.xquery.parser;
}

xq
  : Var                                                          #xqVar
  | StringLiteral                                                #xqStringConstant
  | ap                                                           #xqAp
  | '(' xq ')'                                                   #xqParenExpr
  | left=xq ',' right=xq                                         #xqConcat
  | xq slash=('/'|'//') rp                                       #xqSlash
  | '<' open=Identifier '>' '{' xq '}' '</' close=Identifier '>' #xqTagName
  | forClause letClause? whereClause? returnClause               #xqFLWR
  | letClause xq                                                 #xqLet
  | joinClause                                                   #xqJoin
  ;

joinClause
  : 'join' '(' xq1=xq ','
               xq2=xq ','
               idl1=IdentifierList ','
               idl2=IdentifierList
           ')'
  ;


forClause
  : 'for' Var 'in' xq (',' Var 'in' xq)*
  ;


letClause
  : 'let' Var ':=' xq (',' Var ':=' xq)*
  ;


whereClause
  : 'where' cond
  ;


returnClause
  : 'return' xq
  ;

cond
  : left=xq ('='|'eq')  right=xq                           #condValEqual
  | left=xq ('=='|'is') right=xq                           #condIdEqual
  | 'empty(' xq ')'                                        #condEmpty
  | 'some' Var 'in' xq (',' Var 'in' xq)* 'satisfies' cond #condSomeSatis
  | '(' cond ')'                                           #condParenExpr
  | left=cond 'and' right=cond                             #condAnd
  | left=cond 'or'  right=cond                             #condOr
  | 'not ' cond                                            #condNot
  ;


ap
  : 'doc(' fileName=StringLiteral ')' slash=('/'|'//') rp
  | 'document(' fileName=StringLiteral ')' slash=('/'|'//') rp
  ;

rp
  : '*'                               #rpWildcard
  | '.'                               #rpDot
  | '..'                              #rpDotDot
  | 'text()'                          #rpText
  | Identifier                        #rpTagName
  | '@' Identifier                    #rpAttr
  | '(' rp ')'                        #rpParenExpr
  | left=rp slash=('/'|'//') right=rp #rpSlash
  | rp '[' f ']'                      #rpFilter
  | left=rp ',' right=rp              #rpConcat
  ;


f
  : rp                                #fRp
  | left=rp ('eq'|'=')  right=rp      #fValEqual
  | left=rp ('is'|'==') right=rp      #fIdEqual
  | left=f  'and'       right=f       #fAnd
  | left=f  'or'        right=f       #fOr
  | '(' f ')'                         #fParen
  | 'not ' f                          #fNot
  ;

DOT      : '.' ;
UP       : '..';
WILDCARD : '*' ;
THEN     : ',' ;


LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
LANGLE : '<' | '</';
RANGLE : '>';

ASSIGN : ':=';
EQLS   : '=';
EQUAL  : '==';
SLASH  : '/';
SSLASH : '//';
IS     : 'is';
EQ     : 'eq';
AND    : 'and';
OR     : 'or';
NOT    : 'not';

StringLiteral
  :   '\"' StringCharacters? '\"'
  ;

fragment
StringCharacters :	StringCharacter+ ;

fragment
SPACE : ' ' | '\u000C';

fragment
StringCharacter
  : ~[\"\\@]
	 ;

Var
  : '$' Identifier
  ;

Identifier
  : Letter LetterOrDigit*
  ;

IdentifierList
  : '[' Identifier (',' Identifier)* ']'
  ;

fragment
Letter
  : [a-zA-Z_]
  ;

fragment
LetterOrDigit
  : [a-zA-Z0-9_-]
  ;

WS : [ \t\r\n]+ -> skip ;
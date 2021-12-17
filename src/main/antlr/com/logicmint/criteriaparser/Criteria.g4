grammar Criteria;                // Define a grammar called Hello

@header {
package com.logicmint.criteriaparser;
}

expression :
   condition |
   expression AND expression |
   expression OR expression;

condition :
   COLUMN_ID_TERM comparison_operator term |
   COLUMN_ID_TERM comparison_operator COLUMN_ID_TERM |
   COLUMN_ID_TERM NOT? LIKE STRING_TERM |
   COLUMN_ID_TERM NOT? IN termlist |
   NOT expression |
   '(' expression ')';

comparison_operator :
   '<=' | '>=' | '=' | '<' | '>' | '!=' ;

termlist: '(' term (','term)* ')' ;

term : INT_TERM | STRING_TERM;

// Lexical Tokens
AND: 'AND';
OR: 'OR';
LIKE: 'LIKE';
IN: 'IN';
NOT: 'NOT';
INT_TERM: [-]?[0-9]+ ;
COLUMN_ID_TERM: [a-zA-Z0-9_]+ ;
STRING_TERM: '\'' .*? '\'' ;
WS: [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines, \r (Windows)

grammar Criteria;                // Define a grammar called Hello

expression :
   condition |
   expression 'AND' expression |
   expression 'OR' expression;

condition :
   operand 'LIKE' operand;

operand : INTEGER | COLUMN_IDENTIFIER | STRING;

// Lexical Tokens
INTEGER: [-]?[0-9]+ ;
COLUMN_IDENTIFIER: [a-zA-Z0-9_]+ ;
STRING: '\'' .*? '\'' ;
WS: [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines, \r (Windows)

grammar Criteria;                // Define a grammar called Hello

@header {
package com.logicmint.criteriaparser;
}

expression :
     L_PARENTHESIS expression R_PARENTHESIS             # ParenthesisedExpression
   | condition                      # ConditionExpression
   | expression AND expression      # AndExpression
   | expression OR expression       # OrExpression ;


condition :
     columnId comparison term             # ColumnToTermCondition
   | columnId comparison columnId         # ColumnToColumnCondition
   | columnId NOT? LIKE string_term       # LikeCondition
   | columnId NOT? IN termlist            # InCondition
   | columnId IS NOT? null_term # NulLCondition ;


comparison :
     LESS_THAN_OR_EQUAL    # LessThanOrEquals
   | GRTR_THAN_OR_EQUAL    # GreaterThanOrEquals
   | EQUAL_TO     # Equals
   | LESS_THAN     # LessThan
   | GREATER_THAN     # GreaterThan
   | NOT_EQUAL_TO    # NotEquals ;


termlist: L_PARENTHESIS term (','term)* R_PARENTHESIS ;

term : int_term | string_term ;

// Define these as rules rather than refer to the equivalent lexical tokens directly so that they are represented
// as type specific callbacks in the ParseTreeListener and are therefore easy to attach custom logic to
columnId : COLUMN_ID_TERM ;
int_term : INT_TERM ;
string_term : STRING_TERM;
null_term : NULL_TERM;

// Lexical Tokens
L_PARENTHESIS: '(';
R_PARENTHESIS : ')' ;
NOT_EQUAL_TO : '!=' ;
GREATER_THAN : '>' ;
LESS_THAN : '<' ;
EQUAL_TO : '=' ;
GRTR_THAN_OR_EQUAL : '>=' ;
LESS_THAN_OR_EQUAL : '<=' ;
IS : 'IS' ;
AND: 'AND';
OR: 'OR';
LIKE: 'LIKE';
IN: 'IN';
NOT: 'NOT';
NULL_TERM: 'NULL';
INT_TERM: [-]?[0-9]+ ;
COLUMN_ID_TERM: [a-zA-Z0-9_]+ ;
STRING_TERM: '\'' .*? '\'' ;
WS: [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines, \r (Windows)

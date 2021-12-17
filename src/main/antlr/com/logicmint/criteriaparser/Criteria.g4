grammar Criteria;                // Define a grammar called Hello

@header {
package com.logicmint.criteriaparser;
}

expression :
     '(' expression ')'             # ParenthesisedExpression
   | condition                      # ConditionExpression
   | expression AND expression      # AndExpression
   | expression OR expression       # OrExpression ;

condition :
     columnId comparison term             # ColumnToTermCondition
   | columnId comparison columnId         # ColumnToColumnCondition
   | columnId NOT? LIKE STRING_TERM       # LikeCondition
   | columnId NOT? IN termlist            # InCondition
   | columnId 'IS' NOT? NULL_TERM         # NulLCondition ;

comparison :
     '<='    # LessThanOrEquals
   | '>='    # GreaterThanOrEquals
   | '='     # Equals
   | '<'     # LessThan
   | '>'     # GreaterThan
   | '!='    # NotEquals ;

termlist: '(' term (','term)* ')' ;

columnId : COLUMN_ID_TERM ;

term : INT_TERM | STRING_TERM ;

// Lexical Tokens
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

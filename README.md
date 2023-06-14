# criteriaparser

### ANTLR 4 generated parser for CriteriB language

## CriteriB

CriteriB is a simple language consisting of predicate expressions similar to those you might 
find in the WHERE clause of a SQL 92 query. This is most useful for specifying simple query criteria
which apply to columns in a single flat/denormalized table  

The expressions supported by CriteriB are more limited than SQL 92 predicates. A valid CriteriB expression will
parse as valid SQL

## Why CriteriB?

* A language has to have a name
* Criteri**a** was already taken


## Limitations with respect to SQL predicates

* Does not support addition, subtraction, multiplication or division
  i.e. myNumericValue = 1 + 2  or col1 = col2 / col3 does not compile
 
* First term in comparison must be a column reference
  i.e.  myCol = 'Nick' will compile but 'Nick' = myCol will not compile

* Does not support <> operator, only !=

* Only simple column references, no table names or aliases
  e.g. myTable.myCol or myTableAlias.myCol does not work, only myCol
 
* No function calls, bind parameters, case clause








# SqlManager

Sql Manager
Execute all kinds of queries using a specific database.
![Image](https://github.com/BrunoBeltreGuzman/SqlManager/blob/master/Screenshot1.png)
It is a system that supports any type of Sql (Structured Query Language) query using a specific database.

Functioning:
It contains two main types of methods or functions for executing querys.

Methods or Functions:
Select:
public void select (String query) {…}
It receives a String type parametron which contains the query that you want to execute, this method is 
the one used to execute select type query, that is, they return results which are displayed in a Jtable.
Update:
public void update (String query) {…}
This also receives a parameter of type String which contains the query that you want to execute, this 
method is used to execute query of type update, that is, they do not return results, which is used to 
execute query such as create update, delete, insert, drop and more.

Some of the queries that can be executed from this Sql Mananger are:
Insert, select, update, delete data in a table.
Create, update, delete tables.
Create, update, delete columns.
And more.

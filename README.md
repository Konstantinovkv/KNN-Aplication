Initializing a Cassandra Table  
To initialize a Cassandra table, use the following CREATE TABLE statement:
 
Copy cql code:  

CREATE KEYSPACE IF NOT EXISTS knn
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};  

USE knn;

CREATE TABLE IF NOT EXISTS data_points (
id UUID PRIMARY KEY,
feature_1 double,
feature_2 double,
label text
);  

This statement will create a table named data_points with four columns:

id: A UUID column that serves as the primary key.  
feature_1 and feature_2: Two columns of type double.  
label: A column of type text.  
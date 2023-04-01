K-Nearest Neighbors Classifier with Cassandra  

This project is a simple implementation of the k-nearest neighbors (KNN) classifier using data stored in a Cassandra NoSQL database. The classifier finds objects in the database based on user-defined criteria for proximity and distance functions.

Prerequisites:

Before getting started, you need to have the following software installed on your machine:

JDK 8 or later (https://adoptopenjdk.net/)
Apache Cassandra (https://cassandra.apache.org/download/) or a running Cassandra instance in Docker (https://hub.docker.com/_/cassandra)
Python 3.x (https://www.python.org/downloads/)
Apache Maven (https://maven.apache.org/download.cgi)
Project Structure
Organize your project files and directories as follows:  

knn-cassandra/
src/
main/
java/
knn/
Main.java
KNearestNeighbors.java
DistanceFunction.java
EuclideanDistance.java
ManhattanDistance.java
DataPoint.java
DataPointDistance.java
pom.xml
insert_data.py

Setting up the Environment:  

Install and start Apache Cassandra. Follow the instructions in the official documentation or use Docker to start a Cassandra container:  

docker run --name cassandra -p 9042:9042 -d cassandra

Install the Python cassandra-driver package: 

pip install cassandra-driver

Create the Cassandra keyspace and table. Connect to your Cassandra instance using the cqlsh tool and execute the following commands:  

CREATE KEYSPACE IF NOT EXISTS knn WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE knn;

CREATE TABLE IF NOT EXISTS data_points (
id UUID PRIMARY KEY,
feature_1 double,
feature_2 double,
label text
);


Insert test data into the data_points table using the provided Python script insert_data.py. Update the script with the IP address of your Cassandra instance:  

cluster = Cluster(['your_cassandra_instance_ip'], auth_provider=auth_provider)

Run the Python script:  

python insert_data.py

Building and Running the Project
Open a command prompt, navigate to the knn-cassandra project directory, and compile the Java source files using Maven:  
mvn compile
Run the Java application:
mvn exec:java -Dexec.mainClass="knn.Main"

The application should now run, classify a new data point, and display the classified label in the command prompt.  

Customizing the Classifier
You can customize the k-nearest neighbors classifier by adjusting the value of k and choosing a different distance function. Edit the Main.java file to make these changes:  

// Initialize the k-nearest neighbors classifier  
int k = 3; // Change the value of k based on your requirements
KNearestNeighbors knn = new KNearestNeighbors(k, dataPoints);

// Choose a distance function (Euclidean or Manhattan)  
DistanceFunction distanceFunction = new EuclideanDistance();

Recompile and run the project after making any changes to see the updated results.

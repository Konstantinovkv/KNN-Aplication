<h2> K-Nearest Neighbors Classifier with Cassandra </h2>

This project is a simple implementation of the k-nearest neighbors (KNN) classifier using data stored in a Cassandra NoSQL database. The classifier finds objects in the database based on user-defined criteria for proximity and distance functions.

<h3>Prerequisites:</h3>

Before getting started, you need to have the following software installed on your machine:

* JDK 8 or later (https://adoptopenjdk.net/)
* Apache Cassandra (https://cassandra.apache.org/download/) or a running Cassandra instance in Docker (https://hub.docker.com/_/cassandra)
* Python 3.x (https://www.python.org/downloads/)
* Apache Maven (https://maven.apache.org/download.cgi)  

<h3>Project Structure: </h3> 
Organize your project files and directories as follows:  

    KNN_Application/  
                    src/  
                        main/  
                            java/  
                                knn/  
                                    dbUtils/
                                        CassandraConnector.java
                                    distanceFunctions/
                                        DistanceFunction.java
                                        EuclideanDistance.java
                                        ManhattanDistance.java
                                    Main.java  
                                    KNearestNeighbors.java    
                                    DataPoint.java  
                                    DataPointDistance.java  
                    pom.xml  
                    insert_data.py  

<h3>Setting up the Environment:  </h3>

Install and start Apache Cassandra. Follow the instructions in the official documentation or use Docker to start a Cassandra container:  

docker run --name cassandra -p 9042:9042 -d cassandra

Install the Python cassandra-driver package: 

pip install cassandra-driver

```sql
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
```

```python
cluster = Cluster(['your_cassandra_instance_ip'], auth_provider=auth_provider)
```

Run the Python script:  

python dbFiller.py

<h3>Building and Running the Project:  </h3>

Run the main method in the Main class.

The application should now run, classify a new data point, and display the classified label in the command prompt.  

<h3>Customizing the Classifier:  </h3>
You can customize the k-nearest neighbors classifier by adjusting the value of k and choosing a different distance function. Edit the Main.java file to make these changes:  

// Initialize the k-nearest neighbors classifier  
``` java
int k = 3; // Change the value of k based on your requirements
KNearestNeighbors knn = new KNearestNeighbors(k, dataPoints);
```

// Choose a distance function (Euclidean or Manhattan)  
``` java
DistanceFunction distanceFunction = new EuclideanDistance();
```

Recompile and run the project after making any changes to see the updated results.

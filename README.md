<h2> K-Nearest Neighbors Classifier with Cassandra </h2>

This project is a simple implementation of the k-nearest neighbors (KNN) classifier using data stored in a Cassandra NoSQL database. 
The classifier finds objects in the database based on user-defined criteria for proximity, distance functions, and their associated 
coefficients. By allowing users to configure the distance function and coefficients, the classifier can be tailored to emphasize 
the importance of certain features in determining the nearest neighbors, thus providing a more customized classification experience.

<h3>Prerequisites:</h3>

* JDK 8 or later (https://adoptopenjdk.net/)
* Apache Cassandra (https://cassandra.apache.org/download/) or a running Cassandra instance in Docker (https://hub.docker.com/_/cassandra)
* Python 3.x (https://www.python.org/downloads/)
* Apache Maven (https://maven.apache.org/download.cgi)

<h3>Project Structure: </h3>   

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

```
docker pull cassandra:latest  
docker run -p 9042:9042 --name my-cassandra -d cassandra:latest  
winpty docker exec -it my-cassandra cqlsh
```

Install the Python cassandra-driver package: 

```
pip install cassandra-driver
```


Create the Cassandra keyspace and table. Connect to your Cassandra instance using the cqlsh tool and execute the following commands:  

```sql
CREATE KEYSPACE IF NOT EXISTS knn WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE knn;

CREATE TABLE IF NOT EXISTS data_points (
id UUID PRIMARY KEY,
feature_1 double,
feature_2 double,
label text
);
```

Insert test data into the data_points table using the provided Python script dbFiller.py. 
Update the script with the IP address of your Cassandra instance:  


```python
cluster = Cluster(['your_cassandra_instance_ip'], auth_provider=auth_provider)
```

Run the Python script:  

```
python dbFiller.py
```

<h3>Building and Running the Project:  </h3>

The app will ask you to provide the desired distance function (Euclidean or Manhattan), the coefficients for each feature, and input values 
for feature_1 and feature_2, in order to classify a new data point based on the k-nearest neighbors algorithm using the data stored in a 
Cassandra NoSQL database.

The application should now run, classify a new data point, and display the classified label in the command prompt.  

<h3>Customizing the Classifier: </h3>
You can customize the k-nearest neighbors classifier by adjusting the value of k and choosing a different distance function. Edit the 
`Main.java` file to make these changes:

In the context of the k-nearest neighbors (KNN) algorithm, int k = 3; sets the number of nearest neighbors to consider when classifying 
a new data point. The value of k determines how many of the closest data points in the feature space will be used to determine the class 
or label of the new data point. In this case, the algorithm will consider the 3 closest data points to the new data point for classification.
``` java
int k = 3; // Change the value of k based on your requirements
KNearestNeighbors knn = new KNearestNeighbors(k, dataPoints);
```

<h3>Example Input and Output</h3>
```
Enter feature_1 value:
3.56
Enter feature_2 value:
7.85
Enter the distance function ("e" for euclidean or "m" for manhattan):
e
Enter the coefficients for each feature (comma-separated):
5,8
The classified label for the new data point is: A
```

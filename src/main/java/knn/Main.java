package knn;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import knn.dbUtils.CassandraConnector;
import knn.distansFunctions.DistanceFunction;
import knn.distansFunctions.EuclideanDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        CqlSession session = CassandraConnector.connect();

        // Load data from Cassandra
        List<DataPoint> dataPoints = loadDataPoints(session);

        // Initialize the k-nearest neighbors classifier
        int k = 3; // You can change the value of k based on your requirements
        KNearestNeighbors knn = new KNearestNeighbors(k, dataPoints);

        // Create a new data point to classify
        DataPoint newPoint = new DataPoint(2.0, 3.0, null);

        // Choose a distance function (Euclidean or Manhattan)
        DistanceFunction distanceFunction = new EuclideanDistance();

        // Classify the new data point
        String label = knn.classify(newPoint, distanceFunction);

        System.out.println("The classified label for the new data point is: " + label);

        session.close();
    }

    private static List<DataPoint> loadDataPoints(CqlSession session) {
        List<DataPoint> dataPoints = new ArrayList<>();

        ResultSet resultSet = session.execute("SELECT * FROM my_keyspace.data_points;");
        for (Row row : resultSet) {
            UUID id = row.getUuid("id");
            double feature1 = row.getDouble("feature_1");
            double feature2 = row.getDouble("feature_2");
            String label = row.getString("label");

            DataPoint dataPoint = new DataPoint(feature1, feature2, label);
            dataPoints.add(dataPoint);
        }

        return dataPoints;
    }
}

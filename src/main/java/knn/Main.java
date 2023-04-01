package knn;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import knn.dbUtils.CassandraConnector;
import knn.distanceFunctions.DistanceFunction;
import knn.distanceFunctions.EuclideanDistance;
import knn.distanceFunctions.ManhattanDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        CqlSession session = CassandraConnector.connect();

        // Load data from Cassandra
        List<DataPoint> dataPoints = loadDataPoints(session);

        // Initialize the k-nearest neighbors classifier
        int k = 3; // You can change the value of k based on your requirements
        KNearestNeighbors knn = new KNearestNeighbors(k, dataPoints);

        Scanner scanner = new Scanner(System.in);

        // Input feature values for the new data point
        System.out.println("Enter feature_1 value:");
        double feature1 = scanner.nextDouble();

        System.out.println("Enter feature_2 value:");
        double feature2 = scanner.nextDouble();

        // Create a new data point to classify
        DataPoint newPoint = new DataPoint(feature1, feature2, null);

        // Choose a distance function (Euclidean or Manhattan)
        System.out.println("Enter the distance function (\"e\" for euclidean or \"m\" for manhattan):");
        scanner.nextLine(); // Consume the newline left-over
        String distanceFunctionType = scanner.nextLine().toLowerCase().trim();
        DistanceFunction distanceFunction;

        if (distanceFunctionType.equals("e")) {
            distanceFunction = new EuclideanDistance();
        } else if (distanceFunctionType.equals("m")) {
            distanceFunction = new ManhattanDistance();
        } else {
            System.out.println("Invalid distance function. Exiting.");
            scanner.close();
            session.close();
            return;
        }

        // Set coefficients for the distance function
        System.out.println("Enter the coefficients for each feature (comma-separated):");
        String[] inputCoefficients = scanner.nextLine().split(",");
        double[] coefficients = Arrays.stream(inputCoefficients)
                .mapToDouble(Double::parseDouble)
                .toArray();
        distanceFunction.setCoefficients(coefficients);

        scanner.close();

        // Classify the new data point
        String label = knn.classify(newPoint, distanceFunction);

        System.out.println("The classified label for the new data point is: " + label);

        session.close();
    }

    private static List<DataPoint> loadDataPoints(CqlSession session) {
        List<DataPoint> dataPoints = new ArrayList<>();

        ResultSet resultSet = session.execute("SELECT * FROM knn.data_points;");
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

package knn;

import knn.distansFunctions.DistanceFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class KNearestNeighbors {
    private final int k;
    private List<DataPoint> dataPoints;

    public KNearestNeighbors(int k, List<DataPoint> dataPoints) {
        this.k = k;
        this.dataPoints = dataPoints;
    }

    public String classify(DataPoint newPoint, DistanceFunction distanceFunction) {
        PriorityQueue<DataPointDistance> kNearestNeighbors = new PriorityQueue<>(k);

        for (DataPoint dataPoint : dataPoints) {
            double distance = distanceFunction.calculate(newPoint, dataPoint);

            if (kNearestNeighbors.size() < k) {
                kNearestNeighbors.add(new DataPointDistance(dataPoint, distance));
            } else if (distance < kNearestNeighbors.peek().getDistance()) {
                kNearestNeighbors.poll();
                kNearestNeighbors.add(new DataPointDistance(dataPoint, distance));
            }
        }

        return findMajorityLabel(kNearestNeighbors);
    }

    private String findMajorityLabel(PriorityQueue<DataPointDistance> kNearestNeighbors) {
        Map<String, Integer> labelCount = new HashMap<>();

        while (!kNearestNeighbors.isEmpty()) {
            DataPointDistance neighbor = kNearestNeighbors.poll();
            String label = neighbor.getDataPoint().getLabel();
            labelCount.put(label, labelCount.getOrDefault(label, 0) + 1);
        }

        return labelCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}

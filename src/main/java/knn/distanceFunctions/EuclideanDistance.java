package knn.distanceFunctions;

import knn.DataPoint;

public class EuclideanDistance implements DistanceFunction {

    @Override
    public double calculate(DataPoint point1, DataPoint point2) {
        double sum = 0.0;

        sum += Math.pow(point1.getFeature1() - point2.getFeature1(), 2);
        sum += Math.pow(point1.getFeature2() - point2.getFeature2(), 2);

        return Math.sqrt(sum);
    }
}

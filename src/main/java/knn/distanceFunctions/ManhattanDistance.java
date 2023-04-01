package knn.distanceFunctions;

import knn.DataPoint;

public class ManhattanDistance implements DistanceFunction {

    @Override
    public double calculate(DataPoint point1, DataPoint point2) {
        double sum = 0.0;

        sum += Math.abs(point1.getFeature1() - point2.getFeature1());
        sum += Math.abs(point1.getFeature2() - point2.getFeature2());

        return sum;
    }
}

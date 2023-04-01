package knn.distanceFunctions;

import knn.DataPoint;
import lombok.Setter;

@Setter
public class EuclideanDistance implements DistanceFunction {

    private double[] coefficients;

    @Override
    public double calculate(DataPoint p1, DataPoint p2) {
        double sum = 0;
        for (int i = 0; i < p1.getFeatures().length; i++) {
            double diff = (p1.getFeatures()[i] - p2.getFeatures()[i]) * coefficients[i];
            sum += diff * diff;
        }
        return Math.sqrt(sum);
    }
}

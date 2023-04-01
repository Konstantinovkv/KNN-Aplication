package knn.distansFunctions;

import knn.DataPoint;

public interface DistanceFunction {
    double calculate(DataPoint point1, DataPoint point2);
}

package knn;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataPoint {

    private double[] features;
    private String label;

    public DataPoint(double feature1, double feature2, String label) {
        this.features = new double[] {feature1, feature2};
        this.label = label;
    }

    public double[] getFeatures() {
        return features;
    }
}

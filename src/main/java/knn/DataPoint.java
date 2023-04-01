package knn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DataPoint {

    private double feature1;
    private double feature2;
    private String label;

}

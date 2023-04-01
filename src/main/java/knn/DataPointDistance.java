package knn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DataPointDistance implements Comparable<DataPointDistance> {

    private DataPoint dataPoint;
    private double distance;

    @Override
    public int compareTo(DataPointDistance other) {
        return Double.compare(this.distance, other.distance);
    }
}

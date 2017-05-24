package pl.edu.agh.map;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by hector on 23/04/2017.
 */

@Getter
@EqualsAndHashCode
public class GeoLocation {

    private final double latitude;

    private final double longitude;

    public GeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

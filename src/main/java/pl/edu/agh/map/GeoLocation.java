package pl.edu.agh.map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by hector on 23/04/2017.
 */

@Getter
@EqualsAndHashCode
@ToString
public class GeoLocation {

    private final double lat;

    private final double lng;

    public GeoLocation(double latitude, double longitude) {
        this.lat = latitude;
        this.lng = longitude;
    }
}

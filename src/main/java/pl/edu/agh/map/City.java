package pl.edu.agh.map;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by hector on 23/04/2017.
 */

@Getter
@EqualsAndHashCode
public class City {

    private final String cityName;

    private final GeoLocation location;

    public City(String cityName, GeoLocation location) {
        this.cityName = cityName;
        this.location = location;
    }
}

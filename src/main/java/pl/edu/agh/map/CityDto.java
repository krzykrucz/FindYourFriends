package pl.edu.agh.map;

import lombok.Getter;
import lombok.ToString;
import pl.edu.agh.social.FriendDto;

import java.util.List;

/**
 * Created by hector on 02/05/2017.
 */

@Getter
@ToString
public class CityDto {

    private final String name;

    private final GeoLocation geoLocation;

    private final List<FriendDto> friends;

    public CityDto(String name, GeoLocation geoLocation, List<FriendDto> friends) {
        this.name = name;
        this.geoLocation = geoLocation;
        this.friends = friends;
    }
}

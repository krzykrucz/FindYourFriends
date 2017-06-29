package pl.edu.agh.social;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import pl.edu.agh.map.GeoLocation;

/**
 * Created by hector on 28/06/2017.
 */

@Getter
@Builder
@ToString
public class PostDto {
    private String author;
    private String content;
    private GeoLocation geoLocation;
}

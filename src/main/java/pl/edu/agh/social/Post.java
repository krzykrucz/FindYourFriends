package pl.edu.agh.social;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import pl.edu.agh.map.GeoLocation;

import java.util.Optional;

/**
 * Created by hector on 24/06/2017.
 */

@Getter
@Builder
@ToString
public class Post {

    private final String author;

    private final GeoLocation geoLocation;

    private final String content;

    public Optional<GeoLocation> getGeoLocation() {
        return Optional.ofNullable(geoLocation);
    }

    public Optional<PostDto> toDto() {
        if (this.geoLocation == null) {
            return Optional.empty();
        }
        return Optional.of(PostDto.builder()
                .author(this.author)
                .content(this.content)
                .geoLocation(new GeoLocation(this.geoLocation.getLat(), this.geoLocation.getLng()))
                .build());
    }

}

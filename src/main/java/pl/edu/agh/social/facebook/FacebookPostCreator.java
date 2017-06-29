package pl.edu.agh.social.facebook;

import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Component;
import pl.edu.agh.map.GeoLocation;
import pl.edu.agh.social.PostCreator;

/**
 * Created by hector on 29/06/2017.
 */

@Component
public class FacebookPostCreator implements PostCreator<Post> {
    @Override
    public pl.edu.agh.social.Post create(Post from) {
        final Page place = from.getPlace();
        return pl.edu.agh.social.Post.builder()
                .author(from.getName())
                .content(from.getMessage())
                .geoLocation(place == null || place.getLocation() == null ? null :
                        new GeoLocation(
                                place.getLocation().getLatitude(),
                                place.getLocation().getLongitude()))
                .build();
    }
}

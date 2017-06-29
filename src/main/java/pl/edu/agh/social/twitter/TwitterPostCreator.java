package pl.edu.agh.social.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Place;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.Coordinates;
import org.springframework.stereotype.Component;
import pl.edu.agh.map.GeoLocation;
import pl.edu.agh.map.GeoLocationResolver;
import pl.edu.agh.social.Post;
import pl.edu.agh.social.PostCreator;

/**
 * Created by hector on 28/06/2017.
 */

@Component
public class TwitterPostCreator implements PostCreator<Tweet> {

    private final GeoLocationResolver geoLocationResolver;

    @Autowired
    public TwitterPostCreator(GeoLocationResolver geoLocationResolver) {
        this.geoLocationResolver = geoLocationResolver;
    }

    @Override
    public Post create(Tweet tweet) {
        return Post.builder()
                .content(tweet.getText())
                .author(tweet.getUser().getName())
                .geoLocation(resolveGeoLocation(tweet))
                .build();
    }

    private GeoLocation resolveGeoLocation(Tweet tweet) {
        final Coordinates tweetCoordinates = tweet.getCoordinates();
        final Place tweetPlace = tweet.getPlace();
        GeoLocation geoLocation;
        if (tweetCoordinates != null) {
            geoLocation = new GeoLocation(
                    tweetCoordinates.getLatitude(),
                    tweetCoordinates.getLongitude()
            );
        } else if (tweetPlace != null) {
            geoLocation = geoLocationResolver.resolveLocation(tweetPlace.getFullName())
                    .orElse(null);
        } else {
            geoLocation = null;
        }
        return geoLocation;
    }
}

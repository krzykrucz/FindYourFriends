package pl.edu.agh.social.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Component;
import pl.edu.agh.map.City;
import pl.edu.agh.map.GeoLocation;
import pl.edu.agh.map.GeoLocationResolver;
import pl.edu.agh.social.Friend;
import pl.edu.agh.social.FriendCreator;

import java.util.Optional;

@Component
public class TwitterFriendCreator implements FriendCreator<TwitterProfile> {

    private final GeoLocationResolver geoLocationResolver;

    @Autowired
    public TwitterFriendCreator(GeoLocationResolver geoLocationResolver) {
        this.geoLocationResolver = geoLocationResolver;
    }

    @Override
    public Friend create(TwitterProfile friend) {
        String friendLocation = friend.getLocation();
        if (friendLocation == null) {
            return new Friend(friend.getName(), friend.getProfileUrl(), null);
        }
        final Optional<GeoLocation> location = this.geoLocationResolver.resolveLocation(friendLocation);
        return new Friend(
                friend.getName(), friend.getProfileUrl(),
                location.map(geoLocation -> new City(friendLocation, geoLocation))
                        .orElse(null)
        );
    }
}

package pl.edu.agh.friend.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;
import pl.edu.agh.friend.Friend;
import pl.edu.agh.map.City;
import pl.edu.agh.map.GeoLocation;
import pl.edu.agh.map.GeoLocationResolver;

import java.util.Optional;

/**
 * Created by hector on 23/04/2017.
 */

@Component
public class FacebookFriendCreator implements FriendCreator<User> {

    private final GeoLocationResolver geoLocationResolver;

    @Autowired
    public FacebookFriendCreator(GeoLocationResolver geoLocationResolver) {
        this.geoLocationResolver = geoLocationResolver;
    }

    @Override
    public Friend create(User friend) {
        final Reference locationReference = friend.getLocation();
        if (locationReference == null) {
            return new Friend(friend.getName(), friend.getLink(), null);
        }
        final String friendLocation = locationReference.getName();
        final Optional<GeoLocation> location = this.geoLocationResolver.resolveLocation(friendLocation);
        return new Friend(
                friend.getName(),
                friend.getLink(),
                location.map(geoLocation -> new City(friendLocation, geoLocation))
                        .orElse(null)
        );
    }
}

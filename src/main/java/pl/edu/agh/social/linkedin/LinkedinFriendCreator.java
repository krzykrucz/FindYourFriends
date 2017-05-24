package pl.edu.agh.social.linkedin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Location;
import org.springframework.stereotype.Component;
import pl.edu.agh.map.City;
import pl.edu.agh.map.GeoLocation;
import pl.edu.agh.map.GeoLocationResolver;
import pl.edu.agh.social.Friend;
import pl.edu.agh.social.FriendCreator;

import java.util.Optional;

@Component
public class LinkedinFriendCreator implements FriendCreator<LinkedInProfileFull> {

    private final GeoLocationResolver geoLocationResolver;

    @Autowired
    public LinkedinFriendCreator(GeoLocationResolver geoLocationResolver) {
        this.geoLocationResolver = geoLocationResolver;
    }

    @Override
    public Friend create(LinkedInProfileFull friend) {
        final Location profileLocation = friend.getLocation();
        if (profileLocation == null) {
            return new Friend(friend.getFirstName() + " " + friend.getLastName(), friend.getPublicProfileUrl(), null);
        }
        final String friendLocation = profileLocation.getName();
        final Optional<GeoLocation> location = this.geoLocationResolver.resolveLocation(friendLocation);
        return new Friend(
                friend.getFirstName() + " " + friend.getLastName(),
                friend.getPublicProfileUrl(),
                location.map(geoLocation -> new City(friendLocation, geoLocation))
                        .orElse(null)
        );
    }
}

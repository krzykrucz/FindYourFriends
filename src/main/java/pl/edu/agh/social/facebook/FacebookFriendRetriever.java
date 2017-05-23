package pl.edu.agh.social.facebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import pl.edu.agh.social.Friend;
import pl.edu.agh.social.FriendRetriever;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hector on 23/04/2017.
 */

@Component
class FacebookFriendRetriever implements FriendRetriever {

    private final Facebook facebook;

    private final ConnectionRepository connectionRepository;

    private final FacebookFriendCreator facebookFriendCreator;

    @Autowired
    public FacebookFriendRetriever(Facebook facebook, ConnectionRepository connectionRepository, FacebookFriendCreator facebookFriendCreator) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        this.facebookFriendCreator = facebookFriendCreator;
    }

    @Override
    public List<Friend> getFriends() {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return Collections.emptyList();
        }
        return this.facebook.friendOperations().getFriendProfiles()
                .stream()
                .map(facebookFriendCreator::create)
                .collect(Collectors.toList());
    }
}

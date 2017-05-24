package pl.edu.agh.social.linkedin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import pl.edu.agh.social.Friend;
import pl.edu.agh.social.FriendRetriever;

import java.util.Collections;
import java.util.List;

//@Component
public class LinkedinFriendRetriever implements FriendRetriever {

    private final LinkedIn linkedIn;

    private final ConnectionRepository connectionRepository;

    private final LinkedinFriendCreator linkedinFriendCreator;

    @Autowired
    public LinkedinFriendRetriever(LinkedIn linkedIn, ConnectionRepository connectionRepository, LinkedinFriendCreator linkedinFriendCreator) {
        this.linkedIn = linkedIn;
        this.connectionRepository = connectionRepository;
        this.linkedinFriendCreator = linkedinFriendCreator;
    }

    @Override
    public List<Friend> getFriends() {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return Collections.emptyList();
        }
        /*return this.linkedIn.connectionOperations().getConnections()
                .stream()
                .map(this::mapToFull)
                .map(linkedinFriendCreator::create)
                .collect(Collectors.toList());
                !permission*/
        return Collections.emptyList();
    }

    private LinkedInProfileFull mapToFull(LinkedInProfile profile) {
        return linkedIn.profileOperations().getProfileFullByPublicUrl(profile.getPublicProfileUrl());
    }
}

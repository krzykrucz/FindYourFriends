package pl.edu.agh.social.facebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import pl.edu.agh.social.Post;
import pl.edu.agh.social.PostRetriever;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hector on 29/06/2017.
 */

@Component
public class FacebookPostRetriever implements PostRetriever {

    private final Facebook facebook;

    private final ConnectionRepository connectionRepository;

    private final FacebookPostCreator facebookPostCreator;

    @Autowired
    public FacebookPostRetriever(Facebook facebook, ConnectionRepository connectionRepository, FacebookPostCreator facebookPostCreator) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        this.facebookPostCreator = facebookPostCreator;
    }

    @Override
    public List<Post> getPosts() {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return Collections.emptyList();
        }
        return facebook.friendOperations().getFriendIds()
                .stream()
                .flatMap(id -> facebook.feedOperations().getPosts(id).stream())
                .map(facebookPostCreator::create)
                .collect(Collectors.toList());
    }
}

package pl.edu.agh.social.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;
import pl.edu.agh.social.Post;
import pl.edu.agh.social.PostRetriever;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hector on 24/06/2017.
 */

@Component
public class TwitterPostRetriever implements PostRetriever {

    private final Twitter twitter;

    private final ConnectionRepository connectionRepository;

    private final TwitterPostCreator twitterPostCreator;

    @Autowired
    public TwitterPostRetriever(Twitter twitter, ConnectionRepository connectionRepository, TwitterPostCreator twitterPostCreator) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
        this.twitterPostCreator = twitterPostCreator;
    }

    @Override
    public List<Post> getPosts() {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return Collections.emptyList();
        }
        CursoredList<Long> pagedFriendIds = twitter.friendOperations().getFriendIds();
        final List<Long> allFriendIds = new LinkedList<>(pagedFriendIds);
        while (pagedFriendIds.hasNext()) {
            pagedFriendIds = twitter.friendOperations().getFriendIdsInCursor(pagedFriendIds.getNextCursor());
            allFriendIds.addAll(pagedFriendIds);
        }
        final TimelineOperations timelineOperations = twitter.timelineOperations();
        return allFriendIds.stream()
                .flatMap(id -> timelineOperations.getUserTimeline(id).stream())
                .map(twitterPostCreator::create)
                .collect(Collectors.toList());
    }
}

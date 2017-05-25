package pl.edu.agh.social.twitter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;
import pl.edu.agh.social.Friend;
import pl.edu.agh.social.FriendRetriever;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TwitterFriendRetriever implements FriendRetriever {

    private final Twitter twitter;

    private final ConnectionRepository connectionRepository;

    private final TwitterFriendCreator twitterFriendCreator;

    @Autowired
    public TwitterFriendRetriever(Twitter twitter, ConnectionRepository connectionRepository, TwitterFriendCreator twitterFriendCreator) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
        this.twitterFriendCreator = twitterFriendCreator;
    }

    @Override
    public List<Friend> getFriends() {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return Collections.emptyList();
        }

        return this.twitter.friendOperations().getFriends()
                .stream()
                .map(twitterFriendCreator::create)
                .collect(Collectors.toList());
    }

    public TwitterDto getBasicData() {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null)
            return new TwitterDto("");

        return new TwitterDto(
                twitter.userOperations().getUserProfile().getName()
        );
    }


//    private void searcher() {
//        SearchResults results = twitter.searchOperations().search(
//                new SearchParameters("#manchester")
//                        .geoCode(new GeoCode(50.379241, 19.900846, 200, GeoCode.Unit.KILOMETER))
//                        .lang("pl")
//                        .resultType(SearchParameters.ResultType.RECENT)
//                        .count(25)
//                        .includeEntities(false));
//
//        for (Tweet e : results.getTweets()) {
//            System.out.println(e.getText());
//        }
//    }
}

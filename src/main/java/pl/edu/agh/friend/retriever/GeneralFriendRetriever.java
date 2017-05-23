package pl.edu.agh.friend.retriever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.friend.Friend;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hector on 23/04/2017.
 */

@Service
public class GeneralFriendRetriever implements FriendRetriever {

    private final List<FriendRetriever> friendRetrievers;

    @Autowired
    public GeneralFriendRetriever(List<FriendRetriever> friendRetrievers) {
        this.friendRetrievers = friendRetrievers;
    }

    @Override
    public List<Friend> getFriends() {
        return friendRetrievers
                .stream()
                .flatMap(r -> r.getFriends().stream())
                .collect(Collectors.toList());
    }
}

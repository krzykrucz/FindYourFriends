package pl.edu.agh.friend.creator;

import pl.edu.agh.friend.Friend;

/**
 * Created by hector on 23/04/2017.
 */
public interface FriendCreator<T> {
    Friend create(T friend);
}

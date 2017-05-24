package pl.edu.agh.social;

/**
 * Created by hector on 23/04/2017.
 */
public interface FriendCreator<T> {
    Friend create(T friend);
}

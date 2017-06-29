package pl.edu.agh.social;

/**
 * Created by hector on 28/06/2017.
 */
public interface PostCreator<T> {
    Post create(T from);
}

package pl.edu.agh.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hector on 28/06/2017.
 */

@Service
public class GeneralPostRetriever implements PostRetriever {

    private final List<PostRetriever> postRetrievers;

    @Autowired
    public GeneralPostRetriever(List<PostRetriever> postRetrievers) {
        this.postRetrievers = postRetrievers;
    }

    @Override
    public List<Post> getPosts() {
        return postRetrievers.stream()
                .flatMap(r -> r.getPosts().stream())
                .collect(Collectors.toList());
    }
}

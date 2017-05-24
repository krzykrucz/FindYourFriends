package pl.edu.agh.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.social.facebook.FacebookFriendRetriever;

/**
 * Created by Wojciech Baczyński on 24.05.17.
 */
@Service
public class SocialService {

    private FacebookFriendRetriever facebookFriendRetriever;

    @Autowired
    public SocialService(FacebookFriendRetriever facebookFriendRetriever) {
        this.facebookFriendRetriever = facebookFriendRetriever;
    }

    public FacebookDto getFacebookBasicData() {
        return facebookFriendRetriever.getFacebookBasicData();
    }
}

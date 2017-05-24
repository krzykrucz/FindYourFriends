package pl.edu.agh.social.facebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.agh.social.FacebookDto;
import pl.edu.agh.social.Friend;
import pl.edu.agh.social.FriendRetriever;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hector on 23/04/2017.
 */

@Component
@Service
public class FacebookFriendRetriever implements FriendRetriever {

    private final Facebook facebook;

    private final ConnectionRepository connectionRepository;

    private final FacebookFriendCreator facebookFriendCreator;

    @Autowired
    public FacebookFriendRetriever(Facebook facebook, ConnectionRepository connectionRepository, FacebookFriendCreator facebookFriendCreator) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        this.facebookFriendCreator = facebookFriendCreator;
    }

    @Override
    public List<Friend> getFriends() {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return Collections.emptyList();
        }
        return this.facebook.friendOperations().getFriendProfiles()
                .stream()
                .map(facebookFriendCreator::create)
                .collect(Collectors.toList());
    }

    public FacebookDto getFacebookBasicData() {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null)
            return new FacebookDto("", "");

        User userProfile = this.facebook.userOperations().getUserProfile();

        String[] fieldsToMap = {
                "getId", "getFirstName", "getMiddleName", "getLastName", "getName", "getBirthday", "getAgeRange",
                "getGender", "getEmail", "getAddress",

                "getAbout", "getCover", "getCurrency", "getDevices", "getEducation", "getFavoriteAthletes",
                "getFavoriteTeams", "getHometown", "getInspirationalPeople", "getInstallType",
                "getInterestedIn", "getLanguages", "getLink", "getLocale", "getLocation", "getMeetingFor",
                "getNameFormat", "getPaymentPricePoints", "getPolitical", "getQuotes", "getRelationshipStatus",
                "getReligion", "getSecuritySettings", "getSignificantOther", "getSports", "getTestGroup",
                "getThirdPartyId", "getTimezone", "getUpdatedTime", "getVerified", "getWebsite", "getWork",
                "getVideoUploadLimits", "isIdentityVerified", "viewerCanSendGift", "isInstalled"
        };

        for (String methodName : fieldsToMap) {
            try {
                Method method = userProfile.getClass().getDeclaredMethod(methodName);
                Object value = method.invoke(userProfile);
                if (value != null)
                    System.out.println(">>> " + methodName + ": " + value);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                System.err.println(">>> No such method: " + methodName);
            }
        }

        return new FacebookDto(
                userProfile.getFirstName(),
                userProfile.getLastName()
        );
    }
}

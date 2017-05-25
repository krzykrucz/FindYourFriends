package pl.edu.agh.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.social.Friend;
import pl.edu.agh.social.FriendDto;
import pl.edu.agh.social.GeneralFriendRetriever;
import pl.edu.agh.social.twitter.TwitterDto;
import pl.edu.agh.social.twitter.TwitterFriendRetriever;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hector on 02/05/2017.
 */
@Service
public class MapService {

    private final GeneralFriendRetriever generalFriendRetriever;
    private final TwitterFriendRetriever twitterFriendRetriever;

    @Autowired
    public MapService(GeneralFriendRetriever generalFriendRetriever, TwitterFriendRetriever twitterFriendRetriever) {
        this.generalFriendRetriever = generalFriendRetriever;
        this.twitterFriendRetriever = twitterFriendRetriever;
    }

    public List<CityDto> getCities() {
        return generalFriendRetriever.getFriends()
                .stream()
                .filter(f -> f.getLivingCity().isPresent())
                .collect(Collectors.groupingBy(Friend::getLivingCity, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> convert(entry.getKey().get(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private CityDto convert(City city, List<Friend> friends) {
        final List<FriendDto> friendDtos = friends.stream()
                .map(f -> new FriendDto(f.getName(), f.getAccountLink()))
                .collect(Collectors.toList());
        return new CityDto(city.getCityName(), city.getLocation(), friendDtos);
    }

    public TwitterDto getTwitterBasicData() {
        return twitterFriendRetriever.getBasicData();
    }

}

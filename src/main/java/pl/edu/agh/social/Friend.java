package pl.edu.agh.social;

import lombok.Getter;
import pl.edu.agh.map.City;

import java.util.Optional;

/**
 * Created by hector on 23/04/2017.
 */

@Getter
public class Friend {

    private final String name;

    private final String accountLink;

    private final City livingCity;

    public Optional<City> getLivingCity() {
        return Optional.ofNullable(livingCity);
    }

    public Friend(String name, String accountLink, City livingCity) {
        this.name = name;
        this.accountLink = accountLink;
        this.livingCity = livingCity;
    }
}

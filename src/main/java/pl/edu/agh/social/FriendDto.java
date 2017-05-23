package pl.edu.agh.social;

import lombok.Getter;

/**
 * Created by hector on 02/05/2017.
 */

@Getter
public class FriendDto {

    private final String name;

    private final String accountLink;

    public FriendDto(String name, String accountLink) {
        this.name = name;
        this.accountLink = accountLink;
    }
}

package pl.edu.agh.social;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Wojciech Baczyński on 24.05.17.
 */
@Getter
@Setter
public class FacebookDto {
    private String firstName;
    private String lastName;

    public FacebookDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

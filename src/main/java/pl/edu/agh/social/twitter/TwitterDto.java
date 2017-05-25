package pl.edu.agh.social.twitter;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Wojciech Baczyński on 25.05.17.
 */

@Getter
@Setter
public class TwitterDto {
    private String name;

    public TwitterDto(String name) {
        this.name = name;
    }
}

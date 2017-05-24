package pl.edu.agh.map;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hector on 02/05/2017.
 */

@Configuration
public class GeoConfig {

    @Bean
    public GeoApiContext geoApiContext(@Value("${google.maps.api.key}") String googleApiKey) {
        return new GeoApiContext().setApiKey(googleApiKey);
    }
}

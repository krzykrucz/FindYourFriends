package pl.edu.agh.map;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by hector on 23/04/2017.
 */

@Service
public class GeoLocationResolver {

    private final GeoApiContext geoApiContext;

    @Autowired
    public GeoLocationResolver(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
        geoApiContext.setApiKey("AIzaSyBxeFsHOkk2AEhkB2soI2rXD_qUMfzNCXg");
    }

    public Optional<GeoLocation> resolveLocation(String placeName) {
        final GeocodingResult[] results;
        try {
            results = GeocodingApi.geocode(geoApiContext, placeName).await();
        } catch (ApiException | IOException | InterruptedException e) {
            return Optional.empty();
        }
        if (results.length == 0) {
            return Optional.empty();
        }
        final LatLng geoLocation = results[0].geometry.location;
        return Optional.of(new GeoLocation(geoLocation.lat, geoLocation.lng));
    }
}

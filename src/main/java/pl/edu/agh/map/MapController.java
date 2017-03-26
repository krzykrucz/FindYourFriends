package pl.edu.agh.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by hector on 25/03/2017.
 */

@Controller
public class MapController {

    @GetMapping("/map")
    public String map() {
        return "map";
    }

}

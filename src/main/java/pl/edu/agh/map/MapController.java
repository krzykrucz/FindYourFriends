package pl.edu.agh.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by hector on 25/03/2017.
 */

@Controller
@Slf4j
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping({"/", "/map"})
    public String map(Model model) {
        final List<CityDto> cityDtos = mapService.getCities();
        model.addAttribute("cities", cityDtos);
        log.info("GET /map", cityDtos);
        return "map";
    }

}

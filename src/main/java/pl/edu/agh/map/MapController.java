package pl.edu.agh.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.agh.social.FacebookDto;
import pl.edu.agh.social.SocialService;

import java.util.List;

/**
 * Created by hector on 25/03/2017.
 */

@Controller
@Slf4j
public class MapController {

    private final MapService mapService;
    private final SocialService socialService;

    @Autowired
    public MapController(MapService mapService, SocialService socialService) {
        this.mapService = mapService;
        this.socialService = socialService;
    }

    @GetMapping({"/", "/map"})
    public String map(Model model) {
        final List<CityDto> cityDtos = mapService.getCities();
        model.addAttribute("cities", cityDtos);
        log.info("GET /map", cityDtos);

        FacebookDto facebookDto = socialService.getFacebookBasicData();
        if (facebookDto != null) {
            System.err.println("GET /facebookDto: " + facebookDto.getFirstName() + ", " + facebookDto.getLastName());

            model.addAttribute("facebookDataFirstName", facebookDto.getFirstName());
            model.addAttribute("facebookDataLastName", facebookDto.getLastName());
            log.info("GET /facebookData", facebookDto);
        }

        return "map";
    }

}

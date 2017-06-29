package pl.edu.agh.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.agh.social.GeneralPostRetriever;
import pl.edu.agh.social.Post;
import pl.edu.agh.social.PostDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by hector on 25/03/2017.
 */

@Controller
@Slf4j
public class MapController {

    private final MapService mapService;

    private final GeneralPostRetriever postRetriever;

    @Autowired
    public MapController(MapService mapService, GeneralPostRetriever postRetriever) {
        this.mapService = mapService;
        this.postRetriever = postRetriever;
    }

    @GetMapping({"/", "/map"})
    public String map(Model model) {
        final List<CityDto> cityDtos = mapService.getCities();
        final List<PostDto> postDtos = postRetriever.getPosts()
                .stream()
                .map(Post::toDto)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        model.addAttribute("cities", cityDtos);
        model.addAttribute("posts", postDtos);
        log.info("GET /map cities: " + cityDtos);
        log.info("GET /map posts: " + postDtos);
        return "map";
    }

}

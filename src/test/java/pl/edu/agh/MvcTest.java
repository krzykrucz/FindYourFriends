package pl.edu.agh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.edu.agh.map.MapController;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by hector on 26/03/2017.
 */

@RunWith(SpringRunner.class)
@WebMvcTest({MapController.class})
public class MvcTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void mapEndpointResponds() throws Exception {
        final MvcResult result = this.mvc.perform(get("/map"))
                .andExpect(status().isOk())
                .andExpect(view().name("map"))
                .andReturn();
        assertNotNull(result);
    }
}

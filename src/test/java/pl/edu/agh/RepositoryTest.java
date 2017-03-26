package pl.edu.agh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.agh.user.UserRepository;

import static org.junit.Assert.assertNotNull;


/**
 * Created by hector on 26/03/2017.
 */

@RunWith(SpringRunner.class)
@DataMongoTest
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepositoryExists() {
        assertNotNull(userRepository);
    }
}
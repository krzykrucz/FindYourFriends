package pl.edu.agh.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.user.User;
import pl.edu.agh.user.UserRepository;

/**
 * Created by hector on 27/03/2017.
 */

@Service
public class FacebookService {

    private final UserRepository userRepository;

    @Autowired
    public FacebookService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run() {
        userRepository.save(new User());

    }
}

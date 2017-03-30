package pl.edu.agh.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hector on 26/03/2017.
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByPassword(String password);

}

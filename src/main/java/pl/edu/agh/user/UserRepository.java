package pl.edu.agh.user;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by hector on 26/03/2017.
 */

public interface UserRepository extends MongoRepository<User, String> {

}

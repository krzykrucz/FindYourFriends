package pl.edu.agh.friend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;

/**
 * Created by hector on 23/05/2017.
 */

@Configuration
public class SocialConfig {

    @Bean
    public SignInAdapter signInAdapter() {
        return (userId, connection, request) -> null;
    }

    @Bean
    public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, SignInAdapter signInAdapter) {
        return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, signInAdapter);
    }
}

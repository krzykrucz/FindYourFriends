package pl.edu.agh.social.retriever;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.agh.social.FriendRetriever;
import pl.edu.agh.social.GeneralFriendRetriever;

import java.lang.reflect.Field;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by hector on 23/04/2017.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class GeneralFriendRetrieverTest {

    @Autowired
    private GeneralFriendRetriever generalFriendRetriever;

    @MockBean
    private Facebook facebook;

    @Test
    public void shouldAutowireRetrievers() throws Exception {
        final Field retrieversField = generalFriendRetriever.getClass().getDeclaredField("friendRetrievers");
        retrieversField.setAccessible(true);
        assertThat((List<FriendRetriever>) retrieversField.get(generalFriendRetriever), hasSize(equalTo(1)));
    }


}
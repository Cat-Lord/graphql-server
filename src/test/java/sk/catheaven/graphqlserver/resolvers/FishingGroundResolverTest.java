package sk.catheaven.graphqlserver.resolvers;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;
import sk.catheaven.graphqlserver.testingUtils.Assertions;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;


@DisplayName("FishingGround Resolver")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FishingGroundResolverTest {

    @MockBean
    FishingGroundResolver fishingGroundResolver;

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @DisplayName("should return empty list when no fishing ground is available")
    @Test
    void noFishingGround() throws IOException {
        doReturn(Collections.emptyList()).when(fishingGroundResolver).allFishingGround();
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/requests/allFishingGround.graphql");

        response.assertThatDataField();
        assertEquals(Collections.emptyList(), response.getList("data.list", FishingGround.class));
    }

    @DisplayName("should return list of available fishing grounds")
    @Test
    void allFishingGround() throws IOException {
        var expectedList = List.of(
                new FishingGround(1L, "Test-Label", "0001"),
                new FishingGround(2L, "Test-Label 2", "0002"),
                new FishingGround(3L, "Test-Label 3", "0003"),
                new FishingGround(4L, "Test-Label 4", "0004"),
                new FishingGround(5L, "Test-Label 5", "0005")
        );
        doReturn(expectedList).when(fishingGroundResolver).allFishingGround();
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/requests/allFishingGround.graphql");

        response.assertThatDataField();
        Assertions.assertCollectionEquals(expectedList, response.getList("data.list", FishingGround.class));
    }
}

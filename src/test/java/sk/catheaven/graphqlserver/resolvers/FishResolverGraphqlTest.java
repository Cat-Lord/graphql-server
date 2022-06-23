package sk.catheaven.graphqlserver.resolvers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sk.catheaven.graphqlserver.domain.persistence.Fish;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static sk.catheaven.graphqlserver.testingUtils.Assertions.assertCollectionEquals;


@DisplayName("FishResolver")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FishResolverGraphqlTest {
    @MockBean
    FishResolver fishResolver;

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("returns empty list when no fish is available")
    void noFish() throws IOException {
        doReturn(Collections.emptyList()).when(fishResolver).allFish();

        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/requests/allFish.graphql");

        assertTrue(response.isOk());
        assertDoesNotThrow(() -> response.assertThatDataField().isNotNull());
        response.assertThatField("$.data").isNotNull();
        response.assertThatField("$.*.allFish").isNotNull();
        var list = response.getList("data.allFish", Fish.class);
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("returns list of fish")
    public void allFish() throws IOException {
        var expectedResult = List.of(
                new Fish(1L,"Test fish 1"),
                new Fish(2L,"Test fish 2"),
                new Fish(3L,"Test fish 3")
        );
        doReturn(expectedResult).when(fishResolver).allFish();

        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/requests/allFish.graphql");

        assertTrue(response.isOk());
        assertDoesNotThrow(() -> response.assertThatDataField().isNotNull());
        response.assertThatField("$.data").isNotNull();
        response.assertThatField("$.*.allFish").isNotNull();

        var list = response.getList("data.allFish", Fish.class);
        assertCollectionEquals(expectedResult, list);
    }

    @Test
    @DisplayName("returns expected fish when a correct ID was supplied")
    void fishById() throws IOException {
        var expectedResult = new Fish(1L,"Test fish 1");
        doReturn(Optional.of(expectedResult)).when(fishResolver).getFishById(1L);

        var variables = mapper.createObjectNode();
        variables.put("id", "1");

        GraphQLResponse response = graphQLTestTemplate.perform("graphql/requests/fishById.graphql", variables);
        assertTrue(response.isOk());
        assertEquals(expectedResult, response.get("data.getFishById", Fish.class));
    }

    @Test
    @DisplayName("returns nothing when an unknown ID for fish was supplied")
    void noFishForUnknownId() throws IOException {
        doReturn(Optional.empty()).when(fishResolver).getFishById(any());
        var variables = mapper.createObjectNode();
        variables.put("id", "123123");

        GraphQLResponse response = graphQLTestTemplate.perform("graphql/requests/fishById.graphql", variables);
        assertTrue(response.isOk());
    }
}

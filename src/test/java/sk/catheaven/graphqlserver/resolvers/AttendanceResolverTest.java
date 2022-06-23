package sk.catheaven.graphqlserver.resolvers;

import ch.qos.logback.core.spi.LifeCycle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sk.catheaven.graphqlserver.domain.attendance.NewCatch;
import sk.catheaven.graphqlserver.domain.persistence.Fish;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@Log4j2
@DisplayName("AttendanceResolver")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AttendanceResolverTest {

    @MockBean
    AttendanceResolver attendanceResolver;

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    ObjectMapper mapper;

    private Fish firstFish;
    private Fish secondFish;
    private FishingGround fishingGround;
    private List<NewCatch> newCatches;

    @BeforeEach
    void prepare() {
        firstFish = new Fish(1L, "sample-fish-1");
        secondFish = new Fish(2L, "sample-fish-2");
        fishingGround = FishingGround.builder()
                .id(1L)
                .code("0001")
                .label("sample-fg-label")
                .build();
        newCatches = List.of(
                NewCatch.builder()
                        .fishID(firstFish.getId())
                        .totalAmount(123)
                        .totalWeight(123.0)
                        .build(),
                NewCatch.builder()
                        .fishID(secondFish.getId())
                        .totalAmount(2)
                        .totalWeight(3.0)
                        .build()
        );
    }

    @Test
    @DisplayName("adding attendance should add attendance without errors")
    void addAttendance() throws IOException {
        Long attendanceId = 13L;
        int totalVisits = 123;

        doReturn(attendanceId).when(attendanceResolver).addAttendance(fishingGround.getId(),
                totalVisits,
                newCatches.toArray(NewCatch[]::new)
        );

        var variables = mapper.createObjectNode();
        variables.put("fishingGroundId", fishingGround.getId());
        variables.put("totalVisits", totalVisits);

        ArrayNode newCatchesVariable = mapper.valueToTree(newCatches);
        variables.putArray("catches").addAll(newCatchesVariable);

        var response = graphQLTestTemplate.perform("graphql/mutations/addAttendance.graphql", variables);

        assertTrue(response.isOk());
        response.assertThatNoErrorsArePresent();
        response.assertThatDataField();
        response.assertThatField("$.data.id").as(Long.class).isEqualTo(attendanceId);
    }

    @DisplayName("should throw an error if a required argument is missing")
    @ParameterizedTest
    @MethodSource("getFaultyVariables")
    void errorOnMissingArgument(ObjectNode variables) throws IOException {
        var response = graphQLTestTemplate.perform("graphql/mutations/addAttendance.graphql", variables);

        response.assertThatNumberOfErrors().isEqualTo(1);
        response.assertThatErrorsField().isNotNull();
    }

    private Stream<Arguments> getFaultyVariables() {
        int totalVisits = 123;
        ArrayNode newCatchesVariable = mapper.valueToTree(Collections.emptyList());

        var variables = mapper.createObjectNode();
        variables.put("fishingGroundId", 1L);

        var variables2 = mapper.createObjectNode();
        variables2.put("fishingGroundId", 1L);
        variables2.putArray("catches").addAll(newCatchesVariable);

        var variables3 = mapper.createObjectNode();
        variables3.put("totalVisits", totalVisits);
        variables3.putArray("catches").addAll(newCatchesVariable);

        return Stream.of(
                Arguments.of(variables),
                Arguments.of(variables2),
                Arguments.of(variables3)
        );
    }

}

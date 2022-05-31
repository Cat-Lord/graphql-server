package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.catheaven.graphqlserver.domain.statistics.AttendanceStatistics;
import sk.catheaven.graphqlserver.repository.FishingGroundRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StatisticsResolver implements GraphQLQueryResolver {

    FishingGroundRepository fishingGroundRepository;

    public List<AttendanceStatistics> allFishingGroundsStatistics() {
        return fishingGroundRepository.groupByFishingGround();
    }
}

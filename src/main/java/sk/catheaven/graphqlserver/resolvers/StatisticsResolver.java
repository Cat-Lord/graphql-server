package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;
import sk.catheaven.graphqlserver.domain.statistics.AttendanceStatistics;
import sk.catheaven.graphqlserver.domain.statistics.CatchStatistics;
import sk.catheaven.graphqlserver.domain.statistics.FishStatistics;
import sk.catheaven.graphqlserver.domain.statistics.FishingGroundCatchStatistics;
import sk.catheaven.graphqlserver.repository.FishRepository;
import sk.catheaven.graphqlserver.repository.FishingGroundRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StatisticsResolver implements GraphQLQueryResolver {

    FishingGroundRepository fishingGroundRepository;
    FishRepository fishRepository;

    public List<AttendanceStatistics> allAttendanceStatistics() {
        return fishingGroundRepository.allAttendanceStatistics();
    }

    public List<FishingGroundCatchStatistics> allFishingGroundCatchStatistics() {
        List<FishingGroundCatchStatistics> allStatistics = new ArrayList<>();

        List<FishingGround> fishingGrounds = fishingGroundRepository.findAll();

        for (var ground : fishingGrounds) {
            List<CatchStatistics> stats = fishingGroundRepository.findByFishingGround(ground);
            if (stats.isEmpty())
                continue;

            allStatistics.add(
                new FishingGroundCatchStatistics(ground, stats)
            );
        }

        return allStatistics;
    }

    public List<FishStatistics> allFishStatistics() {
        return fishRepository.groupByFish();
    }
}

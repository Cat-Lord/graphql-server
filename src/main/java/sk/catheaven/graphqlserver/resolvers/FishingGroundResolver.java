package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sk.catheaven.graphqlserver.domain.statistics.FishingGroundStatistics;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;
import sk.catheaven.graphqlserver.repository.FishingGroundRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FishingGroundResolver implements GraphQLQueryResolver {

    private final FishingGroundRepository fishingGroundRepository;

    public List<FishingGround> allFishingGround() {
        return fishingGroundRepository.findAll();
    }

    public List<FishingGroundStatistics> getAllFishingGroundsStatistics() {
        return fishingGroundRepository.groupByFishingGround();
    }
}

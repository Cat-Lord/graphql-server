package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.catheaven.graphqlserver.domain.FishingGroundStatistics;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;
import sk.catheaven.graphqlserver.repository.FishingGroundRepository;

import java.util.List;

@Service
public class FishingGroundResolver implements GraphQLQueryResolver {

    @Autowired
    FishingGroundRepository fishingGroundRepository;

    public List<FishingGround> allFishingGround() {
        return fishingGroundRepository.findAll();
    }

    public List<FishingGroundStatistics> getFishingGroundStatistics() {
        return List.of();
    }
}

package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sk.catheaven.graphqlserver.domain.persistence.Fish;
import sk.catheaven.graphqlserver.repository.FishRepository;

import java.util.List;

@Service
public class FishResolver implements GraphQLQueryResolver  {

    @Autowired
    FishRepository fishRepository;

    public List<Fish> allFish() {
        return fishRepository.findAll();
    }
}

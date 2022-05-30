package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sk.catheaven.graphqlserver.domain.persistence.Fish;
import sk.catheaven.graphqlserver.repository.FishRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FishResolver implements GraphQLQueryResolver  {

    private final FishRepository fishRepository;

    public List<Fish> allFish() {
        return fishRepository.findAll();
    }

    public Optional<Fish> getFishById(Long id) {
        return fishRepository.findById(id);
    }
}

package sk.catheaven.graphqlserver.domain;

import lombok.Value;
import sk.catheaven.graphqlserver.domain.persistence.Fish;

@Value
public class FishStatistics {
    Fish fish;
    Long totalAmount;
    Double totalWeight;

    public FishStatistics(Fish fish, Long totalAmount, Double totalWeight) {
        this.fish = fish;
        this.totalAmount = totalAmount;
        this.totalWeight = totalWeight;
    }

}

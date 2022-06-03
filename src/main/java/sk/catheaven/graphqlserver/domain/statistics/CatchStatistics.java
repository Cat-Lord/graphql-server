package sk.catheaven.graphqlserver.domain.statistics;

import lombok.Data;
import sk.catheaven.graphqlserver.domain.persistence.Fish;

@Data
public class CatchStatistics {
    Fish fish;
    Long totalAmount;
    Double totalWeight;

    public CatchStatistics(Fish fish, Long totalAmount, Double totalWeight) {
        this.fish = fish;
        this.totalAmount = totalAmount;
        this.totalWeight = totalWeight;
    }
}

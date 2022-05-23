package sk.catheaven.graphqlserver.domain;

import lombok.Data;
import sk.catheaven.graphqlserver.domain.persistence.Fish;

/**
 * How many fish and what amount got caught on what fishing ground
 */
@Data
public class CatchStatistics {

    Fish fish;
    Double totalWeight;
    Long totalAmount;

    public CatchStatistics(Fish fish, Long totalAmount, Double totalWeight) {
        this.fish = fish;
        this.totalWeight = totalWeight;
        this.totalAmount = totalAmount;
    }

}

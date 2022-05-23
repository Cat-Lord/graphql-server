package sk.catheaven.graphqlserver.domain;

import lombok.Data;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;

/**
 * For a specific fishing ground group all the available values.
 *
 * NOT an entity.
 */
@Data
public class FishingGroundStatistics {
    
    private FishingGround fishingGround;
    private Long totalAmount;
    private Double totalWeight;
    private Long totalNumberOfVisits;

    public FishingGroundStatistics(FishingGround fishingGround, Long totalNumberOfVisits, Long totalAmount, Double totalWeight) {
        this.fishingGround = fishingGround;
        this.totalNumberOfVisits = totalNumberOfVisits;
        this.totalAmount = totalAmount;
        this.totalWeight = totalWeight;
    }

}

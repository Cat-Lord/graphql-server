package sk.catheaven.graphqlserver.domain.statistics;

import lombok.Data;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;

import java.util.List;

/**
 * How many fish and what amount got caught on what fishing ground
 */
@Data
public class FishingGroundCatchStatistics {

    FishingGround fishingGround;
    List<CatchStatistics> catchStatistics;

    public FishingGroundCatchStatistics(FishingGround fishingGround, List<CatchStatistics> catchStatistics) {
        this.fishingGround = fishingGround;
        this.catchStatistics = catchStatistics;
    }

}

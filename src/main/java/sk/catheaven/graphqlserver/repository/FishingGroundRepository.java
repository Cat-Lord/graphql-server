package sk.catheaven.graphqlserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sk.catheaven.graphqlserver.domain.statistics.FishingGroundStatistics;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;

import java.util.List;

public interface FishingGroundRepository extends JpaRepository<FishingGround, Long> {
    @Query("SELECT new sk.catheaven.graphqlserver.domain.statistics.FishingGroundStatistics(" +
            "    att.fishingGround, SUM(att.numberOfVisits), SUM(catch.totalAmount), SUM(catch.totalWeight))" +
            "FROM Attendance att " +
            "LEFT JOIN Catch catch ON catch.attendance = att " +
            "GROUP BY " +
            "    att.fishingGround " +
            "ORDER BY att.fishingGround.code ASC")
    List<FishingGroundStatistics> groupByFishingGround();
}

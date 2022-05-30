package sk.catheaven.graphqlserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sk.catheaven.graphqlserver.domain.statistics.CatchStatistics;
import sk.catheaven.graphqlserver.domain.persistence.Catch;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;

import java.util.List;

public interface CatchRepository extends JpaRepository<Catch, Long> {
    @Query("SELECT " +
            "   new sk.catheaven.graphqlserver.domain.statistics.CatchStatistics(catch.fish, " +
                                                       "SUM(catch.totalAmount), " +
                                                       "SUM(catch.totalWeight)" +
            "                                          ) " +
            "FROM " +
            "   Catch catch " +
            "GROUP BY " +
            "   catch.attendance.fishingGround, catch.fish " +
            "HAVING " +
            "   catch.attendance.fishingGround = ?1")
    List<CatchStatistics> findByFishingGround(FishingGround fishingGround);
}

package sk.catheaven.graphqlserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sk.catheaven.graphqlserver.domain.statistics.FishStatistics;
import sk.catheaven.graphqlserver.domain.persistence.Fish;

import java.util.List;

public interface FishRepository extends JpaRepository<Fish, Long> {
    @Query("SELECT new sk.catheaven.graphqlserver.domain.statistics.FishStatistics(" +
            "    catch.fish, SUM(catch.totalAmount), SUM(catch.totalWeight))" +
            "FROM Catch catch " +
            "GROUP BY " +
            "    catch.fish")
    List<FishStatistics> groupByFish();
}

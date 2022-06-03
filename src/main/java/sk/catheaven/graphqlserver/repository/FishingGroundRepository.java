package sk.catheaven.graphqlserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;
import sk.catheaven.graphqlserver.domain.statistics.AttendanceStatistics;
import sk.catheaven.graphqlserver.domain.statistics.CatchStatistics;

import java.util.List;

public interface FishingGroundRepository extends JpaRepository<FishingGround, Long> {
    @Query("SELECT new sk.catheaven.graphqlserver.domain.statistics.AttendanceStatistics(" +
            "           att.fishingGround, " +
            "           SUM(att.numberOfVisits)," +
            "           COALESCE(SUM(catch.totalAmount), 0)," +
            "           COALESCE(SUM(catch.totalWeight), 0))" +
            "FROM Attendance att " +
            "LEFT JOIN Catch catch ON catch.attendance = att " +
            "GROUP BY " +
            "    att.fishingGround " +
            "ORDER BY att.fishingGround.code ASC")
    List<AttendanceStatistics> allAttendanceStatistics();

    @Query("SELECT " +
            "   new sk.catheaven.graphqlserver.domain.statistics.CatchStatistics(catch.fish, " +
                        "                                             SUM(catch.totalAmount), " +
                        "                                             SUM(catch.totalWeight)" +
                        "                                            ) " +
            "FROM Catch catch " +
            "GROUP BY " +
            "   catch.attendance.fishingGround, catch.fish " +
            "HAVING catch.attendance.fishingGround = ?1")
    List<CatchStatistics> findByFishingGround(FishingGround fishingGround);
}

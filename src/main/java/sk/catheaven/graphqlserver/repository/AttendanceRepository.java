package sk.catheaven.graphqlserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.catheaven.graphqlserver.domain.persistence.Attendance;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByFishingGround(FishingGround fishingGround);
}

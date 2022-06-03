package sk.catheaven.graphqlserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.catheaven.graphqlserver.domain.persistence.Catch;

public interface CatchRepository extends JpaRepository<Catch, Long> {
}

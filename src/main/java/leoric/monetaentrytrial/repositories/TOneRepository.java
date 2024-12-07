package leoric.monetaentrytrial.repositories;

import leoric.monetaentrytrial.models.FirstTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TOneRepository extends JpaRepository<FirstTask, Long> {
}
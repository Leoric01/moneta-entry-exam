package leoric.monetaentrytrial.repositories;

import leoric.monetaentrytrial.models.SecondTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondTaskRepository extends JpaRepository<SecondTask, Long> {
}
package leoric.monetaentrytrial.repositories;

import leoric.monetaentrytrial.models.ActionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionHistoryRepository extends JpaRepository<ActionHistory, Long> {

}
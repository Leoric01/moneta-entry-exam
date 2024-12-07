package leoric.monetaentrytrial.repositories;

import leoric.monetaentrytrial.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findFirstByOrderByPositionAsc();

    Optional<Ticket> findFirstByOrderByIdDesc();

    Optional<Ticket> findByPosition(int position);

}
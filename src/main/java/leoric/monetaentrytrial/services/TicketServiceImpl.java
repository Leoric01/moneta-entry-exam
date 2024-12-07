package leoric.monetaentrytrial.services;

import jakarta.persistence.EntityNotFoundException;
import leoric.monetaentrytrial.dtos.responses.TicketDtoResponse;
import leoric.monetaentrytrial.models.Ticket;
import leoric.monetaentrytrial.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public TicketDtoResponse generateTicket(Integer defaultTicketNumber) throws Exception {
        int position = (int) ticketRepository.count();
        int ticketNumber = ticketRepository.findFirstByOrderByIdDesc()
                .map(Ticket::getTicketNumber)
                .orElse(defaultTicketNumber);

        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticketNumber);
        ticket.setTimestamp(LocalDateTime.now());
        ticket.setPosition(position);

        return convertTicketToTicketDto(ticketRepository.save(ticket));
    }

    @Override
    public TicketDtoResponse getCurrentTicket() throws Exception {
        Ticket ticket = ticketRepository.findFirstByOrderByPositionAsc().orElseThrow(
                () -> new EntityNotFoundException("No active tickets at the moment"));
        return convertTicketToTicketDto(ticket);
    }

    public void deleteLastTicket() {
        Optional<Ticket> firstTicket = ticketRepository.findByPosition(0);
        if (firstTicket.isEmpty()) {
            return;
        }
        ticketRepository.delete(firstTicket.get());
        ticketRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Ticket::getPosition))
                .forEach(ticket -> {
                    ticket.setPosition(ticket.getPosition() - 1);
                    ticketRepository.save(ticket);
                });
    }

    @Override
    public List<TicketDtoResponse> getAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets
                .stream()
                .map(ticket -> {
                    try {
                        return convertTicketToTicketDto(ticket);
                    } catch (Exception e) {
                        throw new RuntimeException("Error converting ticket", e);
                    }
                })
                .collect(Collectors.toList());
    }

    private TicketDtoResponse convertTicketToTicketDto(Ticket ticket) throws Exception {
        if (ticket == null) {
            throw new Exception("ticket is null, cant convert");
        }
        TicketDtoResponse dto = new TicketDtoResponse();
        dto.setTicketInformation(ticket.toString());
        return dto;
    }
}
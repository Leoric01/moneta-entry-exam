package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.responses.TicketDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    TicketDtoResponse generateTicket(Integer defaultTicketNumber) throws Exception;

    TicketDtoResponse getCurrentTicket() throws Exception;

    void deleteLastTicket();

    List<TicketDtoResponse> getAll();

    TicketDtoResponse getCurrentTicketOrNull();
}
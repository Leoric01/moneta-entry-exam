package leoric.monetaentrytrial.controllers.restcontrollers;

import leoric.monetaentrytrial.dtos.responses.Result;
import leoric.monetaentrytrial.dtos.responses.TicketDtoResponse;
import leoric.monetaentrytrial.services.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/task-three")
public class TicketRestController {
    private final TicketService ticketService;

    @Value("${ticket.default-number:1244}")
    private Integer ticketDefaultNumber;

    @PostMapping
    public ResponseEntity<TicketDtoResponse> generateTicket() throws Exception {
        TicketDtoResponse ticket = ticketService.generateTicket(ticketDefaultNumber);
        return ResponseEntity.status(201).body(ticket);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TicketDtoResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAll());
    }

    @GetMapping("/current")
    public ResponseEntity<Result<TicketDtoResponse>> getCurrentTicket() throws Exception {
        TicketDtoResponse output = ticketService.getCurrentTicket();
        Result<TicketDtoResponse> response = Result.success(output, "Current ticket fetched successfully", OK.value());
        return ResponseEntity.status(OK).body(response);
    }

    @DeleteMapping("/last")
    public void deleteLastTicket() {
        ticketService.deleteLastTicket();
    }
}
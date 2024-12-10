package leoric.monetaentrytrial.controllers.main;

import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.requests.TaskTwoInput;
import leoric.monetaentrytrial.dtos.responses.TicketDtoResponse;
import leoric.monetaentrytrial.models.FirstTask;
import leoric.monetaentrytrial.models.SecondTask;
import leoric.monetaentrytrial.services.ModifyIntegerService;
import leoric.monetaentrytrial.services.TicketService;
import leoric.monetaentrytrial.services.TransformTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@Controller
@RequestMapping("/ui")
@RequiredArgsConstructor
public class MainController {

    private final ModifyIntegerService modifyIntegerService;
    private final TransformTextService transformTextService;
    private final TicketService ticketService;
    @Value("${ticket.default-number:1244}")
    private Integer ticketDefaultNumber;

    @GetMapping
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/first")
    public String firstPage(Model model) {
        List<FirstTask> list = transformTextService.fetchAll();
        model.addAttribute("textin", new TaskOneInput());
        model.addAttribute("list", list);
        return "firsttask";
    }

    @PostMapping("/first/add")
    public String addNewTextToTransform(@ModelAttribute TaskOneInput textin) {
        transformTextService.reverseAndModify(textin);
        return "redirect:/ui/first";
    }

    @GetMapping("/second")
    public String secondPage(Model model) {
        List<SecondTask> list = modifyIntegerService.fetchAll();
        model.addAttribute("intIn", new TaskTwoInput());
        model.addAttribute("list", list);
        return "secondtask";
    }

    @PostMapping("/second/add")
    public String addNewIntToTransform(@ModelAttribute TaskTwoInput intIn) {
        modifyIntegerService.modifyDigits(intIn);
        return "redirect:/ui/second";
    }

    @GetMapping("/third")
    public String thirdPage() {
        return "thirdtask";
    }

    @GetMapping("/third/all")
    public String thirdPageTickets(Model model) {
        List<TicketDtoResponse> tickets = ticketService.getAll();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @PostMapping("/third/generate")
    public String thirdPageGenerateTicket(Model model) throws Exception {
        TicketDtoResponse ticket = ticketService.generateTicket(ticketDefaultNumber);
        model.addAttribute("ticket", ticket);
        return "generateticket";
    }

    @GetMapping("/third/current")
    public String thirdPageCurrentTicket(Model model) {
        TicketDtoResponse ticket = ticketService.getCurrentTicketOrNull();
        if (ticket == null) {
            model.addAttribute("message", "No current active tickets");
        } else {
            model.addAttribute("ticket", ticket);
        }
        return "currentticket";
    }

    @DeleteMapping("/third/delete")
    public String thirdPageDeleteTicket() {
        ticketService.deleteLastTicket();
        return "redirect:/ui/third/all";
    }
}
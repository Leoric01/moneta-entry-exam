package leoric.monetaentrytrial.controllers.main;

import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedText;
import leoric.monetaentrytrial.services.ModifyIntegerService;
import leoric.monetaentrytrial.services.TicketService;
import leoric.monetaentrytrial.services.TransformTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Controller
@RequestMapping("/ui")
@RequiredArgsConstructor
public class MainController {

    private final ModifyIntegerService modifyIntegerService;
    private final TransformTextService transformTextService;
    private final TicketService ticketService;

    @GetMapping
    public String mainPage(Model model) {
        return "mainPage";
    }

    @GetMapping("/first")
    public String firstPage(Model model) {
        model.addAttribute("textin", new TaskOneInput());
        return "firsttask";
    }

    @PostMapping("/first/add")
    public String addUser(@ModelAttribute TaskOneInput textin) {
        ModifiedText output = transformTextService.reverseAndModify(textin);
        return "redirect:/first";
    }

    @GetMapping("/second")
    public String secondPage(Model model) {
        return "mainPage";
    }

    @GetMapping("/third")
    public String thirdPage(Model model) {
        return "mainPage";
    }
}
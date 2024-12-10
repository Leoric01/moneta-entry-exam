package leoric.monetaentrytrial.controllers.restcontrollers;

import jakarta.validation.Valid;
import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedText;
import leoric.monetaentrytrial.dtos.responses.Result;
import leoric.monetaentrytrial.services.TransformTextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/task-one")
public class ModifyStringController {

    private final TransformTextService transformTextService;

    @PostMapping("/reverse")
    public ResponseEntity<Result<ModifiedText>> reverseAndModifyText(@Valid @RequestBody TaskOneInput input) {
        ModifiedText output = transformTextService.reverseAndModify(input);
        Result<ModifiedText> response = Result.success(output, "Text modified successfully", OK.value());
        return ResponseEntity.status(OK).body(response);
    }
}
package leoric.monetaentrytrial.controllers.restcontrollers;

import jakarta.validation.Valid;
import leoric.monetaentrytrial.dtos.requests.TaskTwoInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedInt;
import leoric.monetaentrytrial.dtos.responses.Result;
import leoric.monetaentrytrial.handler.BusinessErrorCodes;
import leoric.monetaentrytrial.services.ModifyIntegerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/task-two")
public class ModifyIntegerController {
    private final ModifyIntegerService modifyIntegerService;

    @PostMapping("/integer")
    public ResponseEntity<Result<ModifiedInt>> modifyInteger(@Valid @RequestBody TaskTwoInput input) {
        try {
            ModifiedInt output = modifyIntegerService.modifyDigits(input);
            return ResponseEntity.ok(
                    Result.success(output, "Integer modified successfully", OK.value())
            );
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                    Result.failure(BusinessErrorCodes.NO_CODE.getCode(), "An unexpected error occurred")
            );
        }
    }
}
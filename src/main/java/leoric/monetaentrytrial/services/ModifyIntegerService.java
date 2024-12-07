package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.requests.TaskTwoInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedInt;
import org.springframework.stereotype.Service;

@Service
public interface ModifyIntegerService {
    ModifiedInt modifyDigits(TaskTwoInput input);
}
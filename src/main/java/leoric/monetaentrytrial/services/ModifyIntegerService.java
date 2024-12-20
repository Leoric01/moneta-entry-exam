package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.requests.TaskTwoInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedInt;
import leoric.monetaentrytrial.models.SecondTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModifyIntegerService {
    ModifiedInt modifyDigits(TaskTwoInput input);

    List<SecondTask> fetchAll();
}
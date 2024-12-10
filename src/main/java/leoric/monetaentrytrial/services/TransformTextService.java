package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedText;
import leoric.monetaentrytrial.models.FirstTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransformTextService {
    ModifiedText reverseAndModify(TaskOneInput input);

    List<FirstTask> fetchAll();
}
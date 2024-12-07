package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedText;
import org.springframework.stereotype.Service;

@Service
public interface TransformTextService {
    ModifiedText reverseAndModify(TaskOneInput input);
}
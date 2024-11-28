package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.responses.ReversedText;
import org.springframework.stereotype.Service;

@Service
public interface TransformTextService {
    ReversedText reverseAndModify(TaskOneInput input);
}
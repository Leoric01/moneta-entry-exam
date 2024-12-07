package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedText;
import leoric.monetaentrytrial.models.TOne;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransformTextService {
    ModifiedText reverseAndModify(TaskOneInput input);

    List<TOne> fetchAll();
}
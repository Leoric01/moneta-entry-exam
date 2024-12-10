package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.requests.TaskTwoInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedInt;
import leoric.monetaentrytrial.models.SecondTask;
import leoric.monetaentrytrial.repositories.SecondTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModifyIntegerServiceImpl implements ModifyIntegerService {
    private final SecondTaskRepository secondTaskRepository;

    @Override
    public ModifiedInt modifyDigits(TaskTwoInput input) {
        SecondTask secondTask = new SecondTask();
        if (input == null) {
            input = new TaskTwoInput(0);
        } else if (input.getInputInt() == null) {
            input.setInputInt(0);
        }
        secondTask.setInput(Long.valueOf(input.getInputInt()));
        int beforeTransform = input.getInputInt();
        ModifiedInt modifiedInt = new ModifiedInt();
        int afterTransform = calculate(beforeTransform);
        modifiedInt.setOutputInt(afterTransform);
        secondTask.setOutput((long) afterTransform);
        secondTaskRepository.save(secondTask);
        return modifiedInt;
    }

    @Override
    public List<SecondTask> fetchAll() {
        return secondTaskRepository.findAll();
    }

    private int calculate(int inputNumber) {
        boolean isInputNegative = inputNumber < 0;
        if (isInputNegative) {
            inputNumber *= -1;
        }
        int shiftedNumber = IntegerModificationUtils.shiftDigits(inputNumber);
        int multipliedNumber = IntegerModificationUtils.multiplyDigits(shiftedNumber);
        int strippedSevens = IntegerModificationUtils.removeAllSevens(multipliedNumber);
        int result = getResult(strippedSevens);

        return isInputNegative ? -result : result;
    }

    private int getResult(int strippedSevens) {
        int evenCount = IntegerModificationUtils.countEvenDigits(strippedSevens);
        int result;
        if (evenCount != 0) {
            result = strippedSevens / evenCount;
        } else {
            // zadani rika vydelit poctem sudych, pokud se na tom trva, musime osetrit deleni nulou, ale spis si myslim
            // ze v praxi by se vratilo cislo bez deleni. zalezi na business case
            // throw new ArithmeticException("Division by zero, no even digits found.");
            result = strippedSevens;
        }
        return result;
    }
}
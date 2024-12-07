package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.requests.TaskTwoInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ModifyIntegerServiceImpl implements ModifyIntegerService {

    @Override
    public ModifiedInt modifyDigits(TaskTwoInput input) {
        int beforeTransform = input.getInputInt();
        ModifiedInt modifiedInt = new ModifiedInt();
        int afterTransform = calculate(beforeTransform);
        modifiedInt.setOutputInt(afterTransform);
        return modifiedInt;
    }

    private int calculate(int inputNumber) {
        boolean isInputNegative = inputNumber < 0;
        if (isInputNegative) {
            inputNumber *= -1;
        }
        int shiftedNumber = IntegerModificationUtils.shiftDigits(inputNumber);
        int multipliedNumber = IntegerModificationUtils.multiplyDigits(shiftedNumber);
        int strippedSevens = IntegerModificationUtils.removeAllSevens(multipliedNumber);

        int evenCount = IntegerModificationUtils.countEvenDigits(strippedSevens);
        if (evenCount != 0) {
            // zadani rika vydelit poctem sudych, pokud se na tom trva, musime osetrit deleni nulou, ale spis si myslim
            // ze v praxi by se vratilo cislo bez deleni. zalezi na business case
            // throw new ArithmeticException("Division by zero, no even digits found.");

            return strippedSevens / evenCount;
        }

        return strippedSevens;
    }
}
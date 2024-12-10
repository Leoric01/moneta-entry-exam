package leoric.monetaentrytrial.services;

import java.util.HashMap;

public class IntegerModificationUtils {

    static int nextBiggerThanThree(HashMap<Integer, Integer> inputMap, int currentPosition) {
        for (int i = currentPosition + 1; i < inputMap.size(); i++) {
            if (inputMap.get(i) > 3) {
                return i;
            }
        }
        return -1;
    }

    static int shiftDigits(int number) {
        HashMap<Integer, Integer> mapOfIndexesAndDigits = getMapOfIndexesAndDigits(number);
        int shiftedNumber = 0;
        int power = (int) Math.pow(10, mapOfIndexesAndDigits.size() - 1);

        for (int i = 0; i < mapOfIndexesAndDigits.size(); i++) {
            int currentDigit = mapOfIndexesAndDigits.get(i);
            if (currentDigit <= 3) {
                int nextBiggerThanThreeIndex = nextBiggerThanThree(mapOfIndexesAndDigits, i);
                int powerForNextBiggerThenThree = power;
                if (nextBiggerThanThreeIndex > -1 && nextBiggerThanThreeIndex > i) {
                    for (int j = i; j < nextBiggerThanThreeIndex; j++) {
                        shiftedNumber += mapOfIndexesAndDigits.get(j) * power / 10;
                        power /= 10;
                    }
                    shiftedNumber += mapOfIndexesAndDigits.get(nextBiggerThanThreeIndex) * powerForNextBiggerThenThree;
                    power /= 10;
                    i = nextBiggerThanThreeIndex;
                } else {
                    shiftedNumber += currentDigit * power;
                    power /= 10;
                }
            } else {
                shiftedNumber += currentDigit * power;
                power /= 10;
            }
        }
        System.out.println("Shifted " + shiftedNumber + " digits");
        return shiftedNumber;
    }

    static int multiplyDigits(int number) {
        int multipliedNumber = 0;
        int power = 1;
        while (number > 0) {
            int digit = number % 10;
            if (digit == 8 || digit == 9) {
                digit *= 2;
            }
            if (willOverflow(multipliedNumber, digit, power)) {
                throw new ArithmeticException("Integer overflow occurred during multiplication.");
            }
            multipliedNumber += digit * power;
            power = digit > 9 ? power * 100 : power * 10;
            number /= 10;
        }
        return multipliedNumber;
    }

    static boolean willOverflow(int currentValue, int digit, int power) {
        return (digit > 0 && power > Integer.MAX_VALUE / digit) ||
               currentValue > Integer.MAX_VALUE - (digit * power);
    }

    static HashMap<Integer, Integer> getMapOfIndexesAndDigits(int number) {
        HashMap<Integer, Integer> positionsAndDigitsMap = new HashMap<>();
        if (number == 0) {
            positionsAndDigitsMap.put(0, 0);
            return positionsAndDigitsMap;
        }
        int helperNumber = number;
        int position = 0;
        while (helperNumber > 0) {
            helperNumber /= 10;
            position++;
        }

        for (int i = position - 1; i >= 0; i--) {
            int digit = number / (int) Math.pow(10, i);
            positionsAndDigitsMap.put(position - i - 1, digit);
            number %= (int) Math.pow(10, i);
        }
        return positionsAndDigitsMap;
    }

    static int numberFromMap(HashMap<Integer, Integer> correctOrderDigitsMap) {
        int number = 0;
        int power = 1;
        for (int i = correctOrderDigitsMap.size() - 1; i >= 0; i--) {
            if (power > Integer.MAX_VALUE / 10 || number > Integer.MAX_VALUE - (correctOrderDigitsMap.get(i) * power)) {
                throw new ArithmeticException("Integer overflow while constructing number");
            }
            number += power * correctOrderDigitsMap.get(i);
            power *= 10;
        }
        return number;
    }

    static int countEvenDigits(int inputNumber) {
        int count = 0;
        int digit;
        while (inputNumber != 0) {
            digit = inputNumber % 10;
            if (digit % 2 == 0 && digit != 0) {
                count++;
            }
            inputNumber /= 10;
        }
        return count;
    }

    static int removeAllSevens(int number) {
        int strippedNumber = 0;
        int power = 1;
        while (number > 0) {
            int digit = number % 10;
            if (digit != 7) {
                strippedNumber += digit * power;
                power *= 10;
            }
            number /= 10;
        }
        return strippedNumber;
    }
}
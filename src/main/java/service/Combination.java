package service;

import java.util.*;

public enum Combination {
    GENERALA(50),
    FOUR(40),
    STRAIGHT(30),
    FULL_HOUSE(25),
    TRIPLE(20),
    DOUBLE_PAIR(15),
    PAIR(10);

    public int getConstant() {
        return constant;
    }

    private int constant;

    Combination(int i) {
        this.constant = i;
    }

    public int calculate(List<Integer> dice) {
        TreeMap<Integer, Integer> diceOccurrences = new TreeMap<>(Comparator.reverseOrder());
        for (Integer die : dice) {
            Integer dieValue = diceOccurrences.get(die);
            if (dieValue != null) {
                diceOccurrences.put(die, dieValue + 1);
            } else {
                diceOccurrences.put(die, 1);
            }

        }
        int result = 0;
        switch (this) {
            case GENERALA: {
                result = getResultFromGeneralaCombination(diceOccurrences);
            }
            break;
            case FOUR: {
                result = getResultFromFourCombination(diceOccurrences);
            }
            break;
            case STRAIGHT: {
                result = getResultFromStraightCombination(diceOccurrences);
            }
            break;
            case FULL_HOUSE: {
                result = getResultFromFullHouseCombination(diceOccurrences);
            }
            break;
            case TRIPLE: {
                result = getResultFromTripleCombination(diceOccurrences);
            }
            break;
            case DOUBLE_PAIR: {
                result = getResultFromDoublePairCombination(diceOccurrences);
            }
            break;
            case PAIR: {
                result = getResultFromPairCombination(diceOccurrences);
            }
            break;
        }
        return result != 0 ? result + constant : result;
    }

    private int getResultFromGeneralaCombination(TreeMap<Integer, Integer> diceOccurrences) {
        return calculateResultFromEquals(diceOccurrences, 5);
    }

    private int getResultFromFourCombination(TreeMap<Integer, Integer> diceOccurrences) {
        return calculateResultFromEquals(diceOccurrences, 4);
    }

    private int getResultFromStraightCombination(TreeMap<Integer, Integer> diceOccurrences) {
        int result = 0;
        if (diceOccurrences.size() >= 5) {
            boolean hasStraight = true;
            Object[] keyCompare = diceOccurrences.keySet().toArray();
            for (int i = 0; i < keyCompare.length - 1; i++) {
                int currentKey = (Integer) keyCompare[i];
                int nextKey = (Integer) keyCompare[i + 1];
                if (currentKey - 1 != nextKey) {
                    hasStraight = false;
                }
            }
            if (hasStraight) {
                result = 5 * (Integer) keyCompare[0] - 10;
            }
        }

        return result;
    }

    private int getResultFromFullHouseCombination(TreeMap<Integer, Integer> diceOccurrences) {
        int result = 0;
        int tripleKey = 0;
        int pairKey = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : diceOccurrences.entrySet()) {
            int key = integerIntegerEntry.getKey();
            int value = diceOccurrences.get(key);
            if (value == 3) {
                tripleKey = key;
            }
            if (value == 2) {
                pairKey = key;
            }
        }
        if (tripleKey != 0 && pairKey != 0) {
            result = tripleKey * 3 + pairKey * 2;
        }
        return result;
    }

    private int getResultFromTripleCombination(TreeMap<Integer, Integer> diceOccurrences) {
        return calculateResultFromEquals(diceOccurrences, 3);
    }

    private int getResultFromDoublePairCombination(TreeMap<Integer, Integer> diceOccurrences) {
        int result = 0;
        int firstPairKey = 0;
        int secondPairKey = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : diceOccurrences.entrySet()) {
            int key = integerIntegerEntry.getKey();
            int value = integerIntegerEntry.getValue();
            if (value == 4) {
                firstPairKey = key;
                secondPairKey = key;
            }
            if (value == 3 || value == 2) {
                if (firstPairKey == 0) {
                    firstPairKey = key;
                } else {
                    secondPairKey = key;
                }
            }
        }
        if (firstPairKey != 0 && secondPairKey != 0) {
            result = firstPairKey * 2 + secondPairKey * 2;
        }
        return result;
    }

    private int getResultFromPairCombination(TreeMap<Integer, Integer> diceOccurrences) {
        return calculateResultFromEquals(diceOccurrences, 2);
    }

    private int calculateResultFromEquals(TreeMap<Integer, Integer> diceOccurrences, int count) {
        for (Map.Entry<Integer, Integer> integerIntegerEntry : diceOccurrences.entrySet()) {
            int value = integerIntegerEntry.getValue();
            if (value >= count) {
                int key = integerIntegerEntry.getKey();
                return count * key;
            }
        }
        return 0;
    }
}

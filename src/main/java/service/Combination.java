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
            if (!diceOccurrences.containsKey(die)) {
                diceOccurrences.put(die, 0);
            }
            int value = diceOccurrences.get(die) + 1;
            diceOccurrences.put(die, value);
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
        int result = 0;
        for (Integer key : diceOccurrences.keySet()) {
            int value = diceOccurrences.get(key);
            if (value == 5) {
                result = 5 * key;
            }
        }
        return result;
    }

    private int getResultFromFourCombination(TreeMap<Integer, Integer> diceOccurrences) {
        int result = 0;
        for (Integer key : diceOccurrences.keySet()) {
            int value = diceOccurrences.get(key);
            if (value == 4) {
                result = 4 * key;
            }
        }
        return result;
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
        for (Integer key : diceOccurrences.keySet()) {
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
        int result = 0;
        for (Integer key : diceOccurrences.keySet()) {
            int value = diceOccurrences.get(key);
            if (value == 5 || value == 4 || value == 3) {
                result = 3 * key;
            }
        }
        return result;
    }

    private int getResultFromDoublePairCombination(TreeMap<Integer, Integer> diceOccurrences) {
        int result = 0;
        int firstPairKey = 0;
        int secondPairKey = 0;
        for (Integer key : diceOccurrences.keySet()) {
            int value = diceOccurrences.get(key);
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
        int result = 0;
        int maxPairKey = 0;
        for (Integer key : diceOccurrences.keySet()) {
            int value = diceOccurrences.get(key);
            if (value == 4 || value == 3 || value == 2) {
                if (maxPairKey < key) {
                    maxPairKey = key;
                }
            }
        }
        if (maxPairKey != 0) {
            result = 2 * maxPairKey;
        }
        return result;
    }
}

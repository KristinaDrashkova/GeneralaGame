package service;

import java.util.Collections;
import java.util.List;

public enum Combination {
    GENERALA(50),
    FOUR(40),
    STRAIGHT(30),
    FULL_HOUSE(25),
    TRIPLE(20),
    DOUBLE_PAIR(15),
    PAIR(10);

    private int constant;

    Combination(int i) {
        this.constant = i;
    }

    public int calculate(List<Integer> dice) {
        int result = 0;
        switch (this) {
            case GENERALA: {
                for (int i = 0; i < dice.size(); i++) {
                    int count = 1;
                    for (int j = i + 1; j < dice.size(); j++) {
                        if (dice.get(i) == dice.get(j)) {
                            count++;
                            if (count == 5) {
                                result = this.constant + 5 * dice.get(i);
                                break;
                            }
                        }
                    }
                }
            }
            break;
            case FOUR: {
                for (int i = 0; i < dice.size(); i++) {
                    int count = 1;
                    for (int j = i + 1; j < dice.size(); j++) {
                        if (dice.get(i) == dice.get(j)) {
                            count++;
                            if (count == 4) {
                                result = this.constant + 4 * dice.get(i);
                                break;
                            }
                        }
                    }
                }
            }
            break;
            case STRAIGHT: {
                int count = 1;
                int minValue = Collections.min(dice);
                for (int i = 0; i < 5; i++) {
                    if (dice.contains(minValue + 1)) {
                        minValue++;
                        count++;
                        if (count == 5) {
                            result = this.constant + 5 * minValue - 10;
                            break;
                        }
                    }
                }
            }
            break;
            case FULL_HOUSE: {
                int firstValue = 0;
                boolean hasTriple = false;
                for (int i = 0; i < dice.size(); i++) {
                    int count = 1;
                    for (int j = i + 1; j < dice.size(); j++) {
                        if (dice.get(i) == dice.get(j)) {
                            count++;
                            if (count == 3) {
                                hasTriple = true;
                                firstValue = dice.get(i);
                            }
                        }
                    }
                }
                if (hasTriple) {
                    for (int i = 0; i < dice.size(); i++) {
                        for (int j = i + 1; j < dice.size(); j++) {
                            if (firstValue != dice.get(i) && dice.get(i) == dice.get(j)) {
                                result = this.constant + 3 * firstValue + 2 * dice.get(i);
                            }
                        }
                    }
                }
            }
            break;
            case TRIPLE: {
                for (int i = 0; i < dice.size(); i++) {
                    int count = 1;
                    for (int j = i + 1; j < dice.size(); j++) {
                        if (dice.get(i) == dice.get(j)) {
                            count++;
                            if (count == 3) {
                                result = this.constant + 3 * dice.get(i);
                                break;
                            }
                        }
                    }
                }
            }
            break;
            case DOUBLE_PAIR: {
                int firstValue = 0;
                boolean hasPair = false;
                for (int i = 0; i < dice.size(); i++) {
                    for (int j = i + 1; j < dice.size(); j++) {
                        if (dice.get(i) == dice.get(j)) {
                            hasPair = true;
                            firstValue = dice.get(i);
                        }
                    }
                }
                if (hasPair) {
                    for (int i = 0; i < dice.size(); i++) {
                        for (int j = i + 1; j < dice.size(); j++) {
                            if (firstValue != dice.get(i) && dice.get(i) == dice.get(j)) {
                                result = this.constant + 2 * firstValue + 2 * dice.get(i);
                            }
                        }
                    }
                }
            }
            break;
            case PAIR: {
                int maxPair = 0;
                for (int i = 0; i < dice.size(); i++) {
                    for (int j = i + 1; j < dice.size(); j++) {
                        if (dice.get(i) == dice.get(j)) {
                            if (maxPair < dice.get(i) * 2) {
                                maxPair = this.constant + dice.get(i) * 2;
                            }
                        }
                    }
                }
                result += maxPair;
            }
            break;
        }
        return result;
    }
}

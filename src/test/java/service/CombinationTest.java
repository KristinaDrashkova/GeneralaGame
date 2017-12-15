package service;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationTest {
    private List<Combination> combinations = Arrays.asList(Combination.values());

    @Test
    public void calculateShouldWorkCorrectlyWithGeneralaCombination() {
        List<Integer> dice = Arrays.asList(6, 6, 6, 6, 6);
        int maxResult = 0;
        for (Combination combination : combinations) {
            int result = combination.calculate(dice);
            if (result > maxResult) {
                maxResult = result;
            }
        }

        Assert.assertEquals(80, maxResult);
    }

    @Test
    public void calculateShouldWorkCorrectlyWithFourCombination() {
        List<Integer> dice = new ArrayList<>(Arrays.asList(6, 6, 6, 6, 5));
        int maxResult = 0;
        for (Combination combination : combinations) {
            int result = combination.calculate(dice);
            if (result > maxResult) {
                maxResult = result;
            }
        }

        Assert.assertEquals(64, maxResult);
    }

    @Test
    public void calculateShouldWorkCorrectlyWithStraightCombination() throws Exception {
        List<Integer> dice = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int maxResult = 0;
        for (Combination combination : combinations) {
            int result = combination.calculate(dice);
            if (result > maxResult) {
                maxResult = result;
            }
        }

        Assert.assertEquals(45, maxResult);
    }

    @Test
    public void calculateShouldWorkCorrectlyWithFullHouseCombination() {
        List<Integer> dice = new ArrayList<>(Arrays.asList(6, 6, 6, 5, 5));
        int maxResult = 0;
        for (Combination combination : combinations) {
            int result = combination.calculate(dice);
            if (result > maxResult) {
                maxResult = result;
            }
        }

        Assert.assertEquals(53, maxResult);
    }

    @Test
    public void calculateShouldWorkCorrectlyWithTripleCombination() {
        List<Integer> dice = new ArrayList<>(Arrays.asList(6, 6, 6, 1, 2));
        int maxResult = 0;
        for (Combination combination : combinations) {
            int result = combination.calculate(dice);
            if (result > maxResult) {
                maxResult = result;
            }
        }

        Assert.assertEquals(38, maxResult);
    }

    @Test
    public void calculateShouldWorkCorrectlyWithDoublePairCombination() {
        List<Integer> dice = new ArrayList<>(Arrays.asList(6, 6, 5, 5, 1));
        int maxResult = 0;
        for (Combination combination : combinations) {
            int result = combination.calculate(dice);
            if (result > maxResult) {
                maxResult = result;
            }
        }

        Assert.assertEquals(37, maxResult);
    }

    @Test
    public void calculateShouldWorkCorrectlyWithPairCombination() {
        List<Integer> dice = new ArrayList<>(Arrays.asList(6, 6, 1, 2, 3));
        int maxResult = 0;
        for (Combination combination : combinations) {
            int result = combination.calculate(dice);
            if (result > maxResult) {
                maxResult = result;
            }
        }

        Assert.assertEquals(22, maxResult);
    }
}
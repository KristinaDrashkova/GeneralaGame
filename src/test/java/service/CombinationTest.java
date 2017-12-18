package service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static service.Combination.*;

@RunWith(value = Parameterized.class)
public class CombinationTest {

    @Parameterized.Parameter(value = 0)
    public List<Integer> diceList;

    @Parameterized.Parameter(value = 1)
    public int expectedResult;

    @Parameterized.Parameter(value = 2)
    public Combination expectedCombination;

    @Parameterized.Parameters()
    public static Collection<Object[]> diceCombinationToResult() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(6, 6, 6, 6, 6), 5 * 6 + GENERALA.getConstant(), GENERALA},
                {Arrays.asList(6, 6, 6, 6, 5), 4 * 6 + FOUR.getConstant(), FOUR},
                {Arrays.asList(5, 6, 6, 6, 6), 4 * 6 + FOUR.getConstant(), FOUR},
                {Arrays.asList(6, 6, 5, 6, 6), 4 * 6 + FOUR.getConstant(), FOUR},
                {Arrays.asList(1, 5, 4, 3, 2), 1 + 2 + 3 + 4 + 5 + STRAIGHT.getConstant(), STRAIGHT},
                {Arrays.asList(2, 5, 3, 6, 4), 2 + 3 + 4 + 5 + 6 + STRAIGHT.getConstant(), STRAIGHT},
                {Arrays.asList(6, 6, 6, 5, 5), 3 * 6 + 2 * 5 + FULL_HOUSE.getConstant(), FULL_HOUSE},
                {Arrays.asList(1, 6, 1, 1, 6), 3 * 1 + 2 * 6 + FULL_HOUSE.getConstant(), FULL_HOUSE},
                {Arrays.asList(6, 6, 6, 4, 1), 3 * 6 + TRIPLE.getConstant(), TRIPLE},
                {Arrays.asList(2, 1, 2, 3, 1), 2 * 2 + 2 * 1 + DOUBLE_PAIR.getConstant(), DOUBLE_PAIR},
                {Arrays.asList(1, 2, 3, 3, 4), 2 * 3 + PAIR.getConstant(), PAIR},
        });
    }

    @Test
    public void testCombinationsCalculate() {
        Assert.assertEquals(expectedCombination.calculate(diceList), expectedResult);
    }
}

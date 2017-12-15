package service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(value = Parameterized.class)
public class CombinationTest {
    private List<Combination> combinations = Arrays.asList(Combination.values());

    @Parameterized.Parameter()
    public List<Integer> diceList;

    @Parameterized.Parameter(value = 1)
    public int expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(6, 6, 6, 6, 6), 80},
                {Arrays.asList(6, 6, 6, 6, 5), 64},
                {Arrays.asList(5, 6, 6, 6, 6), 64},
                {Arrays.asList(6, 6, 5, 6, 6), 64},
                {Arrays.asList(1, 5, 4, 3, 2), 45},
                {Arrays.asList(2, 5, 3, 6, 4), 50},
                {Arrays.asList(6, 6, 6, 5, 5), 53},
                {Arrays.asList(1, 6, 1, 1, 6), 40},
                {Arrays.asList(6, 6, 6, 4, 1), 38},
                {Arrays.asList(2, 1, 2, 3, 1), 21},
                {Arrays.asList(1, 2, 3, 3, 4), 16},
        });
    }

    @Test
    public void testCombinationsCalculate() {
        List<Integer> dice = diceList;
        int maxResult = 0;
        for (Combination combination : combinations) {
            int result = combination.calculate(dice);
            if (result > maxResult) {
                maxResult = result;
            }
        }

        Assert.assertEquals(expected, maxResult);
    }
}

package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DiceServiceImplTest {
    private DiceService diceService;

    @Before
    public void initialize() {
        diceService = new DiceServiceImpl();
    }

    @Test
    public void rollShouldWorkCorrectlyWithNormalData() throws Exception {
        List<Integer> rolls = diceService.roll(100);
        Assert.assertEquals(100, rolls.size());
        for (Integer roll : rolls) {
            Assert.assertTrue((roll >= 1) && (roll <= 6));
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void rollShouldThrowException() {
        List<Integer> rolls = diceService.roll(-1);
        Assert.assertEquals(0, rolls.size());
    }

    @Test(expected = OutOfMemoryError.class)
    public void rollShouldThrowErrorWithMaxValue() {
        List<Integer> rolls = diceService.roll(Integer.MAX_VALUE);
    }

    @Test
    public void rollNoArgumentsShouldWorkCorrectly() {
        List<Integer> rolls = diceService.roll();
        Assert.assertEquals(1 , rolls.size());
        Assert.assertTrue(rolls.get(0) >= 1 && rolls.get(0) <= 6);
    }
}

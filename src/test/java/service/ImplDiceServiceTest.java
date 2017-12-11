package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImplDiceServiceTest {
    private final static String INVALID_READER_PATH = "resources/file.txt";
    private DiceService diceService;

    @Before
    public void initialize() {
        diceService = new ImplDiceService();
    }

    @Test
    public void rollShouldWorkCorrectlyWithNormalData() throws Exception {
        List<Integer> rolls = diceService.roll(100);
        Assert.assertEquals(100, rolls.size());
        for (Integer roll : rolls) {
            Assert.assertTrue((roll >= 1) && (roll <= 6));
        }
    }

    @Test
    public void rollShouldDoNothingWithNegativeData() {
        List<Integer> rolls = diceService.roll(-1);
        Assert.assertEquals(0, rolls.size());
    }

    @Test(expected = OutOfMemoryError.class)
    public void rollShouldThrowErrorWithMaxValue() {
        List<Integer> rolls = diceService.roll(Integer.MAX_VALUE);
    }

    @Test
    public void getResultSumShouldWorkCorrectly() throws Exception {
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        int sum = diceService.getResultSum(list);
        Assert.assertEquals(6, sum);
        list = new ArrayList<Integer>();
        sum = diceService.getResultSum(list);
        Assert.assertEquals(0, sum);
        list = new ArrayList<Integer>(Arrays.asList(-1, -2, -3));
        sum = diceService.getResultSum(list);
        Assert.assertEquals(-6, sum);
    }

    @Test(expected = IOException.class)
    public void rollingDiceShouldThrowExceptionWithInvalidBufferedReader() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(INVALID_READER_PATH));
        diceService.rollingDiceIO(reader);
    }
}

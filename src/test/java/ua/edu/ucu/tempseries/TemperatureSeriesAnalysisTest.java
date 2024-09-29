package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.InputMismatchException;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {

    @Test(expected = InputMismatchException.class)
    public void testTemperatureBelowAbsoluteZero() {
        double[] temperatures = {100.0, -300.0, 25.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatures);
        
        seriesAnalysis.checkForEmptiness();
    }

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testStandardDeviation() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = 1.41421;
    
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, -1.0, 2.5, 0.0, 4.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;
        
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }
    
    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -1.0, 2.5, 0.0, 4.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double expResult = 4.2;
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMinWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            seriesAnalysis.min();
        });
        assertEquals("The set is empty!", thrown.getMessage());
    }
    
    @Test
    public void testMaxWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            seriesAnalysis.max();
        });
        assertEquals("The set is empty!", thrown.getMessage());
    }

   @Test
   public void testAverageWithOneElementArray() {
       double[] temperatureSeries = {-1.0};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
       double expResult = -1.0;

       double actualResult = seriesAnalysis.average();
       assertEquals(expResult, actualResult, 0.00001);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testAverageWithEmptyArray() {
       double[] temperatureSeries = {};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

       // expect exception here
       seriesAnalysis.average();
   }

   @Test
   public void testAverage() {
       double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
       double expResult = 1.0;

       double actualResult = seriesAnalysis.average();

       assertEquals(expResult, actualResult, 0.00001);
   }

   @Test
    public void testFindTempClosestToZero_WithMixedTemperatures() {
        double[] temperatureSeries = {-5.0, -1.0, 1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double expResult = 1.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_WithAllNegativeTemperatures() {
        double[] temperatureSeries = {-5.0, -10.0, -2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double expResult = -2.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_WithTwoMinusAddTemperatures() {
        double[] temperatureSeries = {5.0, -0.2, 10.0, 0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double expResult = 0.2;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_WithEquidistantTemperatures() {
        double[] temperatureSeries = {-2.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double expResult = 2.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_WithSingleTemperature() {
        double[] temperatureSeries = {-10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double expResult = -10.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_WithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertThrows(IllegalArgumentException.class, seriesAnalysis::findTempClosestToZero);
    }

    @Test
    public void testFindTempClosestToValue_WithPositiveValue() {
        double[] temperatureSeries = {-5.0, -1.0, 1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double tempValue = 1.5; // Value we want to find the closest temperature to.
        double expResult = 1.0; // Closest temperature to 1.5 is 1.0
        double actualResult = seriesAnalysis.findTempClosestToValue(tempValue);
        
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue_WithNegativeValue() {
        double[] temperatureSeries = {-5.0, -1.0, 1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double tempValue = -3.0; // Value we want to find the closest temperature to.
        double expResult = -5.0; // Closest temperature to -3.0 is -1.0
        double actualResult = seriesAnalysis.findTempClosestToValue(tempValue);
        
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue_WithZeroValue() {
        double[] temperatureSeries = {-5.0, -1.0, 1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double tempValue = 0.0; // Value we want to find the closest temperature to.
        double expResult = 1.0; // Closest temperature to 0.0 is 1.0
        double actualResult = seriesAnalysis.findTempClosestToValue(tempValue);
        
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue_WithEquidistantValues() {
        double[] temperatureSeries = {-1.0, -3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double tempValue = -2.0; // Value we want to find the closest temperature to.
        double expResult = -1.0; // Closest temperature to -2.0 is -1.0
        double actualResult = seriesAnalysis.findTempClosestToValue(tempValue);
        
        assertEquals(expResult, actualResult, 0.00001);
    }
        
    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {-5.0, 0.0, 5.0, 10.0, 15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double[] expectedResult = {-5.0, 0.0, 5.0};
        double[] actualResult = seriesAnalysis.findTempsLessThen(6.0);
        assertArrayEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {-5.0, 0.0, 5.0, 10.0, 15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double[] expectedResult = {5.0, 10.0, 15.0};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(5.0);
        assertArrayEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsInRange() {
        double[] temperatureSeries = {-5.0, 0.0, 5.0, 10.0, 15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double[] expectedResult = {0.0, 5.0, 10.0};
        double[] actualResult = seriesAnalysis.findTempsInRange(0.0, 10.0);
        assertArrayEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsInRangeWithNoResults() {
        double[] temperatureSeries = {-5.0, 0.0, 5.0, 10.0, 15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double[] actualResult = seriesAnalysis.findTempsInRange(20.0, 30.0);
        assertArrayEquals(new double[]{}, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsInRangeWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        seriesAnalysis.findTempsInRange(0.0, 10.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsInRangeWithNullArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        
        seriesAnalysis.findTempsInRange(0.0, 10.0);
    }

    @Test
    public void testFindTempsLessThenWithNegativeValue() {
        double[] temperatureSeries = {-5.0, 0.0, 5.0, 10.0, 15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double[] expectedResult = {-5.0, 0.0, 5.0, 10.0, 15.0};
        double[] actualResult = seriesAnalysis.findTempsLessThen(20.0);
        assertArrayEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThenWithNegativeValue() {
        double[] temperatureSeries = {-5.0, 0.0, 5.0, 10.0, 15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        double[] expectedResult = {-5.0, 0.0, 5.0, 10.0, 15.0};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(-5.0);
        assertArrayEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testReset() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        double[] ha = new double[0];
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TemperatureSeriesAnalysis n = new TemperatureSeriesAnalysis(ha);
        seriesAnalysis.reset();
        assertArrayEquals(seriesAnalysis.getTsa(), n.getTsa(), 0.000001);
    }


    //s

    @Test
    public void testSortTemps() {
        double[] initialTemperatures = {32.5, 30.0, 28.5, 35.0, 31.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(initialTemperatures);
        double[] expectedSortedTemps = {28.5, 30.0, 31.5, 32.5, 35.0};
        double[] actualSortedTemps = seriesAnalysis.sortTemps();

        assertArrayEquals(expectedSortedTemps, actualSortedTemps, 0.000001);
    }

    @Test
    public void testSortTempsWithNegativeValues() {
        double[] temperatures = {-5.0, -1.0, -3.0, 0.0, -2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatures);

        double[] expectedSortedTemps = {-5.0, -3.0, -2.0, -1.0, 0.0};
        double[] actualSortedTemps = seriesAnalysis.sortTemps();

        assertArrayEquals(expectedSortedTemps, actualSortedTemps, 0.000001);
    }

    @Test
    public void testSortTempsWithSingleElement() {
        double[] temperatures = {10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatures);

        double[] expectedSortedTemps = {10.0};
        double[] actualSortedTemps = seriesAnalysis.sortTemps();

        assertArrayEquals(expectedSortedTemps, actualSortedTemps, 0.000001);
    }

    @Test
    public void testSortTempsWithEmptyArray() {
        double[] temperatures = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatures);

        double[] expectedSortedTemps = {};
        double[] actualSortedTemps = seriesAnalysis.sortTemps();

        assertArrayEquals(expectedSortedTemps, actualSortedTemps, 0.000001);
    }

    @Test
    public void testSortTempsWithIdenticalValues() {
        double[] temperatures = {20.0, 20.0, 20.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatures);

        double[] expectedSortedTemps = {20.0, 20.0, 20.0};
        double[] actualSortedTemps = seriesAnalysis.sortTemps();

        assertArrayEquals(expectedSortedTemps, actualSortedTemps, 0.000001);
    }

    @Test
    public void testTempSummaryStatisticsInitialization() {
        double avg = 25.5;
        double dev = 5.0;
        double min = 20.0;
        double max = 30.0;

        TempSummaryStatistics stats = new TempSummaryStatistics(avg, dev, min, max);

        assertEquals(avg, stats.getAvgTemp(), 0.00001);
        assertEquals(dev, stats.getDevTemp(), 0.00001);
        assertEquals(min, stats.getMinTemp(), 0.00001);
        assertEquals(max, stats.getMaxTemp(), 0.00001);
    }

    @Test
    public void testGettersReturnCorrectValues() {
        TempSummaryStatistics stats = new TempSummaryStatistics(10.0, 2.0, 5.0, 15.0);

        assertEquals(10.0, stats.getAvgTemp(), 0.00001);
        assertEquals(2.0, stats.getDevTemp(), 0.00001);
        assertEquals(5.0, stats.getMinTemp(), 0.00001);
        assertEquals(15.0, stats.getMaxTemp(), 0.00001);
    }
 
    @Test(expected = IllegalArgumentException.class) 
    public void testSummaryStatisticsWithEmptyArray() { 
        // Given an empty temperature series 
        double[] temperatureSeries = {}; 
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries); 
 
        // Expect exception here 
        seriesAnalysis.summaryStatistics(); 
    }

    //s
    @Test
    public void testAddTempsIncreasesSize() {
        double[] initialTemps = {30.0, 25.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(initialTemps);

        int newCount = seriesAnalysis.addTemps(28.0, 29.0);

        // After adding 2 new temperatures, the total count should be 4
        assertEquals(4, newCount);
    }

    @Test
    public void testAddTempsResizesArray() {
        // Create an initial array with limited size
        double[] initialTemps = {30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(initialTemps);

        // Add temperatures that will require resizing
        int newCount = seriesAnalysis.addTemps(28.0, 29.0, 32.0, 35.0);

        // Ensure that the total count reflects the added temperatures
        assertEquals(5, newCount);
    }

    @Test
    public void testAddTempsWithNoInitialTemps() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(new double[0]);
        int newCount = seriesAnalysis.addTemps(28.0, 29.0);
        assertEquals(2, newCount);
    }

    @Test
    public void testAddTempsWithSingleTemperature() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(new double[]{20.0});
        int newCount = seriesAnalysis.addTemps(22.0);
        assertEquals(2, newCount);
    }

    @Test
    public void testAddTempsMultipleTimes() {
        double[] initialTemps = {30.0, 25.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(initialTemps);

        int countAfterFirstAdd = seriesAnalysis.addTemps(28.0, 29.0);
        int countAfterSecondAdd = seriesAnalysis.addTemps(32.0);

        assertEquals(4, countAfterFirstAdd);
        assertEquals(5, countAfterSecondAdd);
    }
}

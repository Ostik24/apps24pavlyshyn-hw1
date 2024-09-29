package ua.edu.ucu.apps.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final double LOWEST_POSSIBLE = -273.0;
    private double[] tsa;
    private int count;

    public TemperatureSeriesAnalysis() {
        this.tsa = null;
        this.count = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.tsa = temperatureSeries;
        this.count = tsa.length;
        for (int i = 0; i < tsa.length; i++) {
            if (tsa[i] < LOWEST_POSSIBLE) {
                throw new InputMismatchException("The temperature is too low.");
            }
        }
    }

    public void checkForEmptiness() {
        if (tsa == null || tsa.length == 0) {
            throw new IllegalArgumentException("The set is empty!");
        }
    }

    public double average() {
        checkForEmptiness();
        int size = tsa.length;
        int sum = 0; 
        for (int i = 0; i < size; i++) {
            sum += tsa[i];
        }
        double result = sum/size;
        return result;
    }

    public double deviation() {
        checkForEmptiness();
        double avrg = average();
        int size = tsa.length;
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum = sum + (tsa[i] - avrg)*(tsa[i] - avrg);
        }
        
        return Math.sqrt(sum/size);
    }

    public double min() {
        checkForEmptiness();
        int size = tsa.length;
        double res = Double.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (tsa[i] < res) {
                res = tsa[i];
            }
        }
        return res;
    }

    public double max() {
        checkForEmptiness();
        int size = tsa.length;
        double res = Double.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (tsa[i] > res) {
                res = tsa[i];
            }
        }
        return res;
    }

    public double findTempClosestToZero() {
        checkForEmptiness();
        int size = tsa.length;
        double res = tsa[0];
        for (int i = 0; i < size; i++) {
            if ((Math.abs(tsa[i]) < Math.abs(res)) || 
            (Math.abs(tsa[i]) == Math.abs(res) && tsa[i] > res)) {
                res = tsa[i];
            }
        }

        return res;
    }

    public double findTempClosestToValue(double tempValue) {
        checkForEmptiness();
        if (tempValue == 0) {
            return findTempClosestToZero();
        }
        int size = tsa.length;
        double res = Double.MAX_VALUE;
        double element = 0;
        for (int i = 0; i < size; i++) {
            if (Math.abs(tempValue - tsa[i]) < res) {
                res = Math.abs(tempValue - tsa[i]);
                element = tsa[i];
            }
        }

        return element;
    }

    public double[] findTempsLessThen(double tempValue) {
        checkForEmptiness();
        return findTempsInRange(-Double.MAX_VALUE, tempValue);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        checkForEmptiness();
        return findTempsInRange(tempValue, Double.MAX_VALUE);
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        checkForEmptiness();
        int size = tsa.length;
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (tsa[i] >= lowerBound && tsa[i] <= upperBound) {
                j++;
            }
        }

        double[] arr = new double[j];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (tsa[i] >= lowerBound && tsa[i] <= upperBound) {
                arr[k] = tsa[i];
                k++;
            }
        }
        return arr;
    }

    public void reset() {
        tsa = new double[0];
    }

    public double[] sortTemps() {
        int n = tsa.length; 
        for (int i = 1; i < n; ++i) { 
            double key = tsa[i]; 
            int j = i - 1; 

            while (j >= 0 && tsa[j] > key) { 
                tsa[j + 1] = tsa[j]; 
                j = j - 1; 
            } 
            tsa[j + 1] = key; 
        } 
        return tsa;
    }

    public TempSummaryStatistics summaryStatistics() {
        double avg = average(); 
        double dev = deviation(); 
        double min = min(); 
        double max = max(); 
        return new TempSummaryStatistics(avg, dev, min, max);
    }

    public int addTemps(double... temps) {
        if (count + temps.length > tsa.length) {
            int newSize = Math.max(count + temps.length, tsa.length * 2);
            double[] newTsa = new double[newSize];

            for (int i = 0; i < count; i++) {
                newTsa[i] = tsa[i];
            }
            tsa = newTsa;
        }

        for (double temp : temps) {
            tsa[count] = temp;
            count++;
        }

        return count;
    }

    public double[] getTsa() {
        return tsa;
    }
}

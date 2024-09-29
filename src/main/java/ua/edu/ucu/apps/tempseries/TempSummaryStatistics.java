package ua.edu.ucu.apps.tempseries;

public class TempSummaryStatistics { 
    private final double avgTemp; 
    private final double devTemp; 
    private final double minTemp; 
    private final double maxTemp; 
 
    public TempSummaryStatistics(double avg, double dev, double min, double max) { 
        this.avgTemp = avg; 
        this.devTemp = dev; 
        this.minTemp = min; 
        this.maxTemp = max; 
    } 
 
    public double getAvgTemp() { 
        return avgTemp; 
    } 
 
    public double getDevTemp() { 
        return devTemp; 
    } 
 
    public double getMinTemp() { 
        return minTemp; 
    } 
 
    public double getMaxTemp() { 
        return maxTemp; 
    } 
}
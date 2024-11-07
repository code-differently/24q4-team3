package Organize.Closet;

import java.util.ArrayList;
import java.util.List;

public class Outfit implements Weather {
    private BottomItems bottomItem;

    private double temperature; 
    private String condition;    
    private int humidity;       
    private String season;
    
    public Outfit(BottomItems bottomItem, double temperature, String condition, int humidity, String season) {
        this.bottomItem = bottomItem;
        this.temperature = temperature;
        this.condition = condition;
        this.humidity = humidity;
        this.season = season;
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public int getHumidity() {
        return humidity;
    }

    @Override
    public String getSeason() {
        return season;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
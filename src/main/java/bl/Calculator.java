package bl;

import enums.FuelType;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by dominik on 20.11.2014.
 */
public class Calculator {
    private double CO2_Consumption_Diesel;
    private double CO2_Consumption_Petrol;

    public double getCO2_Consumption_Diesel() {
        return CO2_Consumption_Diesel;
    }

    public void setCO2_Consumption_Diesel(double CO2_Consumption_Diesel) {
        this.CO2_Consumption_Diesel = CO2_Consumption_Diesel;
    }

    public double getCO2_Consumption_Petrol() {
        return CO2_Consumption_Petrol;
    }

    public void setCO2_Consumption_Petrol(double CO2_Consumption_Petrol) {
        this.CO2_Consumption_Petrol = CO2_Consumption_Petrol;
    }

    public double calculateCo2ConsumptionBasedOnDistance(Route route, Vehicle vehicle)  {
        double distance = route.getDistance();
        double factor;
        double co2Consumption=0;

        switch(route.getTypeOfRoute()) {
            case HIGHWAY:factor = 1; break;
            case COUNTRYROAD:factor = 1.2; break;
            case GRAVELROAD:factor = 2; break;
        }

        switch (vehicle.getTypeOfFuel()) {
            case PATROL: co2Consumption=0.0265; break;
            case DIESEL: co2Consumption=0.0236; break;
        }

        return distance*co2Consumption;
    }

    public void calculateTotalCostOfRoute(Route route, Vehicle vehicle, String dayOfTheWeek) {

    }


}

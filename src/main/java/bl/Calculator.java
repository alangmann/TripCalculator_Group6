package bl;

import enums.FuelType;
import enums.RouteType;
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

    public double calculateCo2Consumption(Route route, Vehicle vehicle) throws IllegalArgumentException  {
        if (route.getDistance() < 0) throw new IllegalArgumentException("Wrong Distance!");

        double distance = route.getDistance();
        double co2Consumption=1;
        double slope=route.getSlope();
        double factoofroutetype=1;
        switch(route.getTypeOfRoute())
        {
            case HIGHWAY:
                break;
            case COUNTRYROAD: factoofroutetype=1.2; break;
            case GRAVELROAD: factoofroutetype=2;
                break;
        }
        if(slope<-5)
        {
            return 0;
        }
        if(slope==0)
            slope=1;

        switch (vehicle.getTypeOfFuel()) {
            case PATROL: co2Consumption=0.0265; break;
            case DIESEL: co2Consumption=0.0236; break;
        }

        return distance*co2Consumption*slope*factoofroutetype;
    }

    public void calculateTotalCostOfRoute(Route route, Vehicle vehicle, String dayOfTheWeek) {

    }


}

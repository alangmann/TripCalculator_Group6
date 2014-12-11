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
        else if (route == null) throw new IllegalArgumentException("Route is null!");
        else if (vehicle == null) throw new IllegalArgumentException("Vehicle is null");

        double distance = route.getDistance();
        double co2Consumption=1;
        double slope=route.getSlope();
        double factorOfRouteType = 1;

        switch (route.getTypeOfRoute()) {
            case HIGHWAY:
                break;
            case COUNTRYROAD:
                factorOfRouteType = 1.2;
                break;
            case GRAVELROAD:
                factorOfRouteType = 2;
                break;
        }

        if (vehicle instanceof Car) {
            if (slope < -5) {
                return 0;
            }
            if (slope == 0) slope = 1;

            switch (vehicle.getTypeOfFuel()) {
                case PATROL:
                    co2Consumption = 0.0265;
                    break;
                case DIESEL:
                    co2Consumption = 0.0236;
                    break;
            }

            double consumption = distance * co2Consumption * slope * factorOfRouteType;
            return consumption;
        }

        else if(vehicle instanceof Truck) {
            Truck truck = (Truck)vehicle;
            switch (truck.getTypeOfFuel()) {
                case PATROL: co2Consumption=0.0265*0.1; break;
                case DIESEL: co2Consumption=0.0236*0.1; break;
            }

            double consumption= distance*co2Consumption*slope*factorOfRouteType;
            if(truck.isAdBlue())
                consumption-=(consumption*0.07);
            return consumption;
        }
        else return -1;
    }

    public void calculateTotalCostOfRoute(Route route, Vehicle vehicle, String dayOfTheWeek) {

    }


}

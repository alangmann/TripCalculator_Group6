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

    public void setCO2_Consumption_Diesel(double CO2_Consumption_Diesel) throws IllegalArgumentException {
        if (CO2_Consumption_Diesel < 0) throw new IllegalArgumentException("Consumption < 0!");
        this.CO2_Consumption_Diesel = CO2_Consumption_Diesel;
    }

    public double getCO2_Consumption_Petrol() {
        return CO2_Consumption_Petrol;
}

    public void setCO2_Consumption_Petrol(double CO2_Consumption_Petrol) throws IllegalArgumentException{
        if (CO2_Consumption_Petrol < 0) throw new IllegalArgumentException("Consumption < 0!");
        this.CO2_Consumption_Petrol = CO2_Consumption_Petrol;
    }

    public double calculateCo2Consumption(Route route, Vehicle vehicle) throws IllegalArgumentException  {
        if (route.getDistance() < 0) throw new IllegalArgumentException("Wrong Distance!");
        else if (route == null) throw new IllegalArgumentException("Route is null!");
        else if (vehicle == null) throw new IllegalArgumentException("Vehicle is null");

        double distance = route.getDistance();
        double co2Consumption=1;
        double slope=(route.getSlope()/100) / 100 + 1;
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
        if (slope < -5) {
            return 0;
        }
        if (slope == 0) slope = 1;

        switch (vehicle.getTypeOfFuel()) {
            case PATROL:
                co2Consumption = 0.0236;
                break;
            case DIESEL:
                co2Consumption = 0.0265;
                break;
        }

        if (vehicle instanceof Car) {
            co2Consumption =  co2Consumption*vehicle.getAverageConsumption();

            double consumption =  distance * co2Consumption * slope * factorOfRouteType;
            int i=1;
            if((i=vehicle.getCargo()%100)>0)
                consumption=consumption +(0.5*i);
            return consumption;
        }

        else if(vehicle instanceof Truck) {
            Truck truck = (Truck)vehicle;

            double consumption= distance*co2Consumption*slope*factorOfRouteType;
            if(truck.isAdBlue())
                consumption-=(consumption*0.07);
            int i=1;
            if((i=vehicle.getCargo()%100)>0)
                consumption=consumption +(0.05*i);
            return consumption;
        }
        else return -1;
    }

    public void calculateTotalCostOfRoute(Route route, Vehicle vehicle, String dayOfTheWeek) {

    }


}

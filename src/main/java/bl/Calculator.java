package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by dominik on 20.11.2014.
 */
public class Calculator {
    public double CO2_Consumption_Diesel;
    public double CO2_Consumption_Petrol;


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

    public void caculateCo2Consumption(Route route, Vehicle vehicle) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(new File("sdf.txt")));
    }

    public void calculateTotalCostOfRoute(Route route, Vehicle vehicle, String dayOfTheWeek) {

    }
}

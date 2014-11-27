package bl;

import java.io.*;
import java.util.LinkedList;

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

    public void caculateCo2ConsumptionBasedOnDistance(Route route, Vehicle vehicle)  {
        double distance = route.getDistance();
        double factor;

        switch(route.getTypeOfRoute()) {
            case HIGHWAY:factor = 1; break;
            case COUNTRYROAD:factor = 1.2; break;
            case GRAVELROAD:factor = 2; break;
        }
    }

    public void calculateTotalCostOfRoute(Route route, Vehicle vehicle, String dayOfTheWeek) {

    }

    public void loadSpecifications() throws IOException
    {
        String pfad = System.getProperty("user.dir") + File.separator
                + "files" + File.separator +"route_specifications.csv";
        BufferedReader br = new BufferedReader(new FileReader(new File(pfad)));
        String line="";
        LinkedList specs = new LinkedList();
        try
        {
            while((line=br.readLine())!=null)
            {
                specs.add(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

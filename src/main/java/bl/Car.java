package bl;

import enums.FuelType;
import org.springframework.stereotype.Repository;

/**
 * Created by Jule on 13.11.2014.
 */
public class Car extends Vehicle
{
    public Car(double averageConsumption, int cargo, FuelType typeOfFuel) {
        super(averageConsumption, cargo, typeOfFuel);
    }

}

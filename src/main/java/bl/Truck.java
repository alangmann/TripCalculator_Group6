package bl;

import enums.FuelType;

/**
 * Created by Jule on 13.11.2014.
 */
public class Truck extends Vehicle
{
    private int axles;
    private boolean adBlue;

    public Truck(double averageConsumption, int cargo, FuelType typeOfFuel) {
        super(averageConsumption, cargo, typeOfFuel);
    }

    public Truck(double averageConsumption, int cargo, FuelType typeOfFuel, int ax, boolean adBlue) {
        this(averageConsumption, cargo, typeOfFuel);
        this.adBlue = adBlue;
        this.axles = ax;
    }
}

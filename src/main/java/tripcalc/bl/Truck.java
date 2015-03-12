package tripcalc.bl;

import tripcalc.enums.FuelType;

/**
 * Created by Jule on 13.11.2014.
 */
public class Truck extends Vehicle
{
    private int axles;
    private boolean adBlue;

    public boolean isAdBlue() {
        return adBlue;
    }

    public void setAdBlue(boolean adBlue) {
        this.adBlue = adBlue;
    }

    public int getAxles() {

        return axles;
    }

    public void setAxles(int axles) {
        this.axles = axles;
    }

    public Truck(double averageConsumption, int cargo, FuelType typeOfFuel) {
        super(averageConsumption, cargo, typeOfFuel);
    }

    public Truck(double averageConsumption, int cargo, FuelType typeOfFuel, int ax, boolean adBlue) {
        this(averageConsumption, cargo, typeOfFuel);
        this.adBlue = adBlue;
        this.axles = ax;
    }
}

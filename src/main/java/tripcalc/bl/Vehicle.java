package tripcalc.bl;

import tripcalc.enums.FuelType;

/**
 * Created by Jule on 13.11.2014.
 */
public class Vehicle
{
    private double averageConsumption;
    private int cargo;
    private FuelType typeOfFuel;

    public Vehicle(double averageConsumption, int cargo, FuelType typeOfFuel) {
        this.averageConsumption = averageConsumption;
        this.cargo = cargo;
        this.typeOfFuel = typeOfFuel;
    }

    public double getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public FuelType getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(FuelType typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }
}

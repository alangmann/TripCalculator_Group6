
import static org.hamcrest.CoreMatchers.equalTo;

import bl.*;
import enums.DayOfTheWeek;
import enums.FuelType;
import enums.RouteType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TripCalculatorTest {

    Calculator cal = new Calculator();

    @Test (expected = IllegalArgumentException.class)
    public void testIfCalculateRouteWithDistanceMinusOneReturnsError() {
        cal.calculateCo2Consumption(new Route(-1, 0, 0, RouteType.HIGHWAY), new Vehicle(6, 1, FuelType.DIESEL));
    }

    @Test
    public void testIfDieselWithConsumption5l100kmonAMotorwayWithASlope5mon10KmReturns1Komma3256625() {
        Route route = new Route(10, 5, 1, RouteType.HIGHWAY);
        Car car = new Car(5, 0, FuelType.DIESEL);
        assertThat((float)this.cal.calculateCo2Consumption(route, car), equalTo(1.3256625f));
    }

    @Test
    public void testIfPatrolWith20kmReturns0Point53WhenSlopeIs1Return1Point0192519199999999() {
        // km x CO2 x slope x Factor of Route type
        Car car = new Car(1.1, 1, FuelType.PATROL);
        Route route = new Route(20, 1, 0, RouteType.HIGHWAY);
        assertThat(this.cal.calculateCo2Consumption(route, car), equalTo(1.0192519199999999));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIfCarWithConsumptionSmaller0ReturnError() {
        Car car = new Car(-1.1, 1, FuelType.PATROL);
        Route route = new Route(20, 1, 0, RouteType.HIGHWAY);
        this.cal.setCO2_Consumption_Diesel(car.getAverageConsumption());
    }

    @Test
    public void testIfAverageConsumptionCanSetTo12() {
        Car car = new Car(1.1, 1, FuelType.PATROL);
        this.cal.setCO2_Consumption_Diesel(car.getAverageConsumption());
        assertThat(this.cal.getCO2_Consumption_Diesel(), equalTo(car.getAverageConsumption()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIfCarWithPetrolConsumptionSmallerZeroReturnsError() {
        Car car = new Car(-1.1, 1, FuelType.PATROL);
        this.cal.setCO2_Consumption_Petrol(car.getAverageConsumption());
    }

    @Test
    public void testIfAverageConsumptionCanBeSet() {
        Car car = new Car(1.1, 1, FuelType.PATROL);
        this.cal.setCO2_Consumption_Petrol(car.getAverageConsumption());
        assertThat(this.cal.getCO2_Consumption_Petrol(), equalTo(car.getAverageConsumption()));
    }

    @Test
    public void testIfCarOnGravelRoadWithConsumption2Returns2Point3881888() {
        Car car = new Car(2, 1, FuelType.PATROL);
        Route route = new Route(20, 1, 0, RouteType.GRAVELROAD);
        assertThat(this.cal.calculateCo2Consumption(route, car), equalTo(2.3881888));
    }

    @Test
    public void testIfCarOnCountryRoadWithConsumption2Returns1Point63291328() {
        Car car = new Car(2, 1, FuelType.PATROL);
        Route route = new Route(20, 1, 0, RouteType.COUNTRYROAD);
        assertThat(this.cal.calculateCo2Consumption(route, car), equalTo(1.63291328));
    }

    @Test
    public void testIfCarOnCountryRoadWithSlopeSmallerMinus5ReturnsZero() {
        Car car = new Car(2, 1, FuelType.PATROL);
        Route route = new Route(20, -6, 0, RouteType.COUNTRYROAD);
        assertThat(this.cal.calculateCo2Consumption(route, car), equalTo(0.0));
    }

    @Test
    public void testTruckWithAdBlue()
    {
        Truck truck = new Truck(1.1,1, FuelType.PATROL,4,true);
        Route route = new Route(20, 1, 0, RouteType.HIGHWAY);
        assertThat(this.cal.calculateCo2Consumption(route, truck), equalTo(0.48900389599999994));
    }

    @Test
    public void testTruckWithoutAdBlue() {
        Truck truck = new Truck(1.1, 1, FuelType.PATROL, 4, false);
        Route route = new Route(20, 1, 0, RouteType.HIGHWAY);
        assertThat(this.cal.calculateCo2Consumption(route, truck), equalTo(0.5220471999999999));
    }

    @Test
    public void testIfTruck10km45AvConsumption5EuroSpecialFeesReturns31Point26() throws IOException {
        Truck truck = new Truck(45, 20000, FuelType.DIESEL);
        Route route = new Route(10, 1, 5, RouteType.HIGHWAY);
        Price p = new Price(DayOfTheWeek.Thursday,1.315 , 1.315);
        assertThat(this.cal.calculateTotalCostOfRoute(route, truck, DayOfTheWeek.Monday, prices), equalTo(31.26));
    }


}
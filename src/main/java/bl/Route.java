package bl;

import enums.RouteType;
import org.springframework.stereotype.Repository;

/**
 * Created by Jule on 13.11.2014.
 */

public class Route
{
    private double distance;
    private double slope;
    private double specialFee;
    private RouteType typeOfRoute;

    public Route(double distance, double slope, double specialFee, RouteType typeOfRoute) {
        this.distance = distance;
        this.slope = slope;
        this.specialFee = specialFee;
        this.typeOfRoute = typeOfRoute;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getSpecialFee() {
        return specialFee;
    }

    public void setSpecialFee(double specialFee) {
        this.specialFee = specialFee;
    }

    public RouteType getTypeOfRoute() {
        return typeOfRoute;
    }

    public void setTypeOfRoute(RouteType typeOfRoute) {
        this.typeOfRoute = typeOfRoute;
    }

    @Override
    public String toString()
    {
        return String.format("Distance: %f, Slope: %f, Special Fee: %f, Type: %s", distance, slope, specialFee, typeOfRoute);
    }
}

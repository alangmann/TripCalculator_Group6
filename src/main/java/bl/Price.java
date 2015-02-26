package bl;

import enums.DayOfTheWeek;
import org.springframework.stereotype.Repository;

/**
 * Created by dominik on 18.12.2014.
 */
public class Price {
    private DayOfTheWeek day;
    private double m_PetrolPrice;
    private double m_DieselPrice;

    public Price(DayOfTheWeek day, double m_PetrolPrice, double m_DieselPrice) {
        this.day = day;
        this.m_PetrolPrice = m_PetrolPrice;
        this.m_DieselPrice = m_DieselPrice;
    }

    public DayOfTheWeek getDay() {
        return day;
    }

    public void setDay(DayOfTheWeek day) {
        this.day = day;
    }

    public double getM_PetrolPrice() {
        return m_PetrolPrice;
    }

    public void setM_PetrolPrice(double m_PetrolPrice) {
        this.m_PetrolPrice = m_PetrolPrice;
    }

    public double getM_DieselPrice() {
        return m_DieselPrice;
    }

    public void setM_DieselPrice(double m_DieselPrice) {
        this.m_DieselPrice = m_DieselPrice;
    }

    @Override
    public String toString() {
        return String.format("Day: " + this.day + ", Petrol Price: " + this.m_PetrolPrice + ", Diesel Price: " + this.m_DieselPrice);
    }
}

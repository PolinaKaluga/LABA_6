package collection;


import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс - координаты объекта класса LabWork
 */
public class Coordinates implements Serializable {
    private Float x;
    private float y;

    public Coordinates(Float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(float coordinateX, float coordinateY, LocalDate now, int price, Person person) {
    }

    public Float getX() {
        return x;
    }



    public float getY() {
        return y;
    }

    public void setX(Float x)  {
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }
    @Override
    public String toString() {
        return "coordinates (x, y) = (" + getX() + ", " + getY() + ")";
    }
}
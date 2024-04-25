package collection;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;



/**
 * Класс объектов(значений) коллекции
 */

public class Ticket implements Serializable {
    private static int uniqueId=1;
    private long id;
    private String name;
    private Coordinates coordinates;

    private ZonedDateTime creationDate;
    private int price;
    private TicketType type;
    private Person person;

    public Ticket(String name, Coordinates coordinates, ZonedDateTime creationDate,
                  int price, TicketType type, Person person) {
        this.id = uniqueId++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.type = type;
        this.person = person;
    }
    public static int getUniqueId() {
        return uniqueId;
    }

    public static void setUniqueId(int uniqueId) {
        Ticket.uniqueId = uniqueId;
    }

    public int getId() {

        return (int) id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setCoordinateX(Float x) {
        this.getCoordinates().setX(x);
    }

    public Coordinates getCoordinates() {
        return null;
    }

    public void setCoordinateY(float y) {
        this.getCoordinates().setY(y);
    }

    public String getCreationDate(){
        return ""+creationDate;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {

        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", type=" + type +
                ", person=" + person +
                '}';
    }


    public String getFormattedCreationDate() {
        //2017-12-03T10:15:30+01:00
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(pattern);
        return creationDate.plusHours(3).format(europeanDateFormatter);
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }


}

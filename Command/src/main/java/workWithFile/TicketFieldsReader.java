package workWithFile;

import collection.*;
import exceptions.ValidValuesRangeException;
import io.UserIO;


import java.time.Instant;
import java.time.ZonedDateTime;


/**
 * Класс, использующийся для заполнения полей нового объекта класса LabWork
 */
public class TicketFieldsReader {
    private UserIO userIO;
    /**
     * Конструктор класса, который присваивает в поле userIO значение, переданное в конструкторе в качестве параметра
     *
     * @param userIO хранит ссылку на объект типа UserIO
     */
    public TicketFieldsReader(UserIO userIO) {
        this.userIO = userIO;
    }

    /**
     * Метод, выводящий производящий чтение данных из консоли. Запрашивает ввод полей в строго определенном порядке.
     *
     * @param id уникальный идентификатор объекта класса LabWork, который должен быть записан в качестве ключа в коллекцию
     * @return возращает объект типа LabWork
     */
    public Ticket read(Integer id) {
        String i = Instant.now().toString();
        return new Ticket(readName(), readCoordinates(),ZonedDateTime.parse(i).plusHours(3), readPrice(), readType(),readPerson());
    }
    /**
     * Метод, выводящий производящий чтение данных из консоли. Запрашивает ввод полей в строго определенном порядке.
     *
     * @return возращает объект типа LabWork
     */
    public Ticket read( ) {
        String i = Instant.now().toString();
        return new Ticket(readName(), readCoordinates(),ZonedDateTime.parse(i).plusHours(3), readPrice(),readType(),readPerson());
    }
    /**
     * Метод, производящий чтение поля name типа String объекта LabWork из потока, указанного в поле userIO. При некорректном вводе просит ввести поля заново.
     *
     * @return значение поля name, уже проверенное на недопустимую ОДЗ.
     */
    public String readName(){
        String str;
        while(true){
            userIO.printCommandText("name (not null): ");
            str= userIO.readLine().trim();
            if(str.equals("") || str==null) userIO.printCommandError("\nЗначение поля не может быть null или пустой строкой\n");
            else return str;
        }
    }

    /**
     * Метод, производящий чтение координат x, y.
     *
     * @return возвращает объект типа Coordinates.
     */
    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    /**
     * Метод, производящий чтение поля x типа Double объекта Coordinates из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля x, уже проверенное на недопустимую ОДЗ.
     */
    public Float readCoordinateX() {
        Float x;
        while (true) {
            try {

                userIO.printCommandText("coordinate_x (Long & x > -985): ");
                String str=userIO.readLine().trim();
                if(str.equals("") || str==null) x= Float.valueOf(0);
                else {
                    x = Float.parseFloat(str);
                    if (x <= -985) throw new ValidValuesRangeException();
                    else return x;
                }
            } catch (ValidValuesRangeException ex) {
                System.out.println("Координата x должна быть больше -985");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Long");
            }
        }
    }
    /**
     * Метод, производящий чтение поля y типа int объекта Coordinates из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля y, уже проверенное на недопустимую ОДЗ.
     */
    public float readCoordinateY() {
        float y;
        while (true) {
            try {
                userIO.printCommandText("coordinate_y (Double): ");
                String str = userIO.readLine().trim();
                if (str.equals("") || str == null) y = 0;
                else {
                    y = Float.parseFloat(str);
                }
                return y;
            }catch(NumberFormatException ex){
                System.err.println("Число должно быть типа Double");
            }
        }
    }


    public int readPrice(){
        int price;
        while (true) {
            try {
                userIO.printCommandText("price (Float && >0): ");
                String str = userIO.readLine().trim();
                if (str.equals("") || str==null) price = 0;
                else {
                    price = Integer.parseInt(str);
                    if (price <=0) throw new ValidValuesRangeException();
                }
                return price;
            }catch (ValidValuesRangeException ex) {
                System.out.println("price должен быть больше 0");
            }catch (NumberFormatException ex) {
                System.err.println("Вводимое значение должно быть Int");
            }
        }
    }

    public TicketType readType(){
        TicketType ticketType;
        while (true) {
            try {
                userIO.printCommandText("Допустимые значения ticketType :\n");
                for (TicketType val : TicketType.values()) {
                    userIO.printCommandText(val.name() + "\n");
                }
                userIO.printCommandText("ticketType: ");
                ticketType = TicketType.valueOf(userIO.readLine().toUpperCase().trim());
                return ticketType;
            } catch (IllegalArgumentException ex) {
                System.err.println("Значение введенной константы не представлено в перечислении  ticketType");
            }
        }
    }

    public Long readPersonHeight() {
        Long height;
        while (true) {
            try {
                userIO.printCommandText("height (Long && >0): ");
                String str = userIO.readLine().trim();
                if (str.equals("") || str==null) height = Long.valueOf(0);
                else {
                    height = Long.parseLong(str);
                    if (height <=0) throw new ValidValuesRangeException();
                }
                return height;
            }catch (ValidValuesRangeException ex) {
                System.out.println("height должен быть больше 0");
            }catch (NumberFormatException ex) {
                System.err.println("Вводимое значение должно быть Float");
            }
        }
    }

    public Float readPersonWeight() {
        float weight;
        while (true) {
            try {
                userIO.printCommandText("weight (Float && >0): ");
                String str = userIO.readLine().trim();
                if (str.equals("") || str==null) weight = 0;
                else {
                    weight = Float.parseFloat(str);
                    if (weight <=0) throw new ValidValuesRangeException();
                }
                return weight;
            }catch (ValidValuesRangeException ex) {
                System.out.println("weight должен быть больше 0");
            }catch (NumberFormatException ex) {
                System.err.println("Вводимое значение должно быть Float");
            }
        }
    }

    public EyeColor readEyeColor(){
        EyeColor eyeColor;
        while (true) {
            try {
                userIO.printCommandText("Допустимые значения eyeColor :\n");
                for (EyeColor val : EyeColor.values()) {
                    userIO.printCommandText(val.name() + "\n");
                }
                userIO.printCommandText("eyeColor: ");
                eyeColor = EyeColor.valueOf(userIO.readLine().toUpperCase().trim());
                return eyeColor;
            } catch (IllegalArgumentException ex) {
                System.err.println("Значение введенной константы не представлено в перечислении eyeColor");
            }
        }
    }

    public HairColor readHairColor(){
        HairColor hairColor;
        while (true) {
            try {
                userIO.printCommandText("Допустимые значения hairColor :\n");
                for (HairColor val : HairColor.values()) {
                    userIO.printCommandText(val.name() + "\n");
                }
                userIO.printCommandText("hairColor: ");
                hairColor = HairColor.valueOf(userIO.readLine().toUpperCase().trim());
                return hairColor;
            } catch (IllegalArgumentException ex) {
                System.err.println("Значение введенной константы не представлено в перечислении hairColor");
            }
        }
    }


    public Person readPerson(){
        return new Person(readPersonHeight(),readPersonWeight(), readEyeColor(), readHairColor());
    }




}


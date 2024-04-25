package collection;


import workWithFile.FileManager;
import workWithFile.XmlParser;

import java.io.PrintStream;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * Класс который управляет коллекцией
 */
public class CollectionManager {
    /**
     * Коллекция, над которой будет осуществляться работа
     */
    TreeMap<Integer, Ticket> treeMap;
    /**
     * Время инициализации коллекции
     */
    ZonedDateTime collectionInitialization;

    /**
     * Конструктор - создание нового объекта менеджера коллекции
     */
    public CollectionManager() {
        this.treeMap = new TreeMap<>();
        String i = Instant.now().toString();
        collectionInitialization = ZonedDateTime.parse(i);
    }
    /**
     * Метод, выводящий основную информацию по используемой коллекции
     */
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Коллекция ").append(treeMap.getClass().getSimpleName()).append("\n");
        sb.append("Тип элементов коллекции: ").append(Ticket.class.getSimpleName()).append("\n");

        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(pattern);
        sb.append("Время ининициализации коллекции: ").append(collectionInitialization.plusHours(3).format(europeanDateFormatter)).append("\n");
        sb.append("Количество элементов в коллекции: ").append(treeMap.size()).append("\n");
        return sb.toString();
    }
    /**
     * Метод, выводящий информацию по элементам коллекции
     */
    public String show() {
        StringBuilder sb= new StringBuilder();
        if (treeMap.size() == 0) {
            sb.append("Коллекция пуста.");
        } else {
            for (Map.Entry<Integer, Ticket> entry : treeMap.entrySet()) {
                sb.append(entry.getValue().toString()).append("\n"); // перебор элементов коллкции
            } //Получение значений по коллекции
        }
        return sb.toString();
    }

    /**
     * Метод, демонстрирующий все типы билетов
     */
    public String showTicketType(){
        StringBuilder sb = new StringBuilder();
        if (treeMap.size() == 0) {
            sb.append("Коллекция пуста.");
        } else {
            for (Map.Entry<Integer, Ticket> entry : treeMap.entrySet()) {
                sb.append(entry.getValue().getType()).append("\n"); // перебор элементов коллкции
            } //Получение значений по коллекции
        }
        return sb.toString();
    }

    /**
     * Метод, демонстрирующий поле Person в порядке уменьшения
     */
    public String showPerson(){
        StringBuilder sb= new StringBuilder();
        ArrayList<Person> personArrayList = new ArrayList<>();
        if (treeMap.size() == 0) {
            sb.append("Коллекция пуста.");
        } else {
            for (Map.Entry<Integer, Ticket> entry : treeMap.entrySet()) {
                personArrayList.add(entry.getValue().getPerson());
            }
            Collections.reverse(personArrayList);
            for(Person person : personArrayList){
                sb.append(person.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Метод, удаляющий элементы с меньшим значением ( ключа)
     * @param id уникальный идентификатор элемента коллекции(ключ)
     */
    public void removeLowerKey(Integer id){
        ArrayList<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, Ticket> entry : treeMap.entrySet()) {
            if (entry.getKey() < id) keys.add(entry.getKey());
        }
        for (Integer key : keys) {
            treeMap.remove(key);
        }
    }



    /**
     * Метод, добавляющий новый элемент в коллекцию c рандомным id
     * @param ticket элемент коллекции, требующий добавления
     */
    public void insert(Ticket ticket) {
        int id=getNewRandomId();
        ticket.setId(id);
        treeMap.put(id, ticket);
    }
    /**
     * Метод, добавляющий новый элемент в коллекцию c конкретным  id
     * @param ticket элемент коллекции, требующий добавления
     */
    public void insertWithId(Integer id, Ticket ticket, PrintStream printStream) {
        if (treeMap.get(id) == null) {
            treeMap.put(id, ticket);
        } else printStream.printf("Элемент с данным ключом уже существует");
    }

    /**
     * Метод, возращаюзий рандомное значение id ( в пределах 10.000)
     * @return радномный id
     */
    public int getNewRandomId(){
        int max=10000;
        int i =(int)(Math.random() * max);
        while(treeMap.containsKey(i)){
            i =(int)(Math.random() * max);
        }
        return i;
    }
    /**
     * Метод, изменяющий поле выбранного по идентификатору элемента коллекции
     *
     * @param id    уникальный идентификатор элемента коллекции (ключ)
     * @param field имя поля элемента коллекции, требующее изменения
     * @param value значение поля элемента коллекции
     * @throws NullPointerException     исключение, выбрасываемое когда требует инициализации, но не инициалировано
     * @throws ClassCastException       исключение, выбрасываемое при попытке преобразовать один тип в другой, который не может быть получен из исходного
     * @throws IllegalArgumentException исключение, выбрасываемое при попытке передать методу недопустимые атрибуты
     */
    public void update(Integer id, String field, String value,PrintStream printStream) {
        if(TicketFieldValidation.validate(field,value)) {
            switch (field) {
                case "name": {
                    if (value.equals("")) throw new NullPointerException();
                    treeMap.get(id).setName(value);
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "coordinate_x": {
                    if (value.equals("")) value = null;
                    treeMap.get(id).setCoordinateX(Float.valueOf(value));
                    System.out.println("Значение поля было изменено");
                    break;

                }
                case "coordinate_y": {
                    if (value.equals("")) value = null;
                    treeMap.get(id).setCoordinateY(Float.parseFloat(value));
                    System.out.println("Значение поля было изменено");
                    break;
                }

                case "price":{
                    if (value.equals("")) {
                        treeMap.get(id).setPrice(0);
                    } else {
                        treeMap.get(id).setPrice((Integer.parseInt(value)));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "ticket type":{
                    treeMap.get(id).setType(TicketType.valueOf(value.toUpperCase(Locale.ROOT)));
                    System.out.println("Значение поля было изменено");
                    break;
                }

                case "person height":{
                    if (value.equals("")) {
                        treeMap.get(id).getPerson().setHeight(null);
                    } else {
                        treeMap.get(id).getPerson().setHeight(Long.parseLong(value));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }

                case "person weight":{
                    if (value.equals("")) {
                        treeMap.get(id).getPerson().setWeight(null);
                    } else {
                        treeMap.get(id).getPerson().setWeight(Float.parseFloat(value));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "person eye color":{
                    treeMap.get(id).getPerson().setEyeColor(EyeColor.valueOf(value.toUpperCase(Locale.ROOT)));
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "person hair color":{
                    treeMap.get(id).getPerson().setHairColor(HairColor.valueOf(value.toUpperCase(Locale.ROOT)));
                    System.out.println("Значение поля было изменено");
                    break;
                }

                case "stop": {
                    break;
                }
                default: {
                    System.out.println("Поле не распознано");
                    break;
                }
            }
        }else{
            System.err.println("Неверно указано название поля или значение не принадлежит допустимому.");
        }
    }
    /**
     * Метод, удаляющий выбранный по идентификатору элемент коллекции
     *
     * @param id уникальный идентификатор элемента коллекции (ключ)
     */
    public void removeKey(Integer id) {
        treeMap.remove(id);
    }
    /**
     * Метод, удаляющий все элементы коллекции
     */
    public void clear() {
        treeMap.clear();
    }
    /**
     * Метод, выводящий булевый результат истины, если в коллекции существует элемент с выбранным ключом, иначе ложь
     *
     * @param id уникальный идентификатор элемента коллекции (ключ)
     * @return true - в коллекции существует элемент с выбранным ключом, false - такого элемента не существует
     */
    public boolean containsKey(Integer id) {
        return treeMap.containsKey(id);
    }
    /**
     * Метод, сохраняющий элементы коллекции в формате XML
     *
     * @param filePath путь до файла, куда следует сохранить элементы коллекции
     */
    public void save(String filePath) {
        XmlParser xmlParser = new XmlParser();
        FileManager fileManager = new FileManager();

        Ticket[] tickets = new Ticket[treeMap.size()];
        tickets = treeMap.values().toArray(tickets);
        String str = xmlParser.parseToXml(tickets);
        fileManager.writeToFile(str, filePath);
    }

    /**
     *
     * @return строка, содержащая все поля коллекции. Отформатировано выводит все в столбец
     */
    public static String getFieldNames(){
        return "Список всех полей: \nname (String)\ncoordinate_x (Float)\ncoordinate_y (Float)\n" +
                "price (int)" + Arrays.toString(TicketType.values()) + "\nheight (Long)\nweight (Float)" +
                Arrays.toString(EyeColor.values()) + Arrays.toString(HairColor.values());
    }

    /**
     * Метод удаляет элементы коллекции,с полем ( id) выше, чем у заданного поля (id)
     * @param id уникальный идентификатор элемента коллекции (ключ)
     */
    public void removeGreaterKey(Integer id){
        ArrayList<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, Ticket> entry : treeMap.entrySet()) {
            if (entry.getKey() > id) keys.add(entry.getKey());
        }
        for (Integer key : keys) {
            treeMap.remove(key);
        }
    }
}

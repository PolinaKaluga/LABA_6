package workWithFile;

import collection.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;


/**
 * Класс, осуществляющий парсинг данных, переданных в качестве строки, в коллекцию или из коллекции доставать данные, которые будут записаны в строку в формате XML.
 */
public class XmlParser {
    /**
     * Метод, осуществляющий парсинг данных, представленных в качестве строки, в коллекцию
     */
    public Ticket[] parseToCollection(InputSource text) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XmlHandler handler = new XmlHandler();
        try {
            parser.parse(text, handler);
        } catch (SAXException ignored) {
        }
        Ticket[] ticketArr = new Ticket[handler.tickets.size()];
        return handler.tickets.toArray(ticketArr);
    }



    private static class XmlHandler extends DefaultHandler {

        private ArrayList<Ticket> tickets = new ArrayList<>();

        private String name;
        /**
         * Хранит поле x объекта класса Coordinates
         */
        private Float x = null;
        /**
         * Хранит поле y объекта класса Coordinates
         */
        private Float y = null;
        /**
         * Хранит поле creationDate объекта класса LabWork
         */
        private ZonedDateTime creationDate = null;


        private Integer price = null;
        private TicketType type = null;

        private Long height = null;
        private Float weight = null; //Поле может быть null, Значение поля должно быть больше 0
        private EyeColor eyeColor = null; //Поле не может быть null
        private HairColor hairColor = null; //Поле не может быть null

        private String lastElementName;
        private Coordinates coordinates;



        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            lastElementName = qName;
        }


        @Override
        public void characters(char[] ch, int start, int length) throws ClassCastException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();
            try {
                if (!information.isEmpty()) {
                    switch (lastElementName) {
                        case "name":
                            name = information;
                            break;
                        case "coordinate_x":
                            x = Float.parseFloat(information);
                            break;
                        case "coordinate_y":
                            y = Float.parseFloat(information);
                            break;
                        case "creation_date":
                            creationDate = ZonedDateTime.parse(information);
                            break;
                        case "ticket type":
                            type = type.valueOf(information);
                            break;
                        case "height":
                            height = Long.parseLong(information);
                            break;
                        case "weight":
                            weight = Float.parseFloat(information);
                            break;
                        case "eye color":
                            eyeColor= eyeColor.valueOf(information);
                            break;
                        case "hair color":
                            hairColor= hairColor.valueOf(information);
                            break;
                    }


                }
            } catch (IllegalArgumentException ex) {
                System.err.println("Указанной константы перечисляемого типа не существует, либо невозможно преобразование типов");
                System.err.println(name);
            }
        }
    }


    public String parseToXml(Ticket[] tickets) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version = \"1.0\"?>\n");
        sb.append("<treemap>\n");
        for (Ticket ticket : tickets) {
            sb.append("\t<Ticket>\n");
            sb.append("\t\t<name>").append(ticket.getName()).append("</name>");
            sb.append("\n\t\t<coordinate_x>").append(ticket.getCoordinates().getX()).append("</coordinate_x>");
            try {
                float y = ticket.getCoordinates().getY();
                sb.append("\n\t\t<coordinate_y>").append(y).append("</coordinate_y>");
            } catch (NullPointerException ignored) {
            }
            sb.append("\n\t\t<creation_date>").append(ticket.getCreationDate()).append("</creation_date>");
            sb.append("\n\t\t<price>").append(ticket.getPrice()).append("<price>");
            sb.append("\n\t\t<type>").append(ticket.getType()).append("<type>");
            sb.append("\n\t\t<person height>").append(ticket.getPerson().getHeight()).append("<person height>");
            sb.append("\n\t\t<person weight>").append(ticket.getPerson().getWeight()).append("<person weight>");
            sb.append("\n\t\t<person eye color>").append(ticket.getPerson().getEyeColor()).append("<person eye color>");
            sb.append("\n\t\t<person hair color>").append(ticket.getPerson().getHairColor()).append("<person hair color>");

            sb.append("\n\t</Ticket>\n");
        }
        sb.append("</treemap>\n");
        return sb.toString();
    }
}


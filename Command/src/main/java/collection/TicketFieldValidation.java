package collection;

import java.util.Locale;

/**
 * Класс, предназначенный для валидации полей объекта LabWork.
 */
public class TicketFieldValidation {

    public static boolean validate(String field, String value) {
        try {
            switch (field) {
                case "id": {
                    if (value == null || value.equals("")) throw new NullPointerException();
                    if (Integer.parseInt(value) > 0) return true;
                    break;
                }
                case "name": {
                    if (value.equals("") || value==null) throw new NullPointerException();
                    return true;
                }
                case "coordinate_x": {
                    if (value.equals("") || value==null) return true;

                    if (Float.parseFloat(value)!=0) {
                        return true;
                    }
                    break;
                }
                case "coordinate_y": {
                    if (value == null || value.equals("")) return true;

                    if (Float.parseFloat(value)!=0) {
                        return true;
                    }
                    break;
                }
                case "price":{
                    if (value == null || value.equals("")) return true;

                    if (Integer.parseInt(value)!=0) {
                        return true;
                    }
                    break;
                }

                case "ticket type": {
                    TicketType.valueOf(value.toUpperCase(Locale.ROOT));
                    return true;
                }


                case "height": {
                    if (value == null || value.equals("")) return true;
                    if(Long.parseLong(value)>0) return true;
                    break;
                }

                case "weight": {
                    if (value == null || value.equals("")) return true;
                    if(Float.parseFloat(value)>0) return true;
                    break;
                }
                case "eye color": {
                    EyeColor.valueOf(value.toUpperCase(Locale.ROOT));
                    return true;
                }
                case "hair color": {
                    HairColor.valueOf(value.toUpperCase(Locale.ROOT));
                    return true;
                }
                case "": {
                    return false;
                }
            }
        } catch (ClassCastException | IllegalArgumentException | NullPointerException ignored) {
        }
        return false;
    }

}

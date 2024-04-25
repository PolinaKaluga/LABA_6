package collection;

import java.io.Serializable;

/**
 * Класс - поле объекта класса LabWork
 */
public class Person implements Serializable {


    private Long height; //Поле может быть null, Значение поля должно быть больше 0

    private Float weight; //Поле может быть null, Значение поля должно быть больше 0
    private EyeColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null

    public Person(Long height, Float weight, EyeColor eyeColor, HairColor hairColor ){
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }


    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height){
        this.height = height;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor)  {

        this.eyeColor = eyeColor;
    }


    public HairColor  getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColor hairColor){

        this.hairColor = hairColor;
    }


    @Override
    public String toString() {
        return "Person{" +
                "height='" + height + '\'' +
                ", weight=" + weight +
                ", eyecolor=" + eyeColor +
                ", haircolor=" + hairColor +
                '}';
    }
}
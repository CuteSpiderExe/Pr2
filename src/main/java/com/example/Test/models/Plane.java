package com.example.Test.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Plane {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    String name, year;
    @NotNull(message = "Поле не может быть пустым")
    @Min(message = "Количество не может быть отрицательным",value = 0)
    Integer price , kolvo, engine;

    public Plane(String name, String year, Integer price, Integer kolvo, Integer engine) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.kolvo = kolvo;
        this.engine = engine;
    }

    public Plane() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getKolvo() {
        return kolvo;
    }

    public void setKolvo(Integer kolvo) {
        this.kolvo = kolvo;
    }

    public Integer getEngine() {
        return engine;
    }

    public void setEngine(Integer engine) {
        this.engine = engine;
    }
}

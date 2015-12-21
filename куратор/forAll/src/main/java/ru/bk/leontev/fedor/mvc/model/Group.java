package ru.bk.leontev.fedor.mvc.model;

import java.io.Serializable;
import java.util.UUID;

public class Group implements Serializable{
    UUID id;
    private int number;
    private String fackultet;

    public Group() {
        id = UUID.randomUUID();
        number=0;
        fackultet="";
    }

    public Group(int number,String fackultet){
        id = UUID.randomUUID();
        this.number=number;
        this.fackultet=fackultet;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getFackultet() {
        return fackultet;
    }

    public void setFackultet(String fack) {
        this.fackultet = fack;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int namber) {
        this.number = namber;
    }

    /**
     * ѕреобразует представление к массиву строк
     */
    public String[] arrStrings() {
        String[] arr = new String[2];
        arr[1] = getFackultet();
        arr[0] = String.valueOf(getNumber());
        return arr;
    }

    public boolean equals(Group group) {
        if (group.getNumber() == getNumber() &&
                group.getFackultet().equals(getFackultet())) {
            return true;
        }
        return false;
    }
}

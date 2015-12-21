package ru.bk.leontev.fedor.mvc.model;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class Student implements Serializable {
    UUID id;
    private String name;
    private String surname;
    private String patronymic;
    private int group;
    private Date date;

    public Student() {
        id = UUID.randomUUID();
        name = "";
        surname = "";
        patronymic = "";
        group = 0;
       // date.setTime(0);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student(String name, String surname, String patronymic, int group, String date) {
        id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.group = group;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getGroup() {

        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {

        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Преобразует представление к массиву строк
     */
    public String[] arrStrings() {
        String[] arr = new String[5];
        arr[0] = getName();
        arr[1] = getSurname();
        arr[2] = getPatronymic();
        arr[3] = String.valueOf(getGroup());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        arr[4] = formatter.format(date);
        return arr;
    }

    /**
     * Сравнивает без учета id
     */
    public boolean equals(Student student) {
        if (student.getName().equals(getName()) &&
                student.getSurname().equals(getSurname()) &&
                student.getPatronymic().equals(getPatronymic()) &&
                student.getGroup() == getGroup() &&
                student.getDate().equals(getDate())) {
            return true;
        }
        return false;
    }
}

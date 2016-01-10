package ru.bk.leontev.fedor.mvc.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Неважно on 28.12.2015.
 */
public class ValidatorStudent {
    String name;
    String surName;
    String patronimic;
    String group;
    String date;

    public ValidatorStudent(String name, String surName, String patronymic, String group, String date) {
        this.name = name;
        this.surName = surName;
        this.patronimic = patronymic;
        this.group = group;
        this.date = date;
    }

    public int isEmptiFields() {
        if (name.isEmpty() || surName.isEmpty() || patronimic.isEmpty() || group.isEmpty() || date.isEmpty()) return 1;
        return 0;
    }

    public int groupIsDigital() {
        try {
            Integer.parseInt(group);
        } catch (NumberFormatException ex) {
            return 2;
        }
        return 0;
    }

    public int formatDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            formatter.parse(date);
        } catch (ParseException e) {
            return 3;
        }
        return 0;
    }

    public int validate() {
        int a = isEmptiFields();
        int b = groupIsDigital();
        int c = formatDate();
        if (a != 0) return a;
        if (b != 0) return b;
        return c;
    }


}

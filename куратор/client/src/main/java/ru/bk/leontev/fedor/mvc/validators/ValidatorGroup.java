package ru.bk.leontev.fedor.mvc.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Неважно on 28.12.2015.
 */
public class ValidatorGroup {
    String facultet;
    String number;

    public ValidatorGroup(String facultet, String number) {
        this.facultet = facultet;
        this.number = number;
    }

    public int isEmptiFields() {
        if (facultet.isEmpty() || number.isEmpty()) return 1;
        return 0;
    }

    public int numberIsDigital() {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            return 2;
        }
        return 0;
    }

    public int validate() {
        int a = isEmptiFields();
        int b = numberIsDigital();
        if (a != 0) return a;
        return b;

    }


}

package ru.bk.leontev.fedor.mvc.view;

import ru.bk.leontev.fedor.mvc.controller.StudentController;
import ru.bk.leontev.fedor.mvc.model.Student;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;

public class StudentView {
    //поля для ввода
    private JTextField name = new JTextField(10);
    private JTextField surname = new JTextField(10);
    private JTextField patronymic = new JTextField(10);
    private JTextField group = new JTextField(3);
    private JTextField date = new JTextField(10);


    //подписи к полям для ввода
    private JLabel labelName = new JLabel("Name");
    private JLabel labelSurname = new JLabel("Surname");
    private JLabel labelPatronymic = new JLabel("Patronymic");
    private JLabel labelGroup = new JLabel("Group");
    private JLabel labelBirthday = new JLabel("Birthday(dd/MM/yyyy):");


    //создание таблицы отображающей данные с диска
    String[] colums = {"Name", "Surname", "Patronymic", "Group", "Birthday(dd/MM/yyyy)"};
    public JTable jTable = new JTable(StudentController.getStringTable(), colums);
    public JScrollPane scrollPane = new JScrollPane(jTable);


    /**
     * конструктор помещает поля на панель
     */
    public StudentView(JPanel window) {
        window.setLayout(new FlowLayout());
        window.add(labelName);
        window.add(name);
        window.add(labelSurname);
        window.add(surname);
        window.add(labelPatronymic);
        window.add(patronymic);
        window.add(labelGroup);
        window.add(group);
        window.add(labelBirthday);
        window.add(date);
    }


    /**
     * обновляет данные в таблице
     */
    public void updateTable() {
        jTable.setModel(new DefaultTableModel(StudentController.getStringTable(), colums));
    }

    /**
     * возвращает введенный объект
     */
    public Student getStudent() {
        Student student = new Student(getName(), getSurname(), getPatronymic(), getGroup(), getDate());
        return student;
    }


    //получение данных с формы и присвоение данных на форму
    public String getName() {
        return name.getText();
    }

    public String getSurname() {
        return surname.getText();
    }

    public String getPatronymic() {
        return patronymic.getText();
    }

    public int getGroup() {
        return Integer.parseInt(group.getText());
    }

    public String getDate() {

        return String.valueOf(date.getText());
    }


    public void setName(String name) {
        this.name.setText(name);
    }

    public void setSurname(String surname) {
        this.surname.setText(surname);
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.setText(patronymic);
    }

    public void setGroup(String group) {
        this.group.setText(group);
    }

    public void setDate(String date) {
        this.date.setText(date);
    }



    /**
     * метод для очистки полей
     */
    public void clear() {
        setName("");
        setSurname("");
        setPatronymic("");
        setGroup("");
        setDate("");
    }
}


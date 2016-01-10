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
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        scrollPane = new JScrollPane(jTable);
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // c.fill = GridBagConstraints.PAGE_START;
        // c.weighty = 1.0;   // установить отступ
        c.anchor = GridBagConstraints.WEST;
        //c.insets = new Insets(10, 0, 0, 0);  // поставить заглушку
        c.gridy = 0;
        c.gridx = 0;
        //c.ipadx = 15;
        window.add(labelName, c);
        c.gridy = 0;
        c.gridx = 1;
        window.add(name, c);
        c.gridy = 1;
        c.gridx = 0;
        window.add(labelSurname, c);
        c.gridy = 1;
        c.gridx = 1;
        window.add(surname, c);
        c.gridy = 2;
        c.gridx = 0;
        window.add(labelPatronymic, c);
        c.gridy = 2;
        c.gridx = 1;
        window.add(patronymic, c);
        c.gridy = 3;
        c.gridx = 0;
        window.add(labelGroup, c);
        c.gridy = 3;
        c.gridx = 1;
        window.add(group, c);
        c.gridy = 4;
        c.gridx = 0;
        window.add(labelBirthday, c);
        c.gridy = 4;
        c.gridx = 1;
        window.add(date, c);
    }


    /**
     * обновляет данные в таблице
     */
    public void updateTable() {
        jTable.setModel(new DefaultTableModel(StudentController.getStringTable(), colums));
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(70);
    }

    /**
     * возвращает введенный объект
     */
    public Student getStudent() {
        Student student = new Student(getName(), getSurname(), getPatronymic(), Integer.parseInt(getGroup()), getDate());
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

    public String getGroup() {
        return group.getText();
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


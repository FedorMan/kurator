package ru.bk.leontev.fedor.mvc.view;

import ru.bk.leontev.fedor.mvc.controller.GroupController;
import ru.bk.leontev.fedor.mvc.model.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

public class GroupView {
    //поля для ввода
    private JTextField number = new JTextField(2);
    private JTextField fackultet = new JTextField(20);


    //подписи к полям для ввода
    private JLabel labelNumber = new JLabel("Number");
    private JLabel labelfackultet = new JLabel("Fackultet");


    //создание таблицы отображающей данные с диска
    String[] colums = {"Number","Fackultet"};
    public JTable jTable = new JTable(GroupController.getStringTable(), colums);
    public JScrollPane scrollPane = new JScrollPane(jTable);


    /**
     * помещает в поле данные
     */
    public void setNumber(String number) {
        this.number.setText(number);
    }


    /**
     * помещает в поле данные
     */
    public void setFackultet(String fackultet) {
        this.fackultet.setText(fackultet);
    }

    /**
     * конструктор помещает поля на панель
     */
    public GroupView(JPanel window) {
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 15;
        window.add(labelNumber, c);
        c.gridx = 1;
        c.gridy = 0;
        window.add(number, c);
        c.gridx = 0;
        c.gridy = 1;
        window.add(labelfackultet, c);
        c.gridx = 1;
        c.gridy = 1;
        window.add(fackultet, c);
    }


    /**
     * обновляет данные в таблице
     */
    public void updateTable() {
        jTable.setModel(new DefaultTableModel(GroupController.getStringTable(), colums));
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(300);
    }

    /**
     * возвращает данные введение в поле
     */
    public String getNumber() {
        return this.number.getText();
    }

    /**
     * возвращает данные введение в поле
     */
    public String getFacultet() {
        return this.fackultet.getText();
    }

    /**
     * возвращает введенный объект
     */
    public Group getGroup() {
        Group group = new Group(Integer.parseInt(getNumber()), getFacultet());
        return group;
    }

    /**
     * очищает поля
     */
    public void clear() {
        setNumber("");
        setFackultet("");
    }
}
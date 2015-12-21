package ru.bk.leontev.fedor.mvc.view;

import ru.bk.leontev.fedor.mvc.controller.GroupController;
import ru.bk.leontev.fedor.mvc.model.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
    String[] colums = {"Number", "Fackultet"};
    public JTable jTable = new JTable(GroupController.getStringTable(), colums);
    public JScrollPane scrollPane;


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
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
         jTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        // jTable.setPreferredScrollableViewportSize(new Dimension(250, 400));
        scrollPane = new JScrollPane(jTable);
        //scrollPane.setPreferredSize(new Dimension(250, 400));
        // scrollPane.setSize(new Dimension(200, 500));
        window.setLayout(new FlowLayout());
        window.add(labelNumber);
        window.add(number);
        window.add(labelfackultet);
        window.add(fackultet);
    }




    /**
     * обновляет данные в таблице
     */
    public void updateTable() {
        jTable.setModel(new DefaultTableModel(GroupController.getStringTable(), colums));
    }

    /**
     * возвращает данные введение в поле
     */
    public int getNumber() {
        return Integer.parseInt(this.number.getText());
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
        Group group = new Group(getNumber(), getFacultet());
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
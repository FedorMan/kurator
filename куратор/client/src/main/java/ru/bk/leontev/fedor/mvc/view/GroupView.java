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
    //���� ��� �����
    private JTextField number = new JTextField(2);
    private JTextField fackultet = new JTextField(20);


    //������� � ����� ��� �����
    private JLabel labelNumber = new JLabel("Number");
    private JLabel labelfackultet = new JLabel("Fackultet");


    //�������� ������� ������������ ������ � �����
    String[] colums = {"Number", "Fackultet"};
    public JTable jTable = new JTable(GroupController.getStringTable(), colums);
    public JScrollPane scrollPane;


    /**
     * �������� � ���� ������
     */
    public void setNumber(String number) {
        this.number.setText(number);
    }


    /**
     * �������� � ���� ������
     */
    public void setFackultet(String fackultet) {
        this.fackultet.setText(fackultet);
    }

    /**
     * ����������� �������� ���� �� ������
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
     * ��������� ������ � �������
     */
    public void updateTable() {
        jTable.setModel(new DefaultTableModel(GroupController.getStringTable(), colums));
    }

    /**
     * ���������� ������ �������� � ����
     */
    public int getNumber() {
        return Integer.parseInt(this.number.getText());
    }

    /**
     * ���������� ������ �������� � ����
     */
    public String getFacultet() {
        return this.fackultet.getText();
    }

    /**
     * ���������� ��������� ������
     */
    public Group getGroup() {
        Group group = new Group(getNumber(), getFacultet());
        return group;
    }

    /**
     * ������� ����
     */
    public void clear() {
        setNumber("");
        setFackultet("");
    }
}
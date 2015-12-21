package ru.bk.leontev.fedor.mvc.view;

import ru.bk.leontev.fedor.mvc.controller.StudentController;
import ru.bk.leontev.fedor.mvc.model.Group;
import ru.bk.leontev.fedor.mvc.model.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class View extends JFrame {

    //отображаемые элементы
    private JPanel studentPanel = new JPanel();
    private JPanel groupPanel = new JPanel();
    private JPanel groupTablePanel = new JPanel();
    private JButton saveStudent = new JButton("SaveStudent");
    private JButton saveGroup = new JButton("SaveGroup");
    private JButton deleteStudent = new JButton("DeleteStudent");
    private JButton deleteGroup = new JButton("DeleteGroup");
    private JButton clearStudent = new JButton("ClearStudent");
    private JButton clearGroup = new JButton("ClearGroup");
    private StudentView studentView = new StudentView(getStudentPanel());
    private GroupView groupView = new GroupView(getGroupPanel());


    //определ€ют выбран ли какой либо объект из таблиц
    private boolean refactorStudent;
    private boolean refactorGroup;


    //выбранные элементы
    private Student dedicatedStudent;
    private Group dedicatedGroup;

    /**
     * создание окна
     */
    public View() {
        setRefactorGroup(false);
        setRefactorStudent(false);
        JPanel panelGroupButton_delete = new JPanel();
        JPanel panelStudentButton_delete = new JPanel();

        JPanel panelGroupButton_add_clear = new JPanel();
        JPanel panelStudentButton_add_clear = new JPanel();
        groupTablePanel.setPreferredSize(new Dimension(250, 400));
        groupTablePanel.add(getGroupView().scrollPane);
        groupTablePanel.setBackground(Color.BLACK);
        GridLayout gl=new GridLayout(5, 2);
        //gl.;
        this.setLayout(gl);
        panelGroupButton_delete.add(deleteGroup);
        panelStudentButton_delete.add(deleteStudent);
        panelGroupButton_add_clear.add(saveGroup);
        panelGroupButton_add_clear.add(clearGroup);
        panelStudentButton_add_clear.add(saveStudent);
        panelStudentButton_add_clear.add(clearStudent);
        this.add(getGroupPanel());
        this.add(getStudentPanel());
        this.add(panelGroupButton_add_clear);
        this.add(panelStudentButton_add_clear);
        this.add(groupTablePanel);
        this.add(getStudentView().scrollPane);
        this.add(panelGroupButton_delete);
        this.add(panelStudentButton_delete);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100, 700);
        this.setVisible(true);
        this.setResizable(true);
    }


    //добавление слушателей на кнопки
    public void deleteStudentListener(ActionListener actionListener) {
        deleteStudent.addActionListener(actionListener);
    }

    public void deleteGroupListener(ActionListener actionListener) {
        deleteGroup.addActionListener(actionListener);
    }

    public void addSaveListener(ActionListener actionListener) {
        saveStudent.addActionListener(actionListener);
    }

    public void addGroupListener(ActionListener actionListener) {
        saveGroup.addActionListener(actionListener);
    }

    public void clearStudentListener(ActionListener actionListener) {
        clearStudent.addActionListener(actionListener);
    }

    public void clearGroupListener(ActionListener actionListener) {
        clearGroup.addActionListener(actionListener);
    }


    //реализаци€ слушателей таблиц
    public void addTableStudentListener() {
        ListSelectionModel selModel = getStudentView().jTable.getSelectionModel();
        selModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRows = getStudentView().jTable.getSelectedRows();
                TableModel model = getStudentView().jTable.getModel();
                if (selectedRows.length != 0) {
                    getStudentView().setName((String) model.getValueAt(selectedRows[0], 0));
                    getStudentView().setSurname((String) model.getValueAt(selectedRows[0], 1));
                    getStudentView().setPatronymic((String) model.getValueAt(selectedRows[0], 2));
                    getStudentView().setGroup((String) model.getValueAt(selectedRows[0], 3));
                    getStudentView().setDate((String) model.getValueAt(selectedRows[0], 4));
                    setDedicatedStudent(new Student());
                    getDedicatedStudent().setName((String) model.getValueAt(selectedRows[0], 0));
                    getDedicatedStudent().setSurname((String) model.getValueAt(selectedRows[0], 1));
                    getDedicatedStudent().setPatronymic((String) model.getValueAt(selectedRows[0], 2));
                    getDedicatedStudent().setGroup(Integer.parseInt((String) model.getValueAt(selectedRows[0], 3)));
                    getDedicatedStudent().setDate((String) model.getValueAt(selectedRows[0], 4));
                    setRefactorStudent(true);
                }
                getStudentView().jTable.clearSelection();

            }
        });
    }

    public void addTableGroupListener() {
        ListSelectionModel selModel = getGroupView().jTable.getSelectionModel();
        selModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRows = getGroupView().jTable.getSelectedRows();
                TableModel model = getGroupView().jTable.getModel();
                if (selectedRows.length != 0) {
                    getGroupView().setFackultet((String) model.getValueAt(selectedRows[0], 1));
                    getGroupView().setNumber((String) model.getValueAt(selectedRows[0], 0));
                    setDedicatedGroup(new Group());
                    getDedicatedGroup().setFackultet((String) model.getValueAt(selectedRows[0], 1));
                    getDedicatedGroup().setNumber(Integer.parseInt((String) model.getValueAt(selectedRows[0], 0)));
                    setRefactorGroup(true);
                    StudentController.search();
                    getStudentView().updateTable();
                }
                getGroupView().jTable.clearSelection();
            }
        });
    }


    //взаимодействие с пол€ми
    public boolean isRefactorStudent() {
        return refactorStudent;
    }

    public void setRefactorStudent(boolean refactorStudent) {
        this.refactorStudent = refactorStudent;
    }

    public boolean isRefactorGroup() {
        return refactorGroup;
    }

    public void setRefactorGroup(boolean refactorGroup) {
        this.refactorGroup = refactorGroup;
    }

    public JPanel getStudentPanel() {
        return studentPanel;
    }

    public JPanel getGroupPanel() {
        return groupPanel;
    }

    public StudentView getStudentView() {
        return studentView;
    }

    public GroupView getGroupView() {
        return groupView;
    }

    public Student getDedicatedStudent() {
        return dedicatedStudent;
    }

    public void setDedicatedStudent(Student dedicatedStudent) {
        this.dedicatedStudent = dedicatedStudent;
    }

    public Group getDedicatedGroup() {
        return dedicatedGroup;
    }

    public void setDedicatedGroup(Group dedicatedGroup) {
        this.dedicatedGroup = dedicatedGroup;
    }

    public boolean valideteStudent() {
        try {
            Student student = getStudentView().getStudent();
        } catch (Exception ex) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Incorrect enter", "", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        String s = studentView.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            formatter.parse(s);
        } catch (ParseException e) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Incorrect date, please enter date in formate dd/MM/yyyy", "", JOptionPane.DEFAULT_OPTION);
            //studentView.clear();
            return false;
        }
        return true;
    }

    public boolean valideteGroup() {
        try {
            Group group = getGroupView().getGroup();
        } catch (Exception ex) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Incorrect enter", "", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        return true;
    }
}

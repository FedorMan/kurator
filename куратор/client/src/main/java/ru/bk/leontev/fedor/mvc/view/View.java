package ru.bk.leontev.fedor.mvc.view;

import ru.bk.leontev.fedor.mvc.controller.StudentController;
import ru.bk.leontev.fedor.mvc.model.Group;
import ru.bk.leontev.fedor.mvc.model.Student;
import ru.bk.leontev.fedor.mvc.validators.ValidatorGroup;
import ru.bk.leontev.fedor.mvc.validators.ValidatorStudent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class View extends JFrame {

    //отображаемые элементы
    private JPanel studentPanel = new JPanel();
    private JPanel groupPanel = new JPanel();
    private JButton saveStudent = new JButton("SaveStudent");
    private JButton saveGroup = new JButton("SaveGroup");
    private JButton deleteStudent = new JButton("DeleteStudent");
    private JButton deleteGroup = new JButton("DeleteGroup");
    private JButton clearStudent = new JButton("ClearStudent");
    private JButton clearGroup = new JButton("ClearGroup");
    //   private JButton addStudent=new JButton("addStudent");
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

        //this.setLayout(new GridLayout(5, 2));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        panelGroupButton_delete.add(deleteGroup, c);
        panelStudentButton_delete.add(deleteStudent, c);
        panelGroupButton_add_clear.add(saveGroup, c);
        //panelGroupButton_add_clear.add(addStudent);
        panelGroupButton_add_clear.add(clearGroup, c);
        panelStudentButton_add_clear.add(saveStudent, c);
        panelStudentButton_add_clear.add(clearStudent, c);
        c.gridy = 0;
        c.gridx = 0;
        //c.weightx=0.3;
        c.gridwidth = 1;
        this.add(getGroupPanel(), c);
        c.gridy = 0;
        c.gridx = 1;
        // c.weightx=0.7;
        c.gridwidth = 3;
        this.add(getStudentPanel(), c);
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 1;
        this.add(panelGroupButton_add_clear, c);
        c.gridy = 1;
        c.gridx = 1;
        c.gridwidth = 3;
        this.add(panelStudentButton_add_clear, c);
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 1;
        //c.ipadx=60;
        this.add(getGroupView().scrollPane, c);
        c.gridy = 2;
        c.gridx = 1;
        c.gridwidth = 3;
        this.add(getStudentView().scrollPane, c);
        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 1;
        this.add(panelGroupButton_delete, c);
        c.gridy = 3;
        c.gridx = 1;
        c.gridwidth = 3;
        this.add(panelStudentButton_delete, c);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(930, 650);
        this.setVisible(true);
        this.setResizable(false);
        //  this.addStudentListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         JPanel jPanel = new JPanel();
        //         jPanel.add(saveStudent);
        //         JOptionPane.showInputDialog(jPanel, "new Student");
        //      }
        //   });
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

    //  public void addStudentListener(ActionListener actionListener) {
    //      addStudent.addActionListener(actionListener);
    //   }


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
        ValidatorStudent validatorStudent = new ValidatorStudent(getStudentView().getName(), getStudentView().getSurname(), getStudentView().getPatronymic(), getStudentView().getGroup(), getStudentView().getDate());
        int result = validatorStudent.validate();
        if (result == 1) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Empty fields", "Exeption", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (result == 2) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Incert Group is not digital", "Exeption", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (result == 3) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Incorrect date, please enter date in formate dd/MM/yyyy", "Exeption", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        return true;
    }

    public boolean valideteGroup() {
        ValidatorGroup validatorGroup = new ValidatorGroup(getGroupView().getFacultet(), getGroupView().getNumber());
        int result = validatorGroup.validate();
        if (result ==1){
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Empty fields", "Exeption", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        if (result == 2) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Incert Number is not digital", "Exeption", JOptionPane.DEFAULT_OPTION);
            return false;
        }
            return true;
    }
}

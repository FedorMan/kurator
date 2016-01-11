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
import java.awt.event.ActionListener;

public class View extends JFrame {

    //отображаемые элементы
    private JPanel studentPanel = new JPanel();
    private JPanel groupPanel = new JPanel();
    private JButton addStudent = new JButton("AddStudent");
    public JButton showAllStudent = new JButton("ShowAll");
    private JButton addGroup = new JButton("AddGroup");
    private JButton deleteStudent = new JButton("DeleteStudent");
    private JButton deleteGroup = new JButton("DeleteGroup");
    private JButton refactoringStudent = new JButton("RefactorStudent");
    private JButton refactoringGroup = new JButton("RefactorGroup");
    private StudentView studentView = new StudentView(getStudentPanel());
    private GroupView groupView = new GroupView(getGroupPanel());
    public NewElement newElement;

    private JLabel laberSavesStudents = new JLabel("Table students");
    private JLabel laberSavesGroups = new JLabel("Table groups");


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

        //this.setLayout(new GridLayout(5, 2));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        panelGroupButton_delete.add(addGroup);
        //panelGroupButton_add_clear.add(NewElement);
        panelGroupButton_delete.add(refactoringGroup);
        panelStudentButton_delete.add(addStudent);
        panelStudentButton_delete.add(refactoringStudent);
        showAllStudent.setEnabled(false);
        panelStudentButton_delete.add(showAllStudent);
        panelStudentButton_delete.add(deleteStudent);
        panelGroupButton_delete.add(deleteGroup);
        c.gridy = 0;
        c.gridx = 0;
        //c.weightx=0.3;
        //c.gridwidth = 1;
        // this.add(getGroupPanel(), c);
        laberSavesGroups.setFont(new Font("Serif", Font.PLAIN, 30));
        this.add(laberSavesGroups, c);
        c.gridy = 0;
        c.gridx = 1;
        // c.weightx=0.7;
        c.gridwidth = 3;
        //newElement=new NewElement(getStudentPanel());
        //this.add(getStudentPanel(), c);
        laberSavesStudents.setFont(new Font("Serif", Font.PLAIN, 30));
        this.add(laberSavesStudents, c);
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 1;
        //this.add(panelGroupButton_add_clear, c);
        this.add(getGroupView().scrollPane, c);
        c.gridy = 1;
        c.gridx = 1;
        c.gridwidth = 3;
        //this.add(panelStudentButton_add_clear, c);
        this.add(getStudentView().scrollPane, c);
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 1;
        //c.ipadx=60;
        //this.add(getGroupView().scrollPane, c);
        //c.gridy = 2;
        //c.gridx = 1;
        //c.gridwidth = 3;
        // this.add(getStudentView().scrollPane, c);
        // c.gridy = 3;
        // c.gridx = 0;
        // c.gridwidth = 1;
        this.add(panelGroupButton_delete, c);
        c.gridy = 2;
        c.gridx = 1;
        c.gridwidth = 3;
        this.add(panelStudentButton_delete, c);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(930, 650);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    /**
     * —оздает окно дл€ добавлени€ нового студента
     */
    JButton saveStudent = new JButton("Save");
    JButton clearStudentFields = new JButton("Clear");


    public void createWindowForAddStudent() {
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("New student");
        jPanel.add(jLabel);
        jPanel.add(getStudentPanel());
        jPanel.add(saveStudent);
        jPanel.add(clearStudentFields);
        newElement = new NewElement(jPanel, 250, 230);
    }

    JButton saveGroup = new JButton("Save");
    JButton clearGroupFields = new JButton("Clear");

    public void createWindowForAddGroup() {
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("New group");
        jPanel.add(jLabel);
        jPanel.add(getGroupPanel());
        jPanel.add(saveGroup);
        jPanel.add(clearGroupFields);
        newElement = new NewElement(jPanel, 350, 200);
    }


    //добавление слушателей на кнопки
    public void deleteStudentListener(ActionListener actionListener) {
        deleteStudent.addActionListener(actionListener);
    }

    public void deleteGroupListener(ActionListener actionListener) {
        deleteGroup.addActionListener(actionListener);
    }

    public void addNewStudentListener(ActionListener actionListener) {
        addStudent.addActionListener(actionListener);
    }

    public void addSaveStudentListener(ActionListener actionListener) {
        saveStudent.addActionListener(actionListener);
    }

    public void addRefactorStudentListener(ActionListener actionListener) {
        refactoringStudent.addActionListener(actionListener);
    }

    public void addRefactorGroupListener(ActionListener actionListener) {
        refactoringGroup.addActionListener(actionListener);
    }


    public void addNewGroupListener(ActionListener actionListener) {
        addGroup.addActionListener(actionListener);
    }

    public void addSaveGroupListener(ActionListener actionListener) {
        saveGroup.addActionListener(actionListener);
    }

    public void clearStudentListener(ActionListener actionListener) {
        clearStudentFields.addActionListener(actionListener);
    }

    public void clearGroupListener(ActionListener actionListener) {
        clearGroupFields.addActionListener(actionListener);
    }

    public void showAllStudentListener(ActionListener actionListener) {
        showAllStudent.addActionListener(actionListener);
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
                    showAllStudent.setEnabled(true);
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
        if (result == 1) {
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

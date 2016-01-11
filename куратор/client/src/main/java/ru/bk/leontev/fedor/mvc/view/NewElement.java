package ru.bk.leontev.fedor.mvc.view;

import javax.swing.*;

public class NewElement extends JFrame {

    public NewElement(JPanel jPanel,int weigth, int higth) {
        this.add(jPanel);
       // this.setSize(250, 230);
        this.setSize(weigth, higth);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
    public void close(){
        this.setVisible(false);
        this.dispose();
        //this.close();
    }

}

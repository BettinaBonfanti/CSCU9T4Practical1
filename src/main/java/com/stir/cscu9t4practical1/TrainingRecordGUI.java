// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField terrain = new JTextField(10);
    private JTextField tempo = new JTextField(4);
    private JTextField repetition = new JTextField(3);
    private JTextField recovery= new JTextField(3);
    private JTextField where = new JTextField(10);
    private JLabel labrec = new JLabel(" Recovery:");
    private JLabel labrep = new JLabel(" Repetitions:");
    private JLabel labter = new JLabel(" Terrain:");
    private JLabel labtem = new JLabel(" Tempo:");
    private JLabel labw = new JLabel(" Location:");

    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find All By Date");


    private JMenuBar sessions = new JMenuBar();
    private JMenu trainingSession = new JMenu("Training Session");
    private JMenuItem swim = new JMenuItem("Swim");
    private JMenuItem sprint = new JMenuItem("Sprint");
    private JMenuItem cycle = new JMenuItem("Cycle");

    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());

        swim.addActionListener(this);
        trainingSession.add(swim);
        sprint.addActionListener(this);
        trainingSession.add(sprint);
        cycle.addActionListener(this);
        trainingSession.add(cycle);
        sessions.add(trainingSession);
        add(sessions);
        sessions.setEnabled(true);

        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);

        add(labrec);
        add(recovery);
        labrec.setVisible(false);
        recovery.setVisible(false);
        recovery.setEditable(true);
        add(labrep);
        add(repetition);
        labrep.setVisible(false);
        repetition.setVisible(false);
        repetition.setEditable(true);
        add(labter);
        add(terrain);
        labter.setVisible(false);
        terrain.setVisible(false);
        terrain.setEditable(true);
        add(labtem);
        add(tempo);
        labtem.setVisible(false);
        tempo.setVisible(false);
        tempo.setEditable(true);
        add(labw);
        add(where);
        labw.setVisible(false);
        where.setVisible(false);
        where.setEditable(true);

        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);

        setSize(800, 400);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate) {
            message = findAllByDate();
        }
        if (event.getSource() == swim){
            labw.setVisible(true);
            where.setVisible(true);
        }
        if (event.getSource() == sprint){
            labrep.setVisible(true);
            repetition.setVisible(true);
            labrec.setVisible(true);
            recovery.setVisible(true);
        }
        if (event.getSource() == cycle){
            labtem.setVisible(true);
            tempo.setVisible(true);
            labter.setVisible(true);
            terrain.setVisible(true);
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        int m, d, y, h, mm, s;
        float km;
        String n = "";
        Entry entry;
        try {
            m = Integer.parseInt(month.getText());
            d = Integer.parseInt(day.getText());
            y = Integer.parseInt(year.getText());
            km = java.lang.Float.parseFloat(dist.getText());
            h = Integer.parseInt(hours.getText());
            mm = Integer.parseInt(mins.getText());
            s = Integer.parseInt(secs.getText());
            n = name.getText();
            switch (what) {
                case "cycle":
                    String ter = terrain.getText();
                    String tem = tempo.getText();
                    entry = new CycleEntry(n, d, m, y, h, mm, s, km, ter, tem);
                    break;
                case "sprint":
                    int rep = Integer.parseInt(repetition.getText());
                    int rec = Integer.parseInt(recovery.getText());
                    entry = new SprintEntry(n, d, m, y, h, mm, s, km, rep, rec);
                    break;
                case "swim":
                    String wr = where.getText();
                    entry = new SwimEntry(n, d, m, y, h, mm, s, km, wr);
                    break;
                default:
                    entry = new Entry(n, d, m, y, h, mm, s, km);
            }
            myAthletes.addEntry(entry);
        }
        catch (NumberFormatException nfe){
            System.err.println("Wrong input");
            JOptionPane.showMessageDialog(null, "Day, month, year, hour, minute, second must be integers! \nKm must be float");
            message = "Input error. Insert data again";
        }
        if (n.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty field. Please enter a name");
            message = "Input error. Insert data again";
        }

        return message;
    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    public String findAllByDate() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.findAllByDate(d, m, y);
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        where.setText("");
        repetition.setText("");
        recovery.setText("");
        terrain.setText("");
        tempo.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI


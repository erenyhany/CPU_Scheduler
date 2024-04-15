package com.example.cpuscheduler;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;


public class GUIController implements Initializable {
    @FXML
    private Button addButton;

    @FXML
    private Button runButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button submitButton;

    @FXML
    private ComboBox<String> c1;

    @FXML
    private ComboBox<String> c2;
    @FXML
    private TextArea textArea;

    @FXML
    private TextField p;
    @FXML
    private TextField bt;
    @FXML
    private Pane pane;
    @FXML
    private TableColumn<PCB, SimpleIntegerProperty> pid;

    @FXML
    private TableColumn<PCB, SimpleIntegerProperty> rem;
    @FXML
    private TableColumn<PCB, SimpleIntegerProperty> arr;

    @FXML
    private TableColumn<PCB, SimpleIntegerProperty> bur;
    @FXML
    private TableColumn<PCB, SimpleIntegerProperty> fin;
    @FXML
    private TableView<PCB> table;

    Scheduler s;
    ObservableList<PCB> list =FXCollections.observableArrayList();

    @FXML
    void submit(MouseEvent event) {
        String s1 = c1.getSelectionModel().getSelectedItem().toString();
        String s2 = c2.getSelectionModel().getSelectedItem().toString();
        // System.out.println(s1);
        // System.out.println(s2);

        if (s1.equals("FCFS")) {
            s = new FCFS();
        } else if (s1.equals("SJF")) {
            if (s2.equals("preemptive")) {
                s = new SJF(PreOrNon.preemptive);
            } else if (s2.equals("nonPreemptive")) {
                s = new SJF();
            }

        } else if (s1.equals("Priority")) {
            if (s2.equals("preemptive")) {
                s = new Priority(PreOrNon.preemptive);
            } else if (s2.equals("nonPreemptive")) {
                s = new Priority();
            }

        } else if (s1.equals("RoundRobin")) {
            s = new RoundRobin(PreOrNon.preemptive);
        }


    }

    @FXML
    void addProcess(MouseEvent event) {
        PCB process;
        if (c1.getSelectionModel().getSelectedItem().toString().equals("Priority")) {
            process = new PCB(Integer.parseInt(p.getText()), Integer.parseInt(bt.getText()));

        } else {
            process = new PCB(Integer.parseInt(bt.getText()));

        }

        s.add(process);
        list.add(process);

       /* for(int i=0;i<s.allPCBs.size();i++){
            System.out.println(s.allPCBs.get(i).getProcessID());

      }*/
    }

    @FXML
    void run(MouseEvent event) {
        new Thread(()->{ s.runScheduler();}).start();

    }

    @FXML
    void stop(MouseEvent event) {

        s.stop();
        String str=null;
        textArea.clear();
        textArea.appendText("average waiting: " + str.valueOf(s.getAverageWaiting())+
                "\naverage turn around: "+ str.valueOf(s.getAverageTurnArround()));




    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c1.setItems(FXCollections.observableArrayList("FCFS","SJF","Priority","RoundRobin"));
        c2.setItems(FXCollections.observableArrayList("preemptive","nonPreemptive"));

        rem.setCellValueFactory(new PropertyValueFactory<PCB,SimpleIntegerProperty >("rem"));
        bur.setCellValueFactory(new PropertyValueFactory<PCB,SimpleIntegerProperty>("bur"));
        arr.setCellValueFactory(new PropertyValueFactory<PCB,SimpleIntegerProperty>("arr"));
        pid.setCellValueFactory(new PropertyValueFactory<PCB,SimpleIntegerProperty>("pid"));
        fin.setCellValueFactory(new PropertyValueFactory<PCB,SimpleIntegerProperty>("fin"));


        table.setItems(list);


    }
}

package com.example.cpuscheduler;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PCB {
    private static int num=0;

    private int processID ;
    private int priority ;
    private int burstTime ;
    private int remainingTime;
    private int arrivalTime;
    private int startTime;
    private int finishTime;

    IntegerProperty pid;
    IntegerProperty fin;
    IntegerProperty arr;
    IntegerProperty bur;
    IntegerProperty rem;


    public int getPid() {
        return pid.get();
    }

    public IntegerProperty pidProperty() {
        return pid;
    }

    public int getFin() {
        return fin.get();
    }

    public IntegerProperty finProperty() {
        return fin;
    }

    public int getArr() {
        return arr.get();
    }

    public IntegerProperty arrProperty() {
        return arr;
    }

    public int getBur() {
        return bur.get();
    }

    public IntegerProperty burProperty() {
        return bur;
    }

    public int getRem() {
        return rem.get();
    }

    public IntegerProperty remProperty() {
        return rem;
    }





    //constructors
    public PCB( int priority, int burstTime ) {
        this.processID = ++num;
        pid=new SimpleIntegerProperty(processID);
        this.priority = priority;
        this.burstTime = burstTime;
        bur=new SimpleIntegerProperty(burstTime);
        this.remainingTime = burstTime;
        rem=new SimpleIntegerProperty(remainingTime);
        this.startTime = -1 ;
        this.finishTime = -1 ;
        fin=new SimpleIntegerProperty(finishTime);
        this.arrivalTime = Scheduler.currentTime;
        arr=new SimpleIntegerProperty(arrivalTime);


    }

    public PCB(int burstTime) {
        this.processID =num++;
        pid=new SimpleIntegerProperty(processID);
        this.burstTime = burstTime;
        bur=new SimpleIntegerProperty(burstTime);
        this.remainingTime = burstTime;
        rem=new SimpleIntegerProperty(remainingTime);
        this.startTime = -1 ;
        this.finishTime = -1;
        fin=new SimpleIntegerProperty(finishTime);
        this.arrivalTime = Scheduler.currentTime;
        arr=new SimpleIntegerProperty(arrivalTime);


    }

    public int getProcessID() {
        return processID;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        PCB.num = num;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    //di mmkn tkhali el remainning time bel negative fa khalo balko law el proccess
    // di khelset shelouha mn el object structure beta3ko!
    public void decrementRemainingTime(int currentTime){
        if(startTime== -1 ){
            setStartTime(currentTime);

        }
        remainingTime--;
        rem.set(remainingTime);

        if(remainingTime == 0 ){
            finishTime =currentTime+1;
            fin.set(finishTime);

        }

    }
}

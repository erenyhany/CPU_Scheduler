package com.example.cpuscheduler;

public class PCB {
    private static int num=0;

    private int processID ;
    private int priority ;
    private int burstTime ;
    private int remainingTime;
    private int arrivalTime;
    private int startTime;
    private int finishTime;


    //constructors
    public PCB( int priority, int burstTime ) {
        this.processID = ++num;
        this.priority = priority;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.startTime = -1 ;
        this.finishTime = -1 ;
        this.arrivalTime = Scheduler.currentTime;


    }

    public PCB(int burstTime) {
        this.processID =num++;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.startTime = -1 ;
        this.finishTime = -1;
        this.arrivalTime = Scheduler.currentTime;


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

        if(remainingTime == 0 ){
            finishTime =currentTime+1;
        }

    }
}

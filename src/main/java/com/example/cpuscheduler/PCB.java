package com.example.cpuscheduler;

public class PCB {
    private static int num=0;

    private int processID ;
    private int priority ;
    private int burstTime ;

    //constructors
    public PCB( int priority, int burstTime) {
        this.processID = ++num;
        this.priority = priority;
        this.burstTime = burstTime;
    }

    public PCB(int burstTime) {
        this.processID = ++num;
        this.burstTime = burstTime;
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
}

package com.example.cpuscheduler;

public class RoundRobin extends Scheduler{
    @Override
    public void add(PCB newpcb) {

    }

    @Override
    public void executePCB(PCB currentPCB) {

        currentPCB.decrementRemainingTime(Scheduler.currentTime);

        System.out.print(String.format("|%-4d",currentPCB.getProcessID()));

        waitOneSecond();


    }

    @Override
    public void runScheduler() {

    }
}

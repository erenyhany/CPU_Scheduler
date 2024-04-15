package com.example.cpuscheduler;

import java.util.LinkedList;
import java.util.Queue;

public class FCFS  extends Scheduler{

    Queue<PCB> queue = new LinkedList<>();


    @Override
    public void add(PCB newpcb) {
        super.add(newpcb);
        queue.add(newpcb);
    }


    @Override
    public void runScheduler() {
        stop = false;
        PCB currentPcb;
        while(!stop){
            if(queue.isEmpty()) continue;
            currentPcb = queue.poll();
            executePCB(currentPcb);
        }
    }


}

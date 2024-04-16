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
            if(queue.isEmpty()) {
                try {
                    semaSched.acquire();
                }catch (Exception e){
                    System.out.println(e.toString());
                }
                continue;
            }
            currentPcb = queue.poll();
            executePCB(currentPcb);
        }
    }


}

package com.example.cpuscheduler;

import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin extends Scheduler{
    Queue<PCB> queue;
     public RoundRobin(PreOrNon preOrNon){
        super(preOrNon);
        queue=new LinkedList<>();


    }
    @Override
    public void add(PCB newpcb) {
        super.add(newpcb);
        queue.add(newpcb);
    }

    @Override
    public void executePCB(PCB currentPCB) {
        if(GUIController.firsttime)
        {
            try{
                GUIController.semaphore.acquire();
                GUIController.firsttime=false;
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        currentPCB.decrementRemainingTime(Scheduler.currentTime);

        str =  String.format("|%-4d|",currentPCB.getProcessID());
        GUIController.semaphore.release();

        waitOneSecond();


    }

    @Override
    public void runScheduler() {
        stop = false;
        PCB currentPcb;
        while(!stop){
            if(queue.isEmpty()) continue;
            currentPcb = queue.poll();
            executePCB(currentPcb);
            if(currentPcb.getRemainingTime()!= 0 )
                queue.add(currentPcb);


        }
    }
}

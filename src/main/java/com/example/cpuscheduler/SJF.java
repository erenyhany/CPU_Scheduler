package com.example.cpuscheduler;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class SJF extends Scheduler{
    PriorityQueue<PCB> queue ;


    public SJF(){
        super();
        queue =  new PriorityQueue<>(new MyCustomComparator());
    }

    public SJF(PreOrNon preOrNon){
        super(preOrNon);
        queue =  new PriorityQueue<>(new MyCustomComparator());

    }

    @Override
    public void add(PCB newpcb) {
        super.add(newpcb);
        queue.add(newpcb);
    }



    @Override
    public void runScheduler() {
        stop = false;
        PCB currentPcb;
        while (!stop){
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
            if(currentPcb.getRemainingTime()!= 0 )
                queue.add(currentPcb);

        }
    }



}








class MyCustomComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
     return ((PCB) o1).getRemainingTime() - ((PCB) o2).getRemainingTime();
    }

}
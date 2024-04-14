package com.example.cpuscheduler;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Priority extends Scheduler{

    PriorityQueue<PCB> priQueue;

    public Priority(){
        super();
        priQueue =  new PriorityQueue<>(new PriorityComparator());
    }

    public Priority(PreOrNon preOrNon){
        super(preOrNon);
        priQueue =  new PriorityQueue<>(new PriorityComparator());
    }

    @Override
    public void add(PCB newpcb) {
        super.add(newpcb);
        priQueue.add(newpcb);
    }


    @Override
    public void runScheduler() {
        stop = false;
        PCB currentPcb;
        while(!stop){
            if(priQueue.isEmpty()) continue;
            currentPcb = priQueue.poll();
            executePCB(currentPcb);
            if(currentPcb.getRemainingTime()!= 0 )
                priQueue.add(currentPcb);

        }
    }
}



class PriorityComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        return ((PCB) o1).getPriority() - ((PCB) o2).getPriority();
    }

}

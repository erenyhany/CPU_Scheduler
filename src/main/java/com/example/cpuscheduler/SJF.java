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
            if(queue.isEmpty()) continue;
            currentPcb = queue.poll();
            executePCB(currentPcb);
            if(currentPcb.getRemainingTime()!= 0 )
                queue.add(currentPcb);

        }
    }

//    public static void main (String [] args){
//        SJF ss = new SJF(PreOrNon.nonPreemptive);
//        PCB dd= new PCB(9);
//        ss.add(dd);
//
//        ss.add(new PCB(7));
//        ss.add(new PCB(10));
//        ss.runScheduler();

//        System.out.println(Scheduler.currentTime);

//
//
//        for(int i = 0 ;i<3;i++){
////            System.out.println(ss.queue.peek().getRemainingTime());
////            ss.waitOneSecond();
////            ss.queue.poll();
//            dd.decrementRemainingTime(ss.currentTime);
//            ss.waitOneSecond();
//            System.out.println(dd.getRemainingTime());
//
//        }
//        String m = String.format("ssdsd%-4sd","5");
//        System.out.println(m);
//    }

}








class MyCustomComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
     return ((PCB) o1).getRemainingTime() - ((PCB) o2).getRemainingTime();
    }

}
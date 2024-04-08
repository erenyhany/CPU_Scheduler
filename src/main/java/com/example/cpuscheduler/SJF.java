package com.example.cpuscheduler;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SJF extends Scheduler{

    PriorityQueue<PCB> queue ;


    public SJF(){
        super();
        queue =  new PriorityQueue<>(new MyCustomComparator());
    }

    @Override
    public void add(PCB newpcb) {
        table.add(newpcb);
        queue.add(newpcb);
        newpcb.setArrivalTime(currentTime);
    }

    @Override
    public void executeprocess(PCB currentPCB) {

    }

//    public static void main (String [] args){
//        SJF ss = new SJF();
//        PCB dd= new PCB(9);
//        ss.add(dd);
//        ss.add(new PCB(7));
//        ss.add(new PCB(10));
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
//        System.out.println();
//    }

}








class MyCustomComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
     return ((PCB) o1).getRemainingTime() - ((PCB) o2).getRemainingTime();
    }

}
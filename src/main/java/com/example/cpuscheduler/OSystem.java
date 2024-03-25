package com.example.cpuscheduler;

import java.util.PriorityQueue;
import java.util.Queue;

public class OSystem {

    Scheduler sched;
    Queue <PCB> newQueue ;

    OSystem(){
        newQueue = new PriorityQueue<>();

    }

    public void setSched(Scheduler sched) {
        this.sched = sched;
    }
}

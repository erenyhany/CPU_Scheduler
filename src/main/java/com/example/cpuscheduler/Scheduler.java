package com.example.cpuscheduler;

import java.util.Vector;
enum PreOrNon{
    preemptive , nonPreemptive;
}



public abstract class Scheduler {
    PreOrNon preOrNot ;
    int currentTime ;
    Vector<PCB> table ;

    public Scheduler(){
        currentTime = 0;
        table = new Vector<>();
        this.preOrNot = PreOrNon.nonPreemptive;
    }

    public Scheduler(PreOrNon preOrNot){
     currentTime = 0;
     table = new Vector<>();
     this.preOrNot = preOrNot;
    }

    //kol wahed haiendah el super then haizawed el pcb di fl object structure beta3o
     public  void add(PCB newpcb){
         table.add(newpcb);
         newpcb.setArrivalTime(currentTime);
     };

     //di 1-hancall fiha el decremenetremainingtime lel process el haiet3emeleha execute,
        // 2- nersem moraba3 3ala hasab nou3o
        //  3-ba3d kda ne3mel delay 1 second gowa el function b waitOneSecond()
        //    4- update the table informations     xxxxxx haga mohema 3aiez 2a2olhaxxxx
    public abstract void executePCB(PCB currentPCB);


    /*
    *1while true
    *2-toul ma el data structure beta3etna fadia khosh f busy wait
    *3-hancall run pcb lel 3aleha el dor b executePCB method
    *4-ne3mel add lekol el processes el added dynamically
    * */
     public abstract void runScheduler();

     public void waitOneSecond()  {
         try {
             Thread.sleep(1000); // 1000 milliseconds = 1 second
         }catch (InterruptedException e){
             System.out.println("Waiting was interrupted!");
         }
         currentTime++;
     }
}

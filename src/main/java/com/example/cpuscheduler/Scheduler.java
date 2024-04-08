package com.example.cpuscheduler;

import java.util.Vector;

public abstract class Scheduler {
 int currentTime ;
 Vector<PCB> table ;
 public Scheduler(){
     currentTime = 0;
     table = new Vector<>();
 }

//di han7ot el pcb fel object struct beta3na + ne3mel append fl table
 public abstract void add(PCB newpcb);

 //di 1-hancall fiha el decremenetremainingtime lel process el haiet3emeleha execute,
    // 2- nersem moraba3 3ala hasab nou3o
    //  3-ba3d kda ne3mel delay 1 second gowa el function b waiOneSecond()
    //    4- ne3mel add lekol el processes el added dynamicaly

            //4-
 public abstract void executeprocess(PCB currentPCB);

 public void waitOneSecond()  {
     try {
         Thread.sleep(1000); // 1000 milliseconds = 1 second
     }catch (InterruptedException e){
         System.out.println("Waiting was interrupted!");
     }
     currentTime++;
 }
}

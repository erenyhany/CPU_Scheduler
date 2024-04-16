package com.example.cpuscheduler;

import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

enum PreOrNon{
    preemptive , nonPreemptive;
}



public abstract class Scheduler {
    int lastExacuted = -1 ;
    String str = "";
    PreOrNon preOrNot ;
    boolean stop = false;
    static int currentTime = 0;
    Vector<PCB> allPCBs ;
//    Vector<String> table;
    Semaphore semaSched = new Semaphore(1);
    public Scheduler(){
        currentTime = 0;
        allPCBs = new Vector<>();
//        table = new Vector<>();
        this.preOrNot = PreOrNon.nonPreemptive;
        lastExacuted = -1;
    }

    public Scheduler(PreOrNon preOrNot){
     currentTime = 0;
     allPCBs = new Vector<>();
//     table = new Vector<>();
     lastExacuted = -1;
     this.preOrNot = preOrNot;

    }


     public  void add(PCB newpcb){

         allPCBs.add(newpcb);
         newpcb.setArrivalTime(currentTime);
         semaSched.release();
     };


    public void executePCB(PCB currentPCB){
        do{
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
            if(lastExacuted != currentPCB.getProcessID()){
                str = String.format("|%-4d|",currentPCB.getProcessID());
            }else {
                str = "|    |";
            }
            GUIController.semaphore.release();
            waitOneSecond();
            lastExacuted = currentPCB.getProcessID();
        }while(preOrNot == PreOrNon.nonPreemptive && currentPCB.getRemainingTime()!= 0);

    };


    /*
    *1while !stop  (di 3ashan ne3mel button esmo stop awel ma nla2i en kolo khalas w msh 3aizin nezawed pcbs tani
    *               dynamically nedous 3aleh 3ashan nebda2 nehseb el waiting time w el remaining time.)
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

     double getAverageWaiting (){
         double acc =0;
         for (PCB p: allPCBs) {
             acc+=(p.getFinishTime()-p.getBurstTime()-p.getArrivalTime());
         }
         return acc/allPCBs.size();
     }

    double getAverageTurnArround (){
        double acc =0;
        for (PCB p: allPCBs) {
            acc+=(p.getFinishTime()-p.getArrivalTime());
        }
        return acc/allPCBs.size();
    }

    void stop (){
         stop = true;

    }
}

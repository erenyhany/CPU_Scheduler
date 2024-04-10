package com.example.cpuscheduler;

import java.util.Vector;
enum PreOrNon{
    preemptive , nonPreemptive;
}



public abstract class Scheduler {
    int lastExacuted = -1 ;
    PreOrNon preOrNot ;
    boolean stop = false;
    static int currentTime = 0;
    Vector<PCB> allPCBs ;
//    Vector<String> table;

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

    //kol wahed haiendah el super then haizawed el pcb di fl object structure beta3o
    //lesa ereny hatzawed hena lel observable list
     public  void add(PCB newpcb){
         allPCBs.add(newpcb);
//         table.add(String.format("p%-3darrial time:%-3dremaining:%d"));
         newpcb.setArrivalTime(currentTime);
     };

//    public void updateTable(int pcbID){
//        table.
//    }
     //di 1-hancall fiha el decremenetremainingtime lel process el haiet3emeleha execute,
        // 2- nersem moraba3 3ala hasab nou3o
        //  3-ba3d kda ne3mel delay 1 second gowa el function b waitOneSecond()
        //  ***shaklena msh hanehtagha***  4- update the table informations     xxxxxx haga mohema 3aiez 2a2olhaxxxx
    public void executePCB(PCB currentPCB){
        do{
            currentPCB.decrementRemainingTime(Scheduler.currentTime);
            if(lastExacuted != currentPCB.getProcessID()){
                System.out.print(String.format("|%-4d",currentPCB.getProcessID()));
            }else {
                System.out.print("|    ");
            }
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
}

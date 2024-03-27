package com.example.cpuscheduler;

public abstract class Scheduler {
 int currentTime ;

 public Scheduler(){
     currentTime = 0;
 }


 //di mmkn tkhali el remainning time bel negative fa khalo balko law el proccess
 // di khelset shelouha mn el object structure beta3ko!
 public void decrementRemainingTime(PCB currentPCB){
     if(currentPCB.getStartTime()== -1 ){
         currentPCB.setStartTime(currentTime);
     }
     currentPCB.setRemainingTime(currentPCB.getRemainingTime()-1);

     if(currentPCB.getRemainingTime() == 0 ){
         currentPCB.setFinishTime(currentTime+1);
     }
     currentTime++;
 }


 public abstract void add(PCB newpcb);


 //di 1-hancall fiha el decremenetremainingtime lel process el haiet3emeleha execute,
    //  2-ba3d kda ne3mel delay 1 second gowa el function
        // 3- nersem moraba3 3ala hasab nou3o

 public abstract void executeprocess(PCB currentPCB);
}

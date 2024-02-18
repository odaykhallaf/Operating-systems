


/*the  Process class represents a cpu scheduling process by storing properties such as id
        arrival  and burst times  and by offering methods for calculat and update process metric
        such as wait and turnaround time  which are crucial for cpu scheduling algo simulation */

class Process {

    //Definition of variables
    // distinct number assigned to the process;.

    private int id;
    // the moment at which the process is finished;
    private int finishTime;
// as soon as the process is added to the system;
    private int arrivalTime;
// Total CPU time used by the process;

    private int burstTime;
// Utilize the remaining CPU time;

    private int remainingBurstTime;
// The total time from start to end;

    private int turnaroundTime;
// The duration of time in the prepared line;

    private int waiteTime;
    private int completeTime;                   // The point at which the procedure has completed executing;

// use the constructor to initialize the process characteristics.

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;                          //Decide  Set the process id..

        // Decide on the arrival time..
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;            // Decide on the total burst time..
        // Set the remaining burst time to the whole burst time initially..
        this.remainingBurstTime = burstTime;
        this.completeTime = 0;                  // Set the completion time to 0 at first ..
        this.waiteTime = 0;                     // Set the wait time to 0 at first...
        this.turnaroundTime = 0;                //Set the turnaround time to 0 at first..
    }

// methods for retrieving process characteristic.;

    public int getId() {
        return id;                            // Give the process Id back..;l
    }

// techniques for determining and allocating process performance metrics
    public void calculatewaitetime() {
        waiteTime = completeTime - burstTime - arrivalTime; // calculate wait time.;
    }

    public void decrementbursttime(int time) {
        remainingBurstTime = Math.max(0, remainingBurstTime - time); // reducing  remaining burst duration while making sure it not negative;;
    }

    public void calculateturnaroundtime() {
        // calculate turnaround time..
        turnaroundTime = completeTime - arrivalTime;
    }

// methods for retrieving process characteristic

    public int getbursttime() {
        return burstTime;                         // give back the whole burst time.
    }

    public int getarrivaltime() {
        // Give the arrival time back.
        return arrivalTime;
    }

    public int getremainingbursttime() {
        // give back  leftover burst duration..
        return remainingBurstTime;
    }

    public void decrementBurstTime(int timeSlice) {
        if (remainingBurstTime > 0) {
            remainingBurstTime--;                 // iff it positive decrease the remaining burst duration..;
        }
    }

    public int getwaitetime() {
        // give the wait time back..
        return waiteTime;
    }

    public int getturnaroundtime() {
        return turnaroundTime;                    // Give the turnaround time back.;.
    }

// Process attribute setter method..

    public void setbursttime(int burstTime) {
        // Define the duration of the burst.
        this.burstTime = burstTime;
    }

    public void setcompletetime(int completeTime) {
        this.completeTime = completeTime;        // decide on the timing of completion..
    }

    public void resetbursttime() {
        // set the amountt of burst time left to  entire burst time
        this.remainingBurstTime = this.burstTime;
    }

    public void setwaitetime(int waiteTime) {
        this.waiteTime = waiteTime;              // choose the duration of the waiting time
    }

    public void setturnaroundtime(int turnaroundTime) {
        // decide on  turnaround time;;
        this.turnaroundTime = turnaroundTime;
    }
// finish time getter and setter method..
// getter function to get  arrival time of  process


    public int getArrivalTime() {
        return arrivalTime;  // Give back  arrival ttime field value;;
    }

// Getter function to get  overall burst time of  process..

    public int getBurstTime() {
        // give back the burst Time field value;
        return burstTime;
    }

// getter function to get  burst time remaining in  process;

    public int getRemainingBurstTime() {
        return remainingBurstTime;  // provides the remaining burst time field value...
    }

// Set  process completion time using the setter function\\

    public void setFinishTime(int finishTime) {
        // assigns the finesh the specified finishTime field of time...
        this.finishTime = finishTime;
    }

// geter function to get process completion time//

    public int getFinishTime() {
        // gives back  finishTime field value;;
        return finishTime;
    }



}

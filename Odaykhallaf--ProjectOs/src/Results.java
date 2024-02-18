





/*  this cladd **Results**  keeps track of and compute the typical cpu scheduling algo turn around and waiting times...>
        it help with the performance evaluation of different scheduling algorithms by accumulating these metrics over several
        instances and computing their averages;;..    */
class Results {
//Definition of variables
    private String algorithmName;
    private double averageTurnaroundTime;
    private double averageWaitingTime;
    private double totalATT;
    private double totalAWT;
    private int count;

    //Setter and getter;;

    // set the average turnaround time...
    public void setAverageTurnaroundTime(double att) {
        this.averageTurnaroundTime = att;
    }

    //  set the average waiting time..
    public void setAverageWaitingTime(double awt) {
        this.averageWaitingTime = awt;
    }

    //  retrieve the average turnaround time..
    public double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }

    //  retrieve the average waiting time..
    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    //// Set the final attributes to their initial values..
    public Results(String algorithmName) {
        this.algorithmName = algorithmName;
    }

// A procedure for resetting the overall turnaround waiting, and counting times;
    public void reset() {
        totalATT = 0;
        totalAWT = 0;
        count = 0;
    }

// Process to increase the count and add a result to the overall turnaround and waiting times;;
    public void addresult(double att, double awt) {
        totalATT += att;
        totalAWT += awt;
        count++;
    }

    // Get a  retrieve the algorithm name;;
    public String getAlgorithmName() {
        return algorithmName;
    }

// How to figure out how long people wait on average;
    public double getaverageawt() {
        return count > 0 ? totalAWT / count : 0;

    }

// Technique for figuring out the typical turnaround time..
    public double getaverageatt() {

        return count > 0 ? totalATT / count : 0;
    }
}

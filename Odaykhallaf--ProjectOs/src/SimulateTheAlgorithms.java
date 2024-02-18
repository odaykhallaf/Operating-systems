

/*
the class  SimulateTheAlgorithms class replicates several Algorithms techniques...
 such as Round Robin , Shortest Remaining Time First , and First-Come-First-Serve ,..
  it keeps track of processes and uses several scheduling strategies to keep them under control...
  in addition to simulae g the execution of processes in accordance with each scheduling algorithm's principles...
   the class may sort processes and compute average waiting and turnaround times...
   it purpose is to assess and contrast the effectiveness of various scheduling techniques in handling CPU jobs.//

   */
import java.util.*;

//
class SimulateTheAlgorithms {

    // Round Robin scheduling define a range of quantum timings
    private final int[] quantumTimes = {10, 20, 50};

    // store the quantum value  in  constructor
    private final int quantum;

    // create list to store the processes
    private List<Process> processes;

    // create a list of queue for multilevel feedback queue algo;;
    private List<List<Process>> queues;

    // constructor for the SimulateTheAlgorithms class.
    public SimulateTheAlgorithms(int quantum) {
        this.quantum = quantum;

        // set up  queue for the multilayer scheduling of feedback queues
        initializeQueues();
    }

    // initialize the multi level feed back queues
    private void initializeQueues() {
        queues = new ArrayList<>();
        for (int i = 0; i < quantumTimes.length; i++) {
            queues.add(new LinkedList<>());
        }
    }

    // set the list of processes
    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    // simulate First Come First Serve  algo
    public void simulateFirstComeFirstServe() {
        // sort processe based on arrival time
        Collections.sort(processes, new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                return p1.getarrivaltime() - p2.getarrivaltime();
            }
        });

        // set up variables to track metrics and time;..
        int currentTime = 0;
        int totalTurnaroundTimeFCFS = 0;
        int totalWaitingTimeFCFS = 0;

        // pprocess iterations should be done in First Come First Serve sequence
        for (Process process : processes) {
            if (currentTime < process.getarrivaltime()) {
                currentTime = process.getarrivaltime();
            }

            process.setwaitetime(currentTime - process.getarrivaltime());
            process.setturnaroundtime(process.getwaitetime() + process.getbursttime());

            totalWaitingTimeFCFS += process.getwaitetime();
            totalTurnaroundTimeFCFS += process.getturnaroundtime();

            currentTime += process.getbursttime();
        }

        // calculate average turnaround time and average waiting time for First Come First Serve  ...
//        double averageTurnaroundTimeFCFS = (double) totalTurnaroundTimeFCFS / processes.size();
//        double averageWaitingTimeFCFS = (double) totalWaitingTimeFCFS / processes.size();
    }

    //  simulate the Shortest Remaining Time First   algo
    public void simulateShortestRemainingTimeFirst() {
        // set up a ready processes priority queue at  beginning;
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                if (p1.getremainingbursttime() != p2.getremainingbursttime()) {
                    return p1.getremainingbursttime() - p2.getremainingbursttime();
                }
                return p1.getarrivaltime() - p2.getarrivaltime();
            }
        });

        // Reset each process burst timing
        for (Process process : processes) {
            process.resetbursttime();
        }

        // Set up variables to track process completion and time;;
        int currentTime = 0;
        int lastProcessId = -1;
        List<Process> completedProcesses = new ArrayList<>();

        // Schedule again until every procedure is finished.;
        while (!completedProcesses.containsAll(processes)) {
            // Update the ready queue with the arrived processes.
            for (Process process : processes) {
                if (process.getarrivaltime() == currentTime && !readyQueue.contains(process) && !completedProcesses.contains(process)) {
                    readyQueue.add(process);
                }
            }

            // find the process that has the least amount of burst time left
            Process currentProcess = readyQueue.peek();

            if (currentProcess != null) {
                if (lastProcessId != currentProcess.getId()) {
                    lastProcessId = currentProcess.getId();
                }

                // Reduce the current process burst time.
                currentProcess.decrementbursttime(1);

                // verify that the procedure has finished running
                if (currentProcess.getremainingbursttime() == 0) {
                    readyQueue.poll();
                    currentProcess.setcompletetime(currentTime + 1);
                    currentProcess.calculatewaitetime();
                    currentProcess.calculateturnaroundtime();
                    completedProcesses.add(currentProcess);
                }
            }

            // increment the current time
            currentTime++;
        }

        // alculate avg turnaround time and avg waiting time
//        double averageTurnaroundTimeSRTF = completedProcesses.stream().mapToInt(Process::getturnaroundtime).average().orElse(0);
//        double averageWaitingTimeSRTF = completedProcesses.stream().mapToInt(Process::getwaitetime).average().orElse(0);
    }

    // simulate the Round Robin  alg
    public void simulateRR(int quantum) {
        // initialize variable for time and queues.
        int currentTime = 0;
        LinkedList<Process> readyQueue = new LinkedList<>();
        List<Process> completedProcesses = new ArrayList<>();

        // schedule again until every procedure is finished
        while (completedProcesses.size() < processes.size()) {
            // as processes come in add them to the ready queue;;
            for (Process process : processes) {
                if (!completedProcesses.contains(process) && process.getarrivaltime() <= currentTime && !readyQueue.contains(process)) {
                    readyQueue.add(process);
                }
            }

            if (!readyQueue.isEmpty()) {
                Process currentProcess = readyQueue.poll();

                // calculate  time slice for the current process;;...
                int timeSlice = Math.min(currentProcess.getremainingbursttime(), quantum);
                currentTime += timeSlice;
                currentProcess.decrementbursttime(timeSlice);

                // check if  process has finish execution ,,
                if (currentProcess.getremainingbursttime() == 0) {
                    currentProcess.setcompletetime(currentTime);
                    currentProcess.calculateturnaroundtime();
                    currentProcess.calculatewaitetime();
                    completedProcesses.add(currentProcess);
                } else {
                    // if the procedure is not finished  add it back to the end of the ready queue
                    readyQueue.add(currentProcess);
                }
            } else {
                // if there is no one in the ready queue forward the time.
                currentTime++;
            }
        }

        // calculate avg turnaround time and avg  waiting time ;;
//        double averageTurnaroundTimeRR = completedProcesses.stream().mapToDouble(Process::getturnaroundtime).average().orElse(0);
//        double averageWaitingTimeRR = completedProcesses.stream().mapToDouble(Process::getwaitetime).average().orElse(0);
    }

    // calculate the Avge Turnaround Time  for the given processes....
    public double calculateATT() {
        int totalTurnaroundTime = 0;
        for (Process process : processes) {
            totalTurnaroundTime += process.getturnaroundtime();
        }
        return (double) totalTurnaroundTime / processes.size();
    }

    // calculate the Avge Waiting Time  for the given processe;;
    public double calculateAWT() {
        int totalWaitingTime = 0;
        for (Process process : processes) {
            totalWaitingTime += process.getwaitetime();
        }
        return (double) totalWaitingTime / processes.size();
    }
}



/// end class ..................................................................................
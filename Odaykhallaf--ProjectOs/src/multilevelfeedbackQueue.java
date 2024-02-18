
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*A multilevel  feedback Queue scheduling algorithm is implemented in Java by the multilevel feedback Queue scheduling ....>
class Based on priority it divide processes into several queues each with a distinct time limit.....>
If a process is not finish within its time slice.....>
it is demoted from it position in the highest priority queue The class illustrates a...|>
typical operating system process scheduling strategy by calculating and displaying the average waiting and turnaround time
for the processes following execution.;;*/


public class multilevelfeedbackQueue {

// Separate fields for processes quantum times and queues;

    private List<Queue<Process>> queues;   // a list of queues categorized by priority.


    // Time slice arrays for every queue;
    private int[] quantumTimes;

    private List<Process> processes;       // a list of every procedure;


// Constructor to set  scheduler initial quantum timings..;

    public multilevelfeedbackQueue(int[] quantumTimes) {
        this.quantumTimes = quantumTimes;
        queues = new ArrayList<>();
        for (int i = 0; i < quantumTimes.length; i++) {
            queues.add(new LinkedList<>());
        }
        processes = new ArrayList<>();
    }

    public void addProcess(Process process) {
        queues.get(0).offer(process);
    }

    public void multilevelfeedbackQueuesimulate() {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("************* MultilevelFeedbackQueue  ****************");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("| iteration |     Average Turnaround Time     |     Average Waiting Time     |");
        System.out.println("--------------------------------------------------------------------------------");

        int[] iterations = {100, 1000, 10000, 100000};

        for (int iteration : iterations) {
            for (int j = 0; j < iteration; j++) {
                Process process = new Process(j, 0, (int) (Math.random() * 10) + 1);
                addProcess(process);
            }

            int currentTime = 0;

            for (int i = 0; i < quantumTimes.length; i++) {
                while (!queues.get(i).isEmpty()) {
                    Process process = queues.get(i).poll();

                    if (process.getArrivalTime() <= currentTime) {
                        int timeSlice = Math.min(process.getRemainingBurstTime(), quantumTimes[i]);
                        currentTime += timeSlice;
                        process.decrementBurstTime(timeSlice);

                        if (process.getRemainingBurstTime() > 0 && i < quantumTimes.length - 1) {
                            queues.get(i + 1).offer(process);
                        } else {
                            process.setFinishTime(currentTime);
                            processes.add(process);
                        }
                    } else {
                        queues.get(i).offer(process);
                        currentTime++;
                    }
                }
            }

            for (Process process : processes) {
                int turnaroundTime = process.getFinishTime() - process.getArrivalTime();
                int waitingTime = turnaroundTime - process.getBurstTime();
                totalTurnaroundTime += turnaroundTime;
                totalWaitingTime += waitingTime;
            }

            double averageTurnaroundTime = (double) totalTurnaroundTime / iteration;
            double averageWaitingTime = (double) totalWaitingTime / iteration;

            System.out.printf("| %-9d |     %-10.2f                  |              %-10.2f      |\n", iteration, averageTurnaroundTime, averageWaitingTime);

            totalWaitingTime = 0;
            totalTurnaroundTime = 0;
            processes.clear();
        }
    }}


import java.util.*;
import java.util.List;

/*The class   is used to simulate and compare several CPU scheduling methods, including Round Robin, multilevel feedback Queue, First Come First Serve, and Shortest Remaining Time First.
        It provides a tool to assess and examine the effectiveness of different scheduling techniques by creating random processes,
        running these algorithms, and displaying their performance in terms of average waiting and turnaround times.*/


    // Main class to simulate CPU scheduling algorithms;;
    public class Main {


        public static void main(String[] args) {
            // Define the round robin algorithm time quantum

            int quantum = 20;
            // define quantum times for multilevel feedback Queue algorithm
            int[] quantumTimes = {5, 10, 15};

// make cpu scheduler instances for various algorithms;

            SimulateTheAlgorithms scheduler = new SimulateTheAlgorithms(quantum);
            multilevelfeedbackQueue scheduler2 = new multilevelfeedbackQueue(quantumTimes);

            // define iterations for simulation in algo;;
            int[] iterations = {100, 1000, 10000, 100000};

            // simulate and print result for algo :  Round Robin,  First Come First Serve, and Shortest Remaining Time First algorithm
            printResultsForAlgorithm("First Come First Serve", iterations, scheduler);
            printResultsForAlgorithm("Shortest Remaining Time First", iterations, scheduler);
            printResultsForAlgorithm("Round Robin", iterations, scheduler, quantum);

            // add processes to multilevel feedback Queue  algo scheduler
            scheduler2.addProcess(new Process(1, 0, 10));
            scheduler2.addProcess(new Process(2, 2, 5));
            scheduler2.addProcess(new Process(3, 4, 8));

            // run multilevelfeedbackQueue scheduler
            scheduler2.multilevelfeedbackQueuesimulate();
            System.out.println("------------------------------------------------------------------------------------------   ");
            // print exit message when finsh the simulation for algo ...
            System.out.println("                                         ");

            System.out.println("   ////                                Process finished                     ////   ");
        }

        // technique for simulating  scheduling process and gathering data;;
        static Results simulationForAlgo(SimulateTheAlgorithms scheduler, int iterations, String algorithmType, Integer... quantum) {
            Results results = new Results(algorithmType);  // to store results create  Results object...

            for (int i = 0; i < iterations; i++) {
                List<Process> processes = generateProcesses(8);  // generate random processes;;
                scheduler.setProcesses(processes);  // configure  scheduler processes

                int[] quantumTimes = {5, 10, 15};
                multilevelfeedbackQueue scheduler2 = new multilevelfeedbackQueue(quantumTimes);

                // change to run the relevant algorithm ..>

                switch (algorithmType) {
                    case "First Come First Serve":
                        scheduler.simulateFirstComeFirstServe();
                        break;
                    case "Shortest Remaining Time First":
                        scheduler.simulateShortestRemainingTimeFirst();
                        break;
                    case "Round Robin":
                        scheduler.simulateRR(quantum[0]);
                        break;
                    case "Multilevel Feedback Queue":
                        scheduler2.multilevelfeedbackQueuesimulate();
                        break;
                }

                // Compile the current iteration findings..;

                results.addresult(scheduler.calculateATT(), scheduler.calculateAWT());
            }
            return results; // provide  gathered data back
        }

        //  print a results table heading

        static void printResultsTableHeader(String algorithmName) {


            System.out.println("************************************* " + algorithmName + " ************************************* ");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("| Iteration |  Average Turnaround Time |  Average Waiting Time    |");
            System.out.println("--------------------------------------------------------------------------------");

        }

        // print the outcomes for a certain iteration
        static void printAlgorithmResults(Results results, int iter) {
            System.out.printf("| %-9d |       %-10.2f         |               %-10.2f |\n", iter, results.getaverageatt(), results.getaverageawt());
            if (iter == 100000) {
                System.out.println("**********************************************************8");
            }
        }

        //  print an algorithm whole results table
        static void printResultsForAlgorithm(String algorithmName, int[] iterations, SimulateTheAlgorithms scheduler, Integer... quantum) {
            printResultsTableHeader(algorithmName);  // print header
            for (int iter : iterations) {
                Results results = new Results(algorithmName);  // for each iteration create a results objec;;

                results = simulationForAlgo(scheduler, iter, algorithmName, quantum);  //  the simulation and get the results

                printAlgorithmResults(results, iter);  // print results for each iteration
            }
            System.out.println("--------------------------------------------------------------------");
        }

//  compile a list of arbitrary processes

        static List<Process> generateProcesses(int counter) {
            // create  empty list for store processe;;..>
            List<Process> processes = new ArrayList<>();

            // make a generator of random numbers
            Random random = new Random();

            // establish a process maximum arrival time
            int maxarrivaltime = 50;

            // for create processes iterate counter time
            for (int i = 0; i < counter; i++) {
                // Create a random burst time duration ranging 5 and 100


                int bursttime = 5 + random.nextInt(96);


                // create a randomized arrival time ranging from 0 to maxarrivaltime.


                int arrivalTime = random.nextInt(maxarrivaltime);

                // using the generated values create a new Process object
                processes.add(new Process(i, bursttime, arrivalTime));
            }

            // provide the produced processes list back;;
            return processes;
        }

    }

//// end class
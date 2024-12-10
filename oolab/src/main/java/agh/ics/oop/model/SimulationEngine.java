package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    ExecutorService executorService = null;
    List<Simulation> simulations;
    List<Thread> simulationThreads = new ArrayList<>();

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() throws InterruptedException {
        for (Simulation simulation : simulations) {
            Thread simulationThread = new Thread(simulation);
            simulationThreads.add(simulationThread);
            simulationThread.start();
        }

        this.awaitSimulationsEnd();
    }

    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread simulationThread : simulationThreads) {
            simulationThread.join();
        }

        if (executorService != null) {
            executorService.shutdown();
            if (!executorService.awaitTermination(10,TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        }
    }

    public void runAsyncInThreadPool() throws InterruptedException {
        executorService = Executors.newFixedThreadPool(4);

        for (Simulation simulation : simulations) {
            executorService.submit(simulation);
        }

        this.awaitSimulationsEnd();
    }
}

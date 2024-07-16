package org.example.сoncurrencySynchronization;

import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final int numberOfTasks;
    private final ExecutorService executorService;
    private final CyclicBarrier barrier;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.executorService = Executors.newFixedThreadPool(numberOfTasks);
        this.barrier = new CyclicBarrier(numberOfTasks, this::combineResults);
    }

    public void executeTasks() {
        for (int i = 1; i <= numberOfTasks; i++) {
            int taskId = i;
            executorService.submit(() -> {
                try {
                    ComplexTask task = new ComplexTask(taskId);
                    task.execute();
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executorService.shutdown();
    }

    private void combineResults() {
        System.out.println("Все задачи выполнены, объединение результатов...");
    }

    public static void main(String[] args) {
        ComplexTaskExecutor executor = new ComplexTaskExecutor(5);
        executor.executeTasks();
    }
}

package org.example.concurrency.concurrencySyn;

public class ComplexTask {
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public void execute() {
        System.out.println("Задача " + taskId + " выполняется...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Задача " + taskId + " выполнена.");
    }
}

package org.example.forkJoinPoolExample;

import java.util.concurrent.RecursiveTask;

class FactorialTask extends RecursiveTask<Long> {
    private final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        System.out.println("Thread: " + Thread.currentThread().getName() + ", n = " + n);
        if (n <= 1) {
            return 1L;
        }

        FactorialTask subTask = new FactorialTask(n - 1);
        subTask.fork();

        long result = n * subTask.join();

        return result;
    }
}
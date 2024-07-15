package org.example.concurrentBank;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {
    private final Map<Integer, BankAccount> accounts = new HashMap<>();
    private int accountCounter = 0;
    private final Lock bankLock = new ReentrantLock();

    public BankAccount createAccount(double initialBalance) {
        bankLock.lock();
        try {
            int accountNumber = accountCounter++;
            BankAccount account = new BankAccount(accountNumber, initialBalance);
            accounts.put(accountNumber, account);
            return account;
        } finally {
            bankLock.unlock();
        }
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        Lock firstLock, secondLock;

        if (fromAccount.getAccountNumber() < toAccount.getAccountNumber()) {
            firstLock = fromAccount.getLock();
            secondLock = toAccount.getLock();
        } else {
            firstLock = toAccount.getLock();
            secondLock = fromAccount.getLock();
        }

        firstLock.lock();
        secondLock.lock();
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        } finally {
            secondLock.unlock();
            firstLock.unlock();
        }
    }

    public double getTotalBalance() {
        bankLock.lock();
        try {
            return accounts.values().stream().mapToDouble(BankAccount::getBalance).sum();
        } finally {
            bankLock.unlock();
        }
    }
}

package org.example.counter;

public class Counter implements Runnable {

    private int count = 0;  // 상태를 갖도록 하기 위한 용도

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getValue() {
        return count;
    }

    @Override
    public void run() {
        synchronized (this){
            this.increment();
            System.out.println("Value for Thread After increment " + Thread.currentThread().getName()+ " "+this.getValue());        // 1
            this.decrement();
            System.out.println("Value for Thread After decrement " + Thread.currentThread().getName()+ " "+this.getValue());        // 0
        }
    }
}

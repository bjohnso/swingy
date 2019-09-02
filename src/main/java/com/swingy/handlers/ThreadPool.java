package com.swingy.handlers;

import com.swingy.id.IDAssigner;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPool extends ThreadGroup{

    private static final IDAssigner poolID = new IDAssigner(1);

    private boolean alive;
    private List<Runnable> taskQueue;
    private int id;

    public ThreadPool(int numThreads) {
        super("ThreadPool-" + poolID.next());
        this.id = poolID.getCurrentID();
        setDaemon(true);
        taskQueue = new LinkedList<>();
        alive = true;
        for (int i = 0; i < numThreads; i++){
            new PooledThread(this).start();
        }
    }

    public synchronized void runTask(Runnable task){
        if (!(alive))
            throw new IllegalStateException("ThreadPool-" + id +" is dead");
        if (task != null){
            taskQueue.add(task);
            notify();
        }
    }

    public synchronized void close(){
        if (!alive)
            return ;
        alive = false;
        taskQueue.clear();
        interrupt();
    }

    public void join(){
        synchronized (this){
            alive = false;
            notifyAll();
        }

        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);

        for (int i = 0; i < count; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected synchronized Runnable getTask(){
        while (taskQueue.size() == 0){
            if (!alive)
                return null;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return taskQueue.remove(0);
    }
}

package com.swingy.handlers;

import com.swingy.id.IDAssigner;

public class PooledThread extends Thread{

    private static final IDAssigner threadID = new IDAssigner(1);
    private ThreadPool pool;

    public PooledThread(ThreadPool pool){
        super(pool, "PooledThread-" + threadID.next());
        this.pool = pool;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            Runnable task = null;
                task = pool.getTask();

            if (task == null)
                return ;

            try {
                task.run();
            }catch (Throwable t){
                pool.uncaughtException(this, t);
            }
        }
    }
}

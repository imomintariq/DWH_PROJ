package ETL;

import java.util.concurrent.ConcurrentLinkedQueue;

public class DataQueue {
    private final ConcurrentLinkedQueue iQueue = new ConcurrentLinkedQueue();
    private final ConcurrentLinkedQueue outQueue = new ConcurrentLinkedQueue();
    private DataQueue(){}
    private static final DataQueue INSTANCE = new DataQueue();


    public static ConcurrentLinkedQueue getInputDataQueue(){
        return INSTANCE.iQueue;
    }
    public static ConcurrentLinkedQueue getOutputDataQueue(){
        return INSTANCE.outQueue;
    }
}

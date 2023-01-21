package space.commandf1.practice.queue;

import java.util.HashSet;
import java.util.Set;

public class QueueManager {
    // private static final Set<Queue> queues = new ArrayList<>();
    private static final Set<Queue> queues = new HashSet<>();

    public static Set<Queue> getQueues() {
        return queues;
    }
}

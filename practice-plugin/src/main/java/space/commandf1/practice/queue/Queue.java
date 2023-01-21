package space.commandf1.practice.queue;

import org.bukkit.entity.Player;

public class Queue {
    private final Player player;
    private final QueueType type;

    public Queue(Player player, QueueType type) {
        this.player = player;
        this.type = type;
    }

    public void disband() {
        QueueManager.getQueues().remove(this);
    }

    public Player getPlayer() {
        return player;
    }

    public QueueType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return this.player.hashCode() + this.type.hashCode();
    }

    @Override
    public String toString() {
        // return this.player.toString();
        return "Queue{player=" + this.player.getName() +", type=" + this.type.toString() +"}";
    }

    @Override
    public boolean equals(Object obj) {
        // return this.player.equals(obj);
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Queue)) {
            return false;
        }

        Queue object = (Queue) obj;
        return object.getPlayer().equals(this.player) && object.getType().equals(this.type);
    }
}

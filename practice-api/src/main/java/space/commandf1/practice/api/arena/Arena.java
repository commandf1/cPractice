package space.commandf1.practice.api.arena;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.List;

@SuppressWarnings("unused")
public interface Arena {
    Location getPos1();
    void setPos1(Location pos1);
    Location getPos2();
    void setPos2(Location pos2);
    Location getCentre();
    void setCentre(Location centre);
    List<Block> getBlocks(Location pos1, Location pos2);
    String getName();
    void setName(String name);
}

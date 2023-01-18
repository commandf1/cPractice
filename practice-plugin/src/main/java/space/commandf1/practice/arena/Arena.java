package space.commandf1.practice.arena;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Arena implements Serializable, space.commandf1.practice.api.arena.Arena {
    private String name;
    private Location pos1, pos2, centre;

    public Arena(String name, Location pos1, Location pos2, Location centre) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.centre = centre;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("all")
    @Override
    public List<Block> getBlocks(Location pos1, Location pos2)
    {
        if(pos1.getWorld() != pos2.getWorld())
            return null;
        World world = pos1.getWorld();
        List<Block> blocks = new ArrayList<>();
        int x1 = pos1.getBlockX();
        int y1 = pos1.getBlockY();
        int z1 = pos1.getBlockZ();

        int x2 = pos2.getBlockX();
        int y2 = pos2.getBlockY();
        int z2 = pos2.getBlockZ();

        int lowestX = Math.min(x1, x2);
        int lowestY = Math.min(y1, y2);
        int lowestZ = Math.min(z1, z2);

        int highestX = lowestX == x1 ? x2 : x1;
        int highestY = lowestX == y1 ? y2 : y1;
        int highestZ = lowestX == z1 ? z2 : z1;

        for(int x = lowestX; x <= highestX; x++) {
            for (int y = lowestY; x <= highestY; y++) {
                for (int z = lowestZ; x <= highestZ; z++) {
                    blocks.add(world.getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

    @Override
    public Location getPos1() {
        return pos1;
    }

    @Override
    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    @Override
    public Location getPos2() {
        return pos2;
    }

    @Override
    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    @Override
    public Location getCentre() {
        return centre;
    }

    @Override
    public void setCentre(Location centre) {
        this.centre = centre;
    }
}

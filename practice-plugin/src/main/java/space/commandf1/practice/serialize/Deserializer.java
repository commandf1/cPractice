package space.commandf1.practice.serialize;

import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Deserializer {
    private final File path;

    public Deserializer(File file) {
        this.path = file;
    }

    public Object read() {
        try (BukkitObjectInputStream inputStream = new BukkitObjectInputStream(Files.newInputStream(path.toPath()))) {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getPath() {
        return path;
    }
}

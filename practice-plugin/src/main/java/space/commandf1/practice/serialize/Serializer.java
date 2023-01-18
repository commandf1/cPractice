package space.commandf1.practice.serialize;

import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Serializer {
    private final Object object;

    public <T> Serializer(T obj) {
        this.object = obj;
    }

    public void writeTo(File file) {
        try (BukkitObjectOutputStream objectOutputStream = new BukkitObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            objectOutputStream.writeObject(this.object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getObject() {
        return object;
    }
}

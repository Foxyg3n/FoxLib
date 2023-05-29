package me.foxyg3n.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.foxyg3n.utils.adapters.ConfigurationSerializableAdapter;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

/**
 * Class for saving Bukkit data
 */
public abstract class JsonDataSaver {

    private static Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization()
            .registerTypeHierarchyAdapter(ConfigurationSerializable.class, new ConfigurationSerializableAdapter())
            .create();
    private final File file;

    /**
     * Updates gson used to save/load data by {@link JsonDataSaver}
     *
     * @param function gives {@link GsonBuilder} context for updating {@link Gson} options and saves updated {@link Gson}
     */
    public static void updateGson(Function<GsonBuilder, Gson> function) {
        gson = function.apply(gson.newBuilder());
    }

    /**
     * Creates the DataSaver instance
     * This creates the folder paths and the file itself if they don't exist
     *
     * @param plugin plugin instance for getting its data folder in which the file will be saved
     * @param fileName name of the file to be saved
     */
    public JsonDataSaver(JavaPlugin plugin, String fileName) {
        this(plugin, fileName, false);
    }

    /**
     * Creates the DataSaver instance
     * This creates the folder paths and the file itself if they don't exist
     *
     * @param plugin plugin instance for getting its data folder in which the file will be saved
     * @param fileName name of the file to be saved
     * @param prettyPrinting whether json in the file should be pretty printed
     */
    public JsonDataSaver(JavaPlugin plugin, String fileName, boolean prettyPrinting) {
        this(new File(plugin.getDataFolder(), fileName.endsWith(".json") ? fileName : fileName + ".json"), prettyPrinting);
    }

    /**
     * Creates the DataSaver instance
     * This creates the folder paths and the file itself if they don't exist
     *
     * @param file file to save/load from
     */
    public JsonDataSaver(File file) {
        this(file, false);
    }

    /**
     * Creates the DataSaver instance
     * This creates the folder paths and the file itself if they don't exist
     *
     * @param file file to save/load from
     * @param prettyPrinting whether json in the file should be pretty printed
     */
    public JsonDataSaver(File file, boolean prettyPrinting) {
        try {
            if(prettyPrinting) gson = gson.newBuilder().setPrettyPrinting().create();
            this.file = file;
            this.file.getParentFile().mkdirs();
            if(!this.file.createNewFile()) load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new SecurityException(e);
        }
    }

    /**
     * This should save your object to file.
     * Use with {@link #toFile(Object)} to save the object
     */
    public abstract void save();
    /**
     * This should load your object from file.
     * Use with {@link #fromFile(TypeToken)} to load the object
     */
    public abstract void load();

    /**
     * Saves desired object to file
     *
     * @param object object to be saved
     */
    protected void toFile(Object object) {
        try(FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(object, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads desired object from the file
     *
     * @param clazz object's class (i.e Integer.class)
     * @return desired object
     * @param <T> object's class type
     */
    protected <T> Optional<T> fromFile(Class<T> clazz) {
        return fromFile(TypeToken.get(clazz));
    }

    /**
     * Loads desired object from the file
     *
     * @param token token of the object class.
     * If you try to save i.e. a {@code HashMap<String, Integer>}, the token should look like this:
     * <pre style="color:orange;">new TypeToken&#x3c;HashMap&#x3c;String, Integer&#x3e;&#x3e;()</pre>
     * @return desired object
     * @param <T> object class type
     */
    protected <T> Optional<T> fromFile(TypeToken<T> token) {
        try(FileReader fileReader = new FileReader(file)) {
            return Optional.ofNullable(gson.fromJson(fileReader, token));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

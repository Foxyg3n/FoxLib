package me.foxyg3n.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;

public abstract class PersistentDataUtils {

    private final HashMap<Class<?>, PersistentDataType<?, ?>> tagTypes = new HashMap<>();
    private final JavaPlugin plugin;

    public PersistentDataUtils(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;

        registerTagType(PersistentDataType.BYTE);
        registerTagType(PersistentDataType.BYTE_ARRAY);
        registerTagType(PersistentDataType.DOUBLE);
        registerTagType(PersistentDataType.FLOAT);
        registerTagType(PersistentDataType.INTEGER);
        registerTagType(PersistentDataType.INTEGER_ARRAY);
        registerTagType(PersistentDataType.LONG);
        registerTagType(PersistentDataType.LONG_ARRAY);
        registerTagType(PersistentDataType.SHORT);
        registerTagType(PersistentDataType.STRING);
    }

    public void registerTagType(PersistentDataType<?, ?> tagType) {
        if(tagType == null) throw new IllegalArgumentException("Tag type cannot be null");
        tagTypes.put(tagType.getComplexType(), tagType);
    }

    public void addTag(ItemStack itemStack, String tag) {
        if(itemStack == null) throw new IllegalArgumentException("ItemStack cannot be null");
        if(tag == null) throw new IllegalArgumentException("Tag cannot be null");
        NamespacedKey namespacedKey = new NamespacedKey(plugin, tag);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(namespacedKey, getTagType(String.class), "");
        itemStack.setItemMeta(itemMeta);
    }

    public void removeTag(ItemStack itemStack, String tag) {
        if(itemStack == null) throw new IllegalArgumentException("ItemStack cannot be null");
        if(tag == null) throw new IllegalArgumentException("Tag cannot be null");
        NamespacedKey namespacedKey = new NamespacedKey(plugin, tag);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().remove(namespacedKey);
        itemStack.setItemMeta(itemMeta);
    }

    public boolean hasTag(ItemStack itemStack, String tag) {
        if(itemStack == null) throw new IllegalArgumentException("ItemStack cannot be null");
        if(tag == null) throw new IllegalArgumentException("Tag cannot be null");
        NamespacedKey namespacedKey = new NamespacedKey(plugin, tag);
        return itemStack.getItemMeta().getPersistentDataContainer().has(namespacedKey, getTagType(String.class));
    }

    public <T> void storeValue(ItemStack itemStack, String key, T value) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        storeValue(itemMeta, key, value);
        itemStack.setItemMeta(itemMeta);
    }

    public <T> void storeValue(PersistentDataHolder holder, String key, T value) {
        if(holder == null) throw new IllegalArgumentException("Holder cannot be null");
        if(key == null) throw new IllegalArgumentException("Key cannot be null");
        if(value == null) throw new IllegalArgumentException("Value cannot be null");
        if(!tagTypes.containsKey(value.getClass())) throw new IllegalArgumentException("Tag type not registered for class " + value.getClass().getName());
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        holder.getPersistentDataContainer().set(namespacedKey, getTagType(value), value);
    }

    public void removeValue(ItemStack itemStack, String key) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        removeValue(itemMeta, key);
        itemStack.setItemMeta(itemMeta);
    }

    public void removeValue(PersistentDataHolder holder, String key) {
        if(holder == null) throw new IllegalArgumentException("Holder cannot be null");
        if(key == null) throw new IllegalArgumentException("Key cannot be null");
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        holder.getPersistentDataContainer().remove(namespacedKey);
    }

    public <T> T getValue(ItemStack itemStack, String key, Class<T> type) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return getValue(itemMeta, key, type);
    }

    public <T> T getValue(PersistentDataHolder holder, String key, Class<T> type) {
        if(holder == null) throw new IllegalArgumentException("Holder cannot be null");
        if(key == null) throw new IllegalArgumentException("Key cannot be null");
        if(type == null) throw new IllegalArgumentException("Value type cannot be null");
        if(!tagTypes.containsKey(type)) throw new IllegalArgumentException("Tag type not registered for class " + type.getName());
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        return holder.getPersistentDataContainer().get(namespacedKey, getTagType(type));
    }

    public <T> boolean hasValue(ItemStack itemStack, String key, Class<T> type) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return hasValue(itemMeta, key, type);
    }

    public <T> boolean hasValue(PersistentDataHolder holder, String key, Class<T> type) {
        if(holder == null) throw new IllegalArgumentException("Holder cannot be null");
        if(key == null) throw new IllegalArgumentException("Key cannot be null");
        if(type == null) throw new IllegalArgumentException("Value type cannot be null");
        if(!tagTypes.containsKey(type)) throw new IllegalArgumentException("Tag type not registered for class " + type.getName());
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        return holder.getPersistentDataContainer().has(namespacedKey, getTagType(type));
    }

    @SuppressWarnings("unchecked")
    private <T> PersistentDataType<?, T> getTagType(@Nullable T object) {
        return (PersistentDataType<?, T>) tagTypes.get(object.getClass());
    }

    @SuppressWarnings("unchecked")
    private <T> PersistentDataType<?, T> getTagType(@Nullable Class<T> clazz) {
        return (PersistentDataType<?, T>) tagTypes.get(clazz);
    }
    
}

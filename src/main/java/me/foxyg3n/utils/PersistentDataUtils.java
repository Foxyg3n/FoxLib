package me.foxyg3n.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.HashMap;

public abstract class PersistentDataUtils {

    private static final HashMap<Class<?>, PersistentDataType<?, ?>> tagTypes = new HashMap<>();
    private static final String NAMESPACE = "data";

    static {
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

    public static void registerTagType(PersistentDataType<?, ?> tagType) {
        if(tagType == null) throw new IllegalArgumentException("Tag type cannot be null");
        tagTypes.put(tagType.getComplexType(), tagType);
    }

    public static void addTag(ItemStack itemStack, String tag) {
        if(itemStack == null) throw new IllegalArgumentException("ItemStack cannot be null");
        if(tag == null) throw new IllegalArgumentException("Tag cannot be null");
        NamespacedKey namespacedKey = new NamespacedKey(NAMESPACE, tag.toLowerCase());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(namespacedKey, getTagType(String.class), "");
        itemStack.setItemMeta(itemMeta);
    }

    public static void removeTag(ItemStack itemStack, String tag) {
        if(itemStack == null) throw new IllegalArgumentException("ItemStack cannot be null");
        if(tag == null) throw new IllegalArgumentException("Tag cannot be null");
        NamespacedKey namespacedKey = new NamespacedKey(NAMESPACE, tag.toLowerCase());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().remove(namespacedKey);
        itemStack.setItemMeta(itemMeta);
    }

    public static boolean hasTag(ItemStack itemStack, String tag) {
        if(itemStack == null) throw new IllegalArgumentException("ItemStack cannot be null");
        if(tag == null) throw new IllegalArgumentException("Tag cannot be null");
        NamespacedKey namespacedKey = new NamespacedKey(NAMESPACE, tag.toLowerCase());
        return itemStack.getItemMeta().getPersistentDataContainer().has(namespacedKey, getTagType(String.class));
    }

    public static <T> void storeValue(ItemStack itemStack, String key, T value) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        storeValue(itemMeta, key.toLowerCase(), value);
        itemStack.setItemMeta(itemMeta);
    }

    public static <T> void storeValue(PersistentDataHolder holder, String key, T value) {
        if(holder == null) throw new IllegalArgumentException("Holder cannot be null");
        if(key == null) throw new IllegalArgumentException("Key cannot be null");
        if(value == null) throw new IllegalArgumentException("Value cannot be null");
        if(!tagTypes.containsKey(value.getClass())) throw new IllegalArgumentException("Tag type not registered for class " + value.getClass().getName());
        NamespacedKey namespacedKey = new NamespacedKey(NAMESPACE, key.toLowerCase());
        holder.getPersistentDataContainer().set(namespacedKey, getTagType(value), value);
    }

    public static void removeValue(ItemStack itemStack, String key) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        removeValue(itemMeta, key.toLowerCase());
        itemStack.setItemMeta(itemMeta);
    }

    public static void removeValue(PersistentDataHolder holder, String key) {
        if(holder == null) throw new IllegalArgumentException("Holder cannot be null");
        if(key == null) throw new IllegalArgumentException("Key cannot be null");
        NamespacedKey namespacedKey = new NamespacedKey(NAMESPACE, key.toLowerCase());
        holder.getPersistentDataContainer().remove(namespacedKey);
    }

    public static <T> T getValue(ItemStack itemStack, String key, Class<T> type) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return getValue(itemMeta, key.toLowerCase(), type);
    }

    public static <T> T getValue(PersistentDataHolder holder, String key, Class<T> type) {
        if(holder == null) throw new IllegalArgumentException("Holder cannot be null");
        if(key == null) throw new IllegalArgumentException("Key cannot be null");
        if(type == null) throw new IllegalArgumentException("Value type cannot be null");
        if(!tagTypes.containsKey(type)) throw new IllegalArgumentException("Tag type not registered for class " + type.getName());
        NamespacedKey namespacedKey = new NamespacedKey(NAMESPACE, key.toLowerCase());
        return holder.getPersistentDataContainer().get(namespacedKey, getTagType(type));
    }

    public static <T> boolean hasValue(ItemStack itemStack, String key, Class<T> type) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return hasValue(itemMeta, key.toLowerCase(), type);
    }

    public static <T> boolean hasValue(PersistentDataHolder holder, String key, Class<T> type) {
        if(holder == null) throw new IllegalArgumentException("Holder cannot be null");
        if(key == null) throw new IllegalArgumentException("Key cannot be null");
        if(type == null) throw new IllegalArgumentException("Value type cannot be null");
        if(!tagTypes.containsKey(type)) throw new IllegalArgumentException("Tag type not registered for class " + type.getName());
        NamespacedKey namespacedKey = new NamespacedKey(NAMESPACE, key.toLowerCase());
        return holder.getPersistentDataContainer().has(namespacedKey, getTagType(type));
    }

    @SuppressWarnings("unchecked")
    private static <T> PersistentDataType<?, T> getTagType(@Nullable T object) {
        return (PersistentDataType<?, T>) tagTypes.get(object.getClass());
    }

    @SuppressWarnings("unchecked")
    private static <T> PersistentDataType<?, T> getTagType(@Nullable Class<T> clazz) {
        return (PersistentDataType<?, T>) tagTypes.get(clazz);
    }
    
}

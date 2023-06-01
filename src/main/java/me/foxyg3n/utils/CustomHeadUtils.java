package me.foxyg3n.utils;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

/**
 * Utils for custom heads with use of this site <a href="https://minecraft-heads.com">minecraft-heads.com</a>
 */
public abstract class CustomHeadUtils {

    /**
     * Returns custom head item with provided texture
     *
     * @param texture Texture value used for custom head texture
     * (Choose a head from <a href="https://minecraft-heads.com">minecraft-heads.com</a>, scroll down and copy 'Value' string)
     * @return Custom head with texture
     */
    public static ItemStack getCustomHead(String texture) {
        ItemStack customHead = new ItemStack(Material.PLAYER_HEAD);
        if(texture == null || texture.isEmpty()) return customHead;
        SkullMeta headMeta = (SkullMeta) customHead.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures", new Property("textures", texture));
        try {
            assert headMeta != null;
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, gameProfile);
        } catch(NoSuchFieldException | IllegalArgumentException | IllegalAccessException ignored) {}
        customHead.setItemMeta(headMeta);
        return customHead;
    }

}

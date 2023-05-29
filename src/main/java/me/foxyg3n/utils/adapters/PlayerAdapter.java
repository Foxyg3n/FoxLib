package me.foxyg3n.utils.adapters;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Type;
import java.util.UUID;

public class PlayerAdapter implements JsonSerializer<Player>, JsonDeserializer<Player> {

    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        UUID uuid = context.deserialize(obj.get("playerUUID"), UUID.class);
        return Bukkit.getPlayer(uuid);
    }

    @Override
    public JsonElement serialize(Player player, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.add("playerUUID", context.serialize(player.getUniqueId()));
        return obj;
    }

}

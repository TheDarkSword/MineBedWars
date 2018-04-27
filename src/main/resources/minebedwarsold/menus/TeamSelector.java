package minebedwarsold.menus;

import de.tr7zw.itemnbtapi.NBTItem;
import minebedwarsold.Main;
import minebedwarsold.enums.Message;
import minebedwarsold.objects.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Copyright © 2018 by Lorenzo Magni
 * This file is part of MineBedwars.
 * <p>
 * MineBedwars is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * MineBedwars is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with MineBedwars.  If not, see <http://www.gnu.org/licenses/>.
 */
public class TeamSelector {
    public void open(Player player) {
        player.openInventory(create());
    }

    private Inventory create() {
        ArrayList<ItemStack> items = new ArrayList<>();
        Inventory inventory;

        if(Main.gameHandler.getMap().getTeams().size() <= 9) {
            inventory = Bukkit.createInventory(null, 9, Message.TEAM_SELECTOR.get());
        } else {
            inventory = Bukkit.createInventory(null, 12, Message.TEAM_SELECTOR.get());
        }

        for(Team team : Main.gameHandler.getMap().getTeams()) {
            ItemStack item = new Wool(team.getName().getColor()).toItemStack(team.getSize());
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(Message.TEAM_NAME.get());
            itemMeta.setLore(Collections.singletonList(Message.CLICK_TO_JOIN.get()));
            item.setItemMeta(itemMeta);
            NBTItem nbtItem = new NBTItem(item);
            nbtItem.setString("team", team.getName().name());
            items.add(nbtItem.getItem());
        }

        for(int i = 0; i < items.size(); i++) {
            inventory.setItem(i, items.get(i));
        }

        return inventory;
    }
}

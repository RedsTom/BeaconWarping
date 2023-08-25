/*
 * Copyright (c) 2020-2022 lokka30. Use of this source code is governed by the MIT license that can be found in the
 * LICENSE.md file.
 * This class is bundled inside the MicroLib resource, a library purposed for Bukkit/SpigotMC plugin developers. Read
 *  more about the resource here: https://www.spigotmc.org/resources/microlib.84017/
 */

package me.redstom.beaconwarp.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

import static me.redstom.beaconwarp.common.TextConstants.ITEM_LORE_STYLE;
import static me.redstom.beaconwarp.common.TextConstants.ITEM_NAME_STYLE;

public class ItemBuilder {

    private final ItemStack item;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemBuilder(ItemStack item) {
        this.item = new ItemStack(item);
    }

    public ItemBuilder displayName(Component name) {
        return this.displayName(name, false);
    }

    public ItemBuilder displayName(Component name, boolean enforceStyle) {
        if (!enforceStyle) {
            name = name.style(ITEM_NAME_STYLE);
        }

        ItemMeta im = this.item.getItemMeta();
        im.displayName(name);
        item.setItemMeta(im);

        return this;
    }

    public ItemBuilder lore(Component... lore) {
        return lore(Arrays.asList(lore), false);
    }

    public ItemBuilder lore(List<Component> lore, boolean enforceStyle) {
        if (!enforceStyle) {
            lore = lore.stream().map(line -> line.style(ITEM_LORE_STYLE)).toList();
        }

        ItemMeta im = item.getItemMeta();
        im.lore(lore);
        item.setItemMeta(im);

        return this;
    }

    public ItemStack toItemStack() {
        return item;
    }
}

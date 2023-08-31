package me.redstom.beaconwarp.items;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import lombok.Getter;
import me.redstom.beaconwarp.inventories.ItemBuilder;
import me.redstom.beaconwarp.inventories.Menu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.translation.GlobalTranslator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public abstract class Item <T extends Menu<?>> {

    protected final GuiItem item;
    private final   T       menu;

    protected Item(T menu, Material material) {
        this.menu = menu;
        this.item = new GuiItem(new ItemStack(material));
    }

    protected abstract void init();

    protected abstract ItemBuilder update(ItemBuilder item);

    public void update() {
        ItemBuilder builder = new ItemBuilder(this.item.getItem());
        item.setItem(update(builder).toItemStack());

        menu.gui().update();
    }

    protected Component r(Component component) {
        return GlobalTranslator.render(component, menu.locale());
    }
}

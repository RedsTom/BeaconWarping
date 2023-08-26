package me.redstom.beaconwarp.text.utils;

import net.kyori.adventure.text.Component;

import java.util.function.UnaryOperator;

public abstract class TranslatableComponent {

    protected abstract Component build(ComponentArgs args);

    public Component build() {
        return build(new ComponentArgs());
    }

    public Component build(UnaryOperator<ComponentArgs> transformer) {
        return build(transformer.apply(new ComponentArgs()));
    }
}

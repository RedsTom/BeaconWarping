package me.redstom.beaconwarp.text.utils;

import java.util.HashMap;
import java.util.Map;

public class ComponentArgs {

    public static final ComponentArgs EMPTY = new ComponentArgs();

    private Map<ComponentArg<?>, Object> args;

    public ComponentArgs() {
        this.args = new HashMap<>();
    }

    public <U> ComponentArgs put(ComponentArg<U> key, U value) {
        args.put(key, value);
        return this;
    }

    public <U> U get(ComponentArg<U> key) {
        return (U) args.get(key);
    }
}

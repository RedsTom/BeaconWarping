package me.redstom.beaconwarp.orm.repositories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;

@Singleton

@Getter
public class Repositories {

    @Inject private UserRepository users;
    @Inject private WarpRepository warps;

}

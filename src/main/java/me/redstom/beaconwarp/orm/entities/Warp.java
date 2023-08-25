package me.redstom.beaconwarp.orm.entities;

import jakarta.persistence.*;
import lombok.*;
import org.bukkit.Location;
import org.bukkit.Material;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Warp {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int    id;
    private                                                     String name;

    private String world;

    private int x;
    private int y;
    private int z;

    private State state;
    private Side  side;

    private Material icon;

    @ManyToOne(fetch = FetchType.EAGER) private User user;

    public enum State {
        ENABLED,
        DISABLED
    }

    @AllArgsConstructor
    public enum Side {
        TOP(0, 1, 0, 180),
        NORTH(0, 0, -1, 180),
        EAST(1, 0, 0, -90),
        SOUTH(0, 0, 1, 0),
        WEST(-1, 0, 0, 90),
        ;

        private final int x;
        private final int y;
        private final int z;
        private final int yaw;

        public Location apply(Location location) {
            location.add(x, y, z);
            location.setYaw(yaw);

            return location;
        }
    }
}

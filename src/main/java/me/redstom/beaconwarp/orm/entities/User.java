package me.redstom.beaconwarp.orm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "BeaconUser")
public class User {

    @Id
    private UUID uniqueId;

    private boolean informed;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Warp> warps;

}

package me.redstom.beaconwarp.text.info;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;

@Singleton
@Getter
public class InfoMessages {

    @Inject private WarpExistenceNoticeTranslatableComponent warpExistenceNoticeMessage;

}

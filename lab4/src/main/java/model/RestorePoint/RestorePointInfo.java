package model.RestorePoint;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RestorePointInfo {
    private final List<File> fileList;
    private final UUID rpId;
    private final LocalDateTime creationTime;

    public RestorePointInfo(RestorePoint restorePoint) {
        fileList = restorePoint.getFileList();
        rpId = restorePoint.getRpId();
        creationTime = restorePoint.getCreationTime();
    }

    @Override
    public String toString() {
        return "Restore Point with such files:\n" + fileList +
                "\nid:" + rpId +
                " and creation: " + creationTime +
                "has been created.\n";
    }
}

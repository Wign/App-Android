package wign.android.wignivs.dk.wign;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Troels on 29/03/2017.
 */

public class Video extends RealmObject {
    private String videoID;
    private String description;
    private String thumb;
    private Date created_at;

    public Video() {
    }

    public Video(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb_url) {
        this.thumb = thumb_url;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String video_uuid) {
        this.videoID = video_uuid;
    }
}

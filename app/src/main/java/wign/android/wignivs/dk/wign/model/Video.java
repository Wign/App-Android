package wign.android.wignivs.dk.wign.model;

import android.net.Uri;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by Troels on 29/03/2017.
 */

public class Video extends RealmObject {
    private int ID;
    private String videoID;
    private String videoURI;
    private String description;
    private String thumb;
    @Index
    private Integer votes;
    private Date updated_at;

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

    public Uri getVideoURI() {
        return Uri.parse(getVideoURL());
    }

    public void setVideoURI(String videoURI) {
        this.videoURI = videoURI;
    }

    public Date getUpdated() {
        return updated_at;
    }

    public void setUpdated(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getVideoURL() {
        return "https:" + exchangeformat(videoURI, "360p-16x9");
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String exchangeformat(String source, String to) {
        return source.replace("/qvga/", "/"+to+"/");
    }
}

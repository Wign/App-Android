package wign.android.wignivs.dk.wign;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Troels on 29/03/2017.
 */

public class Video extends RealmObject {
    private Word word;
    @PrimaryKey
    private int id;
    private String desc;
    private String thumb_url;
    private String video_uuid;
    private Date created_at;

    public Video() {
    }

    public Video(Word word, String desc) {
        this.word = word;
        this.desc = desc;
        this.created_at = new Date();
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getVideo_uuid() {
        return video_uuid;
    }

    public void setVideo_uuid(String video_uuid) {
        this.video_uuid = video_uuid;
    }
}

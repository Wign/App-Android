package wign.android.wignivs.dk.wign;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Troels on 29/03/2017.
 */

public class Word extends RealmObject {
    @PrimaryKey
    private String word;
    private RealmList<Video> videos;

    public Word() {
    }

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public RealmList<Video> getList() {
        return videos;
    }

    @Override
    public String toString() {
        return word;
    }
}
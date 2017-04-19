package wign.android.wignivs.dk.wign;

import android.support.v7.widget.RecyclerView;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by Troels on 29/03/2017.
 */

public class Word extends RealmObject {
    @Index
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

    @Override
    public String toString() {
        return word;
    }
}
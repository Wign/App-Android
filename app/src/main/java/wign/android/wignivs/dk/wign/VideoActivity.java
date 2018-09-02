package wign.android.wignivs.dk.wign;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Case;
import io.realm.Realm;
import wign.android.wignivs.dk.wign.api.ApiService;
import wign.android.wignivs.dk.wign.api.ServiceGenerator;
import wign.android.wignivs.dk.wign.gotchas.getVideosAsyncTask;
import wign.android.wignivs.dk.wign.model.Word;

public class VideoActivity extends AppCompatActivity {
    public static final String QUERY_ID = "Query";
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();


        setContentView(R.layout.activity_fragment);

        Bundle bundle = getIntent().getExtras();
        String queryWord = bundle.getString(QUERY_ID);
        Word word = realm.where(Word.class).equalTo("word", queryWord, Case.INSENSITIVE).findFirst();

        if(word != null) {
            setTitle(word.getWord()); // Set the title to the word

            ApiService ApiService = ServiceGenerator.createService(ApiService.class);
            new getVideosAsyncTask(ApiService).execute(word.getWord());
        } else {
            setTitle("Word \"" + queryWord + "\" not found"); // Set the title to the word
        }

        FragmentManager fm = getSupportFragmentManager();

        Fragment videoFragment = fm.findFragmentById(R.id.fragment_container);
        if(videoFragment == null) {
            if (word != null) {
                videoFragment = new VideoFragment();
                videoFragment.setArguments(bundle); // Sending the query to the fragment as well
                fm.beginTransaction()
                        .add(R.id.fragment_container, videoFragment)
                        .commit();
            }
            else {
                videoFragment = new EmptyVideoFragment();
                fm.beginTransaction()
                        .add(R.id.fragment_container, videoFragment)
                        .commit();
            }
        }
    }

    public static Intent newIntent(Context packageContext, String query) {
        Intent i = new Intent(packageContext, VideoActivity.class);
        i.putExtra(QUERY_ID, query);
        return i;
    }
}

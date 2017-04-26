package wign.android.wignivs.dk.wign;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import wign.android.wignivs.dk.wign.api.*;
import wign.android.wignivs.dk.wign.gotchas.getVideosAsyncTask;

public class VideoActivity extends AppCompatActivity {
    private static final String QUERY_ID = "Query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Bundle bundle = getIntent().getExtras();
        String query = bundle.getString(QUERY_ID);
        if(query != null && !query.isEmpty()) {
            ApiService ApiService = ServiceGenerator.createService(ApiService.class);
            new getVideosAsyncTask(ApiService).execute(query);
        }

        FragmentManager fm = getSupportFragmentManager();

        Fragment videoFragment = fm.findFragmentById(R.id.fragment_container);
        if(videoFragment == null) {
            videoFragment = new VideoFragment();
            videoFragment.setArguments(bundle); // Sending the query to the fragment as well
            fm.beginTransaction()
                    .add(R.id.fragment_container, videoFragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context packageContext, String query) {
        Intent i = new Intent(packageContext, VideoActivity.class);
        i.putExtra(QUERY_ID, query);
        return i;
    }
}

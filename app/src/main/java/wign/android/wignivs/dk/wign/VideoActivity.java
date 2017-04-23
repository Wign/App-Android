package wign.android.wignivs.dk.wign;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VideoActivity extends AppCompatActivity {
    private static final String QUERY_ID = "Query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Bundle bundle = getIntent().getExtras();
        String query = bundle.getString(QUERY_ID);
        if(query != null && !query.isEmpty()) {
            ApiService ApiService = ServiceGenerator.createService(ApiService.class);
            new getVideosAsyncTask(ApiService).execute(query);
        }

        FragmentManager fm = getSupportFragmentManager();

        Fragment videoFragment = fm.findFragmentById(R.id.video_fragment);
        if(videoFragment == null) {
            videoFragment = new MainActivityFragment();
            videoFragment.setArguments(bundle);
            fm.beginTransaction()
                    .add(R.id.video_fragment, videoFragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context packageContext, String query) {
        Intent i = new Intent(packageContext, VideoActivity.class);
        i.putExtra(QUERY_ID, query);
        return i;
    }
}

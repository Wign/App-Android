package wign.android.wignivs.dk.wign;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        FragmentManager fm = getSupportFragmentManager();

        Fragment videoFragment = fm.findFragmentById(R.id.video_fragment);
        if(videoFragment == null) {
            videoFragment = new MainActivityFragment();
            videoFragment.setArguments(getIntent().getExtras());
            fm.beginTransaction()
                    .add(R.id.video_fragment, videoFragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context packageContext, String query) {
        Intent i = new Intent(packageContext, VideoActivity.class);
        i.putExtra("Query", query);
        return i;
    }
}

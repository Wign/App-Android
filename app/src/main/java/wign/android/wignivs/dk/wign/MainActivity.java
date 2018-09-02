package wign.android.wignivs.dk.wign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import wign.android.wignivs.dk.wign.api.ApiService;
import wign.android.wignivs.dk.wign.api.ServiceGenerator;
import wign.android.wignivs.dk.wign.gotchas.getAllWordsAsyncTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Async get all the words from Wign and fill Realm DB
        ApiService ApiService = ServiceGenerator.createService(ApiService.class);
        new getAllWordsAsyncTask(ApiService).execute();

        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();

        Fragment mainFragment = fm.findFragmentById(R.id.fragment_container);
        if(mainFragment == null) {
            mainFragment = new MainActivityFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, mainFragment)
                    .commit();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package wign.android.wignivs.dk.wign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        Fragment mainFragment = fm.findFragmentById(R.id.main_fragment);
        if(mainFragment == null) {
            mainFragment = new MainActivityFragment();
            fm.beginTransaction()
                    .add(R.id.main_fragment, mainFragment)
                    .commit();
        }

    }
}

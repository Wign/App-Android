package wign.android.wignivs.dk.wign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    Realm realm;

    private AutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();

        ApiService ApiService = ServiceGenerator.createService(ApiService.class);
        new getAllWordsAsyncTask(ApiService).execute(); // Async get all the words from Wign and fill Realm DB

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        actv = (AutoCompleteTextView) findViewById(R.id.search_sign);

        /* @Todo: Autocompleting the input area
        RealmResults<Word> words = realm.where(Word.class).findAllAsync();
        if(words.isLoaded()) {
            System.out.println("Got the words!");
            System.out.println(words.toString());
            WordAutoCompleteAdapter wordAdapter = new WordAutoCompleteAdapter(this, words) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    return null;
                }
            };
            actv.setAdapter(wordAdapter);
        }
        */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

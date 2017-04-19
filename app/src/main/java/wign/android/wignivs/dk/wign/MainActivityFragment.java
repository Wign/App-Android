package wign.android.wignivs.dk.wign;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private Realm realm;
    private Button btn;
    private RecyclerView recyclerView;
    private AutoCompleteTextView actv;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();
        System.out.println("path: " + realm.getPath());

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ApiService ApiService = ServiceGenerator.createService(ApiService.class);
        new getAllWordsAsyncTask(ApiService).execute(); // Async get all the words from Wign and fill Realm DB

        btn = (Button) v.findViewById(R.id.btn_find_sign);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = actv.getText().toString();
                Intent i = VideoActivity.newIntent(getContext(), query);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.main_video_recycler);
        //recyclerView.setAdapter(); // @Todo: Adding a adapter to the RecyclerView

        actv = (AutoCompleteTextView) v.findViewById(R.id.search_sign);
        // @Todo: Autocompleting the input area - Last task!

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}

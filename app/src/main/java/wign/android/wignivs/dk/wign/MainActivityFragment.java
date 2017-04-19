package wign.android.wignivs.dk.wign;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private Realm realm;
    private RecyclerView recyclerView;
    private AutoCompleteTextView actv;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ApiService ApiService = ServiceGenerator.createService(ApiService.class);
        new getAllWordsAsyncTask(ApiService).execute(); // Async get all the words from Wign and fill Realm DB

        recyclerView = (RecyclerView) v.findViewById(R.id.main_recycler);
        //recyclerView.setAdapter(); // Adding a adapter to the RecyclerView

        actv = (AutoCompleteTextView) v.findViewById(R.id.search_sign);
        // @Todo: Autocompleting the input area - Last task!

        // @Todo: Move everything from activity to here

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}

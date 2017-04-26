package wign.android.wignivs.dk.wign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import im.ene.toro.Toro;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import wign.android.wignivs.dk.wign.adapter.VideoRecyclerViewAdapter;
import wign.android.wignivs.dk.wign.model.Video;
import wign.android.wignivs.dk.wign.model.Word;

public class VideoFragment extends Fragment {
    private Realm realm;

    private RecyclerView recyclerView;
    private VideoRecyclerViewAdapter adapter;

    private String theQuery;
    private Word theWord;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();

        theQuery = getActivity().getIntent().getStringExtra("Query");
        theWord = realm.where(Word.class).equalTo("word", theQuery).findFirst();

        View v = inflater.inflate(R.layout.fragment_video, container, false);

        if(theWord != null) {
            recyclerView = (RecyclerView) v.findViewById(R.id.video_list_recycler);
            setUpRecyclerView();
            Toro.register(recyclerView);
        }

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
        if(theWord != null) {
            Toro.unregister(recyclerView);
        }
    }

    private void setUpRecyclerView() {
        RealmResults<Video> videoList = theWord.getList().sort("votes", Sort.DESCENDING);
        adapter = new VideoRecyclerViewAdapter(getActivity(), videoList);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
    }
}

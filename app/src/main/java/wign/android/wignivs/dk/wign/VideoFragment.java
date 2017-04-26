package wign.android.wignivs.dk.wign;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    private Button btn_add;

    private String theQuery;
    private Word theWord;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();

        theQuery = getActivity().getIntent().getStringExtra(VideoActivity.QUERY_ID);
        theWord = realm.where(Word.class).equalTo("word", theQuery).findFirst();

        View v = inflater.inflate(R.layout.fragment_video, container, false);

        if(theWord != null) {
            recyclerView = (RecyclerView) v.findViewById(R.id.video_list_recycler);
            setUpRecyclerView();
            Toro.register(recyclerView);
        }

        btn_add = (Button) v.findViewById(R.id.btn_add_video);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "You pressed it! This feature will come in the near future :-)", Snackbar.LENGTH_LONG).show();
            }
        });

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

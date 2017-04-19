package wign.android.wignivs.dk.wign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;

/**
 * A fragment representing a list of Videos.
 */
public class VideoListFragment extends Fragment {
    Realm realm;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VideoListFragment() {
    }

    /*
    public static VideoListFragment newInstance(int columnCount) {
        VideoListFragment fragment = new VideoListFragment();
        return fragment;
    }
    */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.video_list, container, false);

        //@Todo: Adapter and initiate the list

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}

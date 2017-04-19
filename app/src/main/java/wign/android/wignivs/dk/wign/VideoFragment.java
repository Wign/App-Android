package wign.android.wignivs.dk.wign;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class VideoFragment extends Fragment {

    private String theQuery;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!getArguments().isEmpty()) {
            theQuery = getArguments().getString("Query");
        }

        ApiService ApiService = ServiceGenerator.createService(ApiService.class);
        new getVideosAsyncTask(ApiService).execute(theQuery);
    }
}

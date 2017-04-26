package wign.android.wignivs.dk.wign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Troels on 26/04/2017.
 */
public class EmptyVideoFragment extends Fragment {

    public EmptyVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_null_video, container, false);

        // Get the query word from Activity
        String theQuery = getActivity().getIntent().getStringExtra(VideoActivity.QUERY_ID);

        // Setting the message and write it on the TextView
        TextView tv = (TextView) v.findViewById(R.id.empty_video_text);
        tv.setText(getResources().getString(R.string.empty_video, theQuery));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ImageView iv = (ImageView) v.findViewById(R.id.non);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
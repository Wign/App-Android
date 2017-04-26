package wign.android.wignivs.dk.wign;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private Realm realm;

    private Button btn;
    private EditText actv;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        btn = (Button) v.findViewById(R.id.btn_find_sign);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = actv.getText().toString();
                Intent i = VideoActivity.newIntent(getContext(), query);
                startActivity(i);
            }
        });

        actv = (EditText) v.findViewById(R.id.search_sign);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}

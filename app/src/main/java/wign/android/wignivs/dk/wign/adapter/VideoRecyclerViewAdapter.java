package wign.android.wignivs.dk.wign.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import im.ene.toro.BaseAdapter;
import im.ene.toro.PlayerManager;
import im.ene.toro.Toro;
import io.realm.OrderedRealmCollection;
import wign.android.wignivs.dk.wign.R;
import wign.android.wignivs.dk.wign.adapter.holders.VideoViewHolder;
import wign.android.wignivs.dk.wign.model.Video;

/**
 * Created by Troels on 24/04/2017.
 */

public class VideoRecyclerViewAdapter extends BaseAdapter<VideoViewHolder> implements PlayerManager {

    private OrderedRealmCollection<Video> data;
    private Context context;

    public VideoRecyclerViewAdapter(Context context, OrderedRealmCollection<Video> data) {
        super();
        setHasStableIds(true);
        this.context = context;
        this.data = data;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_video, parent, false);
        final VideoViewHolder vh = new VideoViewHolder(view, context);
        vh.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == vh.getPlayerView()) {
                    Toro.pause();
                    Snackbar.make(parent, "Clicked to VIDEO", Snackbar.LENGTH_LONG).show();
                } else if (v == vh.textDescription) {
                    Snackbar.make(parent, "Clicked to TEXT", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        vh.setOnItemLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(parent, "Long pressed to VIDEO", Snackbar.LENGTH_LONG).show();
                return false;
            }
        });

        return vh;
    }

    @Nullable
    @Override
    public Video getItem(int index) {
        return data.get(index);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int index) {
        return data.get(index).getID();
    }
}

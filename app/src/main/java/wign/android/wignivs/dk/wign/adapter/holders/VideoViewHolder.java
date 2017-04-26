package wign.android.wignivs.dk.wign.adapter.holders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.*;
import com.google.android.exoplayer2.source.*;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.exoplayer2.util.Util;

import im.ene.toro.exoplayer2.ExoPlayerView;
import im.ene.toro.exoplayer2.Media;
import im.ene.toro.extended.ExtPlayerViewHolder;
import wign.android.wignivs.dk.wign.R;
import wign.android.wignivs.dk.wign.model.Video;

/**
 * Created by Troels on 25/04/2017.
 */

public class VideoViewHolder extends ExtPlayerViewHolder {

    private Video video;
    public final TextView textDescription;
    private Context context;


    public VideoViewHolder(View itemView, Context context) {
        super(itemView);
        textDescription = (TextView) itemView.findViewById(R.id.list_item_desc);
        this.context = context;
    }

    @Override
    protected void onBind(RecyclerView.Adapter adapter, @Nullable Object object) {
        video = (Video) object;
        System.out.println(video.getVideoURL());
            try {
                this.playerView.setMedia(new Media(video.getVideoURI()), false);
            } catch (ParserException | NullPointerException e) {
                e.printStackTrace();
            }
        textDescription.setText(video.getDescription());
    }

    @Override
    protected ExoPlayerView findVideoView(View itemView) {
        return (ExoPlayerView) itemView.findViewById(R.id.list_item_video);
    }

    @Nullable
    @Override
    public String getMediaId() {
        return video.getID()+"";
    }

    @Override
    protected MediaSource getMediaSource() {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "wign.android.wignivs.dk.wign"));
        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        return new ExtractorMediaSource(video.getVideoURI(), dataSourceFactory, extractorsFactory, null, null);
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        super.setOnItemClickListener(listener);
        itemView.setOnClickListener(listener);
    }

    public void setOnItemLongClickListener(final View.OnLongClickListener listener) {
        super.setOnItemLongClickListener(listener);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return listener.onLongClick(v) || helper.onLongClick(v);
            }
        });
    }
}

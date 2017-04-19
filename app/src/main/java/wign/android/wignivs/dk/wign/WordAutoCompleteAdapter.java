package wign.android.wignivs.dk.wign;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmModel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

/**
 * Created by Troels on 11/04/2017.
 */

public class WordAutoCompleteAdapter extends RealmBaseAdapter implements Filterable {
    private OrderedRealmCollection resultList;
    private Context context;

    public WordAutoCompleteAdapter(Context context, @Nullable OrderedRealmCollection data) {
        super(data);
        resultList = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        }
        if (adapterData != null) {
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(adapterData.get(position).toString());
        }
        return null;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}

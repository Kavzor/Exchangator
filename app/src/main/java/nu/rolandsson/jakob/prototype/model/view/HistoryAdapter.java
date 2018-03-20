package nu.rolandsson.jakob.prototype.model.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import nu.rolandsson.jakob.prototype.R;
import nu.rolandsson.jakob.prototype.model.History;
import nu.rolandsson.jakob.prototype.model.HistoryRepository;

public class HistoryAdapter extends BaseAdapter {

    private HistoryRepository mRepository;
    private Context mContext;

    public HistoryAdapter(Context context) {
        mContext = context;
        mRepository = new HistoryRepository();
    }

    public HistoryRepository getRepository() {
        return mRepository;
    }

    @Override
    public int getCount() {
        return mRepository.getAll().size();
    }

    @Override
    public Object getItem(int position) {
        return mRepository.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        History historyItem = (History) getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.history_list_view, parent, false);
        }

        if(historyItem != null) {
            TextView textView = convertView.findViewById(R.id.history_list_item);
            textView.setText(historyItem.toString());
        }

        return convertView;
    }
}

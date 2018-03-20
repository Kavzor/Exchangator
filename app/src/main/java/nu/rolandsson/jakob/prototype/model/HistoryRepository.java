package nu.rolandsson.jakob.prototype.model;

import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    private List<History> mHistoryList = new ArrayList<>();

    public List<History> getAll() {
        return mHistoryList;
    }

    public History get(int index){
        return mHistoryList.get(index);
    }

    public void add(History entry) {
        if(!mHistoryList.contains(entry)) {
            mHistoryList.add(entry);
        }
        else {
            mHistoryList.remove(entry);
            mHistoryList.add(entry);
        }
    }
}

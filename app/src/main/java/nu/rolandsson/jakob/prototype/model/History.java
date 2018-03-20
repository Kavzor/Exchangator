package nu.rolandsson.jakob.prototype.model;

public class History {

    private String mFrom;
    private String mTo;
    private int mSelectedPositionFrom;
    private int mSelectedPositionTo;


    public History(String from, int fromPosition, String to, int toPosition) {
        mFrom = from;
        mSelectedPositionFrom = fromPosition;
        mTo = to;
        mSelectedPositionTo = toPosition;
    }

    private String getFrom() {
        return mFrom;
    }
    private String getTo() {
        return mTo;
    }

    public int getSelectedPositionFrom() {
        return mSelectedPositionFrom;
    }


    public int getSelectedPositionTo() {
        return mSelectedPositionTo;
    }


    @Override
    public String toString() {
        return mFrom + " - " + mTo;
    }

    @Override
    public boolean equals(Object object) {
        if(this.getClass() == object.getClass()) {
            History history = (History) object;
            return history.getFrom().equals(this.getFrom()) && history.getTo().equals(this.getTo());
        } else {
            return false;
        }
    }
}

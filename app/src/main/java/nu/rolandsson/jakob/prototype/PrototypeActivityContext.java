package nu.rolandsson.jakob.prototype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.kavzor.data.callback.ChangeListener;

import nu.rolandsson.jakob.prototype.components.CurrencyComponent;
import nu.rolandsson.jakob.prototype.components.TextComponent;
import nu.rolandsson.jakob.prototype.constants.Component;
import nu.rolandsson.jakob.prototype.constants.Resource;
import nu.rolandsson.jakob.prototype.model.view.HistoryAdapter;

abstract class PrototypeActivityContext extends AppCompatActivity {
    private CurrencyComponent mFromCurrency;
    private CurrencyComponent mToCurrency;
    private TextComponent mCurrency;
    private TextView mCurrencyResult;
    private ListView mHistoryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Component.PROTOTYPE_ACTIVITY);

        mFromCurrency = findViewById(Component.CURRENCY_FROM);
        mToCurrency = findViewById(Component.CURRENCY_TO);
        mCurrency = findViewById(Component.CURRENCY_INPUT);
        mCurrencyResult = findViewById(Component.CURRENCY_RESULT);
        mHistoryView = findViewById(Component.CURRENCY_HISTORY);

        initialize();
        setupResources();
        setInteractions();
    }

    protected abstract void initialize();
    protected abstract void setupResources();
    protected abstract void setInteractions();

    ChangeListener[] getChangeListeners() {
        return new ChangeListener[] { mFromCurrency, mToCurrency, mCurrency };
    }

    CurrencyComponent getFromCurrency() {
        return mFromCurrency;
    }

    CurrencyComponent getToCurrency() {
        return mToCurrency;
    }

    TextComponent getCurrency() {
        return mCurrency;
    }

    TextView getCurrencyResult() {
        return mCurrencyResult;
    }

    ListView getHistoryView() {
        return mHistoryView;
    }

    @SuppressWarnings("unchecked")
    HistoryAdapter getHistoryAdapter() {
        return (HistoryAdapter) mHistoryView.getAdapter();
    }

    void displayResult(String result) {
        getCurrencyResult().setText(result);

    }

    void displayEmpty() {
        String emptyValue = getResources().getString(Resource.CURRENCY_NO_VALUE);
        getCurrencyResult().setText(emptyValue);
    }

}

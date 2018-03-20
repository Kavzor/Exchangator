package nu.rolandsson.jakob.prototype;

import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kavzor.data.callback.ChangeListener;
import com.kavzor.data.currency.CurrencyContext;
import com.kavzor.data.currency.CurrencyConverter;

import java.text.DecimalFormat;

import nu.rolandsson.jakob.prototype.components.CurrencyComponent;
import nu.rolandsson.jakob.prototype.constants.Component;
import nu.rolandsson.jakob.prototype.constants.Display;
import nu.rolandsson.jakob.prototype.constants.Resource;
import nu.rolandsson.jakob.prototype.model.History;
import nu.rolandsson.jakob.prototype.model.view.HistoryAdapter;

public class PrototypeActivity extends PrototypeActivityContext {

    private CurrencyContext currencyContext;
    private String[] currencyNames;
    private String[] currencyRates;

    @Override
    protected void initialize() {
        currencyNames = getResources().getStringArray(Resource.CURRENCY_NAMES);
        currencyRates = getResources().getStringArray(Resource.CURRENCY_RATES);
        currencyContext = getCurrencyContext();
    }

    @Override
    protected void setupResources() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, Component.PROTOTYPE_SPINNER_ITEM, currencyNames);

        getFromCurrency().setAdapter(adapter);
        getToCurrency().setAdapter(adapter);

        BaseAdapter historyAdapter = new HistoryAdapter(this);
        getHistoryView().setAdapter(historyAdapter);
    }

    @Override
    protected void setInteractions() {
        for (ChangeListener changeListener : getChangeListeners()) {
            if(changeListener instanceof CurrencyComponent) {
                changeListener.setChangeListener(this::convertCurrency,
                        this::updateHistory, this::updateLabels);
            }
            else {
                changeListener.setChangeListener(this::convertCurrency);
            }
        }

        getHistoryView().setOnItemClickListener((parent, view, position, id) -> {
            History history = (History) getHistoryAdapter().getItem(position);
            getFromCurrency().setSelection(history.getSelectedPositionFrom());
            getToCurrency().setSelection(history.getSelectedPositionTo());
        });
    }

    public CurrencyContext getCurrencyContext() {
        DecimalFormat df = new DecimalFormat(Display.DECIMAL_DISPLAY_PATTERN);

        return CurrencyContext.prepare(config -> config.
                decimalFormat(df).
                currencies(currencyNames).
                rates(currencyRates).
                build());
    }

    private void convertCurrency() {
        CurrencyConverter.convert(builder -> builder.
            context(currencyContext).
            from((String) getFromCurrency().getSelectedItem()).
            to((String) getToCurrency().getSelectedItem()).
            value(getCurrency().getText().toString()).
            onEmpty(super::displayEmpty).
            onError(super::displayResult).
            onSuccess(super::displayResult));
    }

    private void updateHistory() {
        String from = getFromCurrency().getSelectedItem().toString();
        int fromPos = getFromCurrency().getSelectedItemPosition();

        String to = getToCurrency().getSelectedItem().toString();
        int toPos = getToCurrency().getSelectedItemPosition();

        String noValue = getString(Resource.CURRENCY_NO_VALUE);

        if(!from.equals(noValue) && !to.equals(noValue)){
            History entry = new History(from, fromPos, to, toPos);
            getHistoryAdapter().getRepository().add(entry);
            getHistoryAdapter().notifyDataSetChanged();
        }
    }

    private void updateLabels() {
        String from = getFromCurrency().getSelectedItem().toString();
        String to = getToCurrency().getSelectedItem().toString();
        TextView currencyFrom = findViewById(R.id.p_currency_from_header);
        TextView currencyTo = findViewById(R.id.p_currency_to_header);

        String fromText = getString(R.string.p_formatted_from, from);
        String toText = getString(R.string.p_formatted_to, to);

        currencyFrom.setText(fromText);
        currencyTo.setText(toText);
    }
}

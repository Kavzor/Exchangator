package nu.rolandsson.jakob.prototype.components;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.kavzor.data.callback.ChangeListener;
import com.kavzor.data.callback.ValueChangedWatcher;


public class CurrencyComponent extends AppCompatSpinner implements ChangeListener {

    public CurrencyComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChangeListener(ValueChangedWatcher...changedWatchers) {
        this.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(ValueChangedWatcher changedWatcher : changedWatchers) {
                    changedWatcher.valueChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

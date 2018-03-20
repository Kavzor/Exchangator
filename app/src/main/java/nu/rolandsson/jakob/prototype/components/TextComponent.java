package nu.rolandsson.jakob.prototype.components;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.kavzor.data.callback.ChangeListener;
import com.kavzor.data.callback.ValueChangedWatcher;

public class TextComponent extends AppCompatEditText implements ChangeListener {

    public TextComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChangeListener(ValueChangedWatcher...changedWatchers) {
        this.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for(ValueChangedWatcher changedWatcher: changedWatchers) {
                    changedWatcher.valueChanged();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

package cn.ucai.fulicenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton rbNewgoods, rbBoutigue, rbCategory, rbCart, rbPersonal;
    int index, currentIndex;
    RadioButton[] rbs = new RadioButton[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rbNewgoods = (RadioButton) findViewById(R.id.Lauout_newgoods);
        rbBoutigue = (RadioButton) findViewById(R.id.Layout_boutigue);
        rbCategory = (RadioButton) findViewById(R.id.Layout_category);
        rbCart = (RadioButton) findViewById(R.id.Layout_cart);
        rbPersonal = (RadioButton) findViewById(R.id.Layout_personal);
        rbs[0] = rbNewgoods;
        rbs[1] = rbBoutigue;
        rbs[2] = rbCategory;
        rbs[3] = rbCart;
        rbs[4] = rbPersonal;
    }

    public void onCheckedChange(View view) {
        switch (view.getId()) {
            case R.id.Lauout_newgoods:
                index = 0;
                break;
            case R.id.Layout_boutigue:
                index = 1;
                break;
            case R.id.Layout_category:
                index = 2;
                break;
            case R.id.Layout_cart:
                index = 3;
                break;
            case R.id.Layout_personal:
                index = 4;
                break;
        }
        if (index != currentIndex) {
            setRadioStatus();
        }
    }

    private void setRadioStatus() {
        for (int i = 0; i < rbs.length; i++) {
            if (index != i) {
                rbs[i].setChecked(false);
            } else {
                rbs[i].setChecked(true);
            }
        }
        currentIndex = index;
    }

}

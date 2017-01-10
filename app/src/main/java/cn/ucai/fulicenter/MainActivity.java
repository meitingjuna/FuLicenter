package cn.ucai.fulicenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    int index, currentIndex;
    RadioButton[] rbs = new RadioButton[5];
    @BindView(R.id.Lauout_newgoods)
    RadioButton LauoutNewgoods;
    @BindView(R.id.Layout_boutigue)
    RadioButton LayoutBoutigue;
    @BindView(R.id.Layout_category)
    RadioButton LayoutCategory;
    @BindView(R.id.Layout_cart)
    RadioButton LayoutCart;
    @BindView(R.id.Layout_personal)
    RadioButton LayoutPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rbs[0] = LauoutNewgoods;
        rbs[1] = LayoutBoutigue;
        rbs[2] = LayoutCategory;
        rbs[3] = LayoutCart;
        rbs[4] = LayoutPersonal;
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

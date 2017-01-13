package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.fragment.NewGoodsFragment;
import cn.ucai.fulicenter.view.MFGT;

public class BoutiquechildActivity extends AppCompatActivity {


    @BindView(R.id.mBack)
    ImageView mBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutiquechild);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new NewGoodsFragment())
                .commit();
        tvTitle.setText(getIntent().getStringExtra(I.Boutique.NAME));
    }


    @OnClick(R.id.mBack)
    public void onClick() {
        MFGT.finish(this);//结束这个页面重新回到上一个页面.
        //this.finish();//结束这个页面重新回到上一个页面.
    }
}

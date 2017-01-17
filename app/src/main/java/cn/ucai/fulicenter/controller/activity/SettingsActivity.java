package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.SharePrefrenceUtils;
import cn.ucai.fulicenter.model.ustils.ImageLoader;
import cn.ucai.fulicenter.view.MFGT;

public class SettingsActivity extends AppCompatActivity {


    @BindView(R.id.iv_user_profile_avatar)
    ImageView ivUserProfileAvatar;
    @BindView(R.id.tv_user_profile_name)
    TextView tvUserProfileName;
    @BindView(R.id.tv_user_profile_nick)
    TextView tvUserProfileNick;
    @BindView(R.id.iv_fanhui_1)
    ImageView ivFanhui1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        User user = FuLiCenterApplication.getUser();
        if (user != null) {
            loadUser(user);
        } else {
            MFGT.gotoLogin(this);
        }
    }

    private void loadUser(User user) {
        ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user), this, ivUserProfileAvatar);
        tvUserProfileName.setText(user.getMuserName());
        tvUserProfileNick.setText(user.getMuserNick());
    }


    @OnClick({R.id.iv_fanhui_1, R.id.btn_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui_1:
                MFGT.finish(this);
                break;
            case R.id.btn_logout:
                FuLiCenterApplication.setUser(null);
                SharePrefrenceUtils.getInstance(this).removeUser();
                MFGT.gotoLogin(this);
                finish();
                break;
        }
    }
}

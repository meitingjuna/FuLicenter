package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.adapter.CollectAdapter;
import cn.ucai.fulicenter.controller.adapter.GoodsAdapter;
import cn.ucai.fulicenter.model.bean.CollectBean;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.IModelUser;
import cn.ucai.fulicenter.model.net.ModelUser;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.ustils.ConvertUtils;
import cn.ucai.fulicenter.view.DisplayUtils;
import cn.ucai.fulicenter.view.SpaceItemDecoration;

public class CollectsActivity extends AppCompatActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.backClickArea)
    LinearLayout backClickArea;
    @BindView(R.id.tv_common_title)
    TextView tvCommonTitle;
    @BindView(R.id.tvRefresh)
    TextView tvRefresh;
    @BindView(R.id.mRw)
    RecyclerView mRw;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    IModelUser model;
    User user;
    int pageId = 1;
    CollectAdapter mAdapter;
    GridLayoutManager gm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collects);
        ButterKnife.bind(this);
        DisplayUtils.initBackWithTitle(this, "收藏宝贝");
        user = FuLiCenterApplication.getUser();
        if (user == null) {
            finish();
        } else {
            initView();
            initData();
            setListener();
        }

    }


    private void initData() {
        pageId = 1;
        downloadContactLiset(I.ACTION_DOWNLOAD, pageId);
    }

    private void downloadContactLiset(final int action, int PageId) {
        model = new ModelUser();
        model.getCollects(this, user.getMuserName(), pageId, I.PAGE_SIZE_DEFAULT, new OnCompleteListener<CollectBean[]>() {
            @Override
            public void onSuccess(CollectBean[] result) {
                /*srl.setRefreshing(false);
                tvRefresh.setVisibility(View.GONE);
                mAdapter.setMore(true);*/
                mAdapter.setMore(result != null && result.length > 0);
                if (!mAdapter.isMore()) {
                    if (action == I.ACTION_PULL_UP) {
                        mAdapter.setFooter("没有更多数据了哦..");
                    }
                    return;
                }
                mAdapter.setFooter("加载更多数据.");
                ArrayList<CollectBean> list = ConvertUtils.array2List(result);
                switch (action) {
                    case I.ACTION_DOWNLOAD:
                        mAdapter.initData(list);
                        break;
                    case I.ACTION_PULL_DOWN:
                        srl.setRefreshing(false);
                        tvRefresh.setVisibility(View.GONE);
                        mAdapter.initData(list);
                        break;
                    case I.ACTION_PULL_UP:
                        mAdapter.addData(list);
                        break;
                }

               /*     if (!mAdapter.isMore()) {
                        if (action == I.ACTION_PULL_UP) {
                            mAdapter.setFooter("没有更多数据了哦..");
                        }
                        return;
                    }
                    mAdapter.setFooter("加载更多数据.");
                    ArrayList<CollectBean> list = ConvertUtils.array2List(result);
                    if (action == I.ACTION_DOWNLOAD || action == I.ACTION_PULL_DOWN) {
                        mAdapter.initData(list);
                    } else {
                        mAdapter.addData(list);
                    }
                    if (list.size() < I.PAGE_SIZE_DEFAULT) {
                        mAdapter.setMore(false);
                    }*/
            }

            @Override
            public void onError(String error) {
                srl.setRefreshing(false);
                tvRefresh.setVisibility(View.GONE);
                mAdapter.setMore(false);
            }
        });
    }

    private void setListener() {
        setPullDownListener();
        setPullUpListener();
    }

    //上拉
    private void setPullUpListener() {
        mRw.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mAdapter.setDragging(newState == RecyclerView.SCROLL_STATE_DRAGGING);
                int lastPosition = gm.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mAdapter.isMore() && lastPosition == mAdapter.getItemCount() - 1) ;
                pageId++;
                downloadContactLiset(I.ACTION_PULL_UP, pageId);
            }
        });

    }

    //下拉
    private void setPullDownListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                tvRefresh.setVisibility(View.VISIBLE);
                pageId = 1;
                downloadContactLiset(I.ACTION_PULL_DOWN, pageId);
            }


        });
    }

    private void initView() {
        srl.setColorSchemeColors(
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_blue)
        );
        gm = new GridLayoutManager(this, I.COLUM_NUM);
        mRw.addItemDecoration(new SpaceItemDecoration(12));
        mRw.setLayoutManager(gm);
        mAdapter = new CollectAdapter(this, new ArrayList<CollectBean>());
        mRw.setAdapter(mAdapter);
    }

}

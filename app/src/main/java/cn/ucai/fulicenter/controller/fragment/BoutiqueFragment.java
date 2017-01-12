package cn.ucai.fulicenter.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.adapter.BoutiqueAdapter;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.net.IModelNewBoutique;
import cn.ucai.fulicenter.model.net.ModelBoutique;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.ustils.CommonUtils;
import cn.ucai.fulicenter.model.ustils.ConvertUtils;
import cn.ucai.fulicenter.view.SpaceItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoutiqueFragment extends Fragment {
   // static final int ACTION_DOWNLOAD = 0;//下载首页
    //static final int ACTION_PULL_DOWN = 1;//下拉刷新

    @BindView(R.id.tvRefresh)
    TextView tvRefresh;
    @BindView(R.id.mRw)
    RecyclerView mRw;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    LinearLayoutManager gm;
    BoutiqueAdapter mAdapter;
    ArrayList<BoutiqueBean> mList = new ArrayList<>();
    IModelNewBoutique mModel;
    @BindView(R.id.tvChunxing)
    TextView tvChunxing;


    public BoutiqueFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_boutique, container, false);
        ButterKnife.bind(this, layout);
        initView();
        mModel = new ModelBoutique();
        setPullDownListener();
        return layout;
    }

    private void initData(final int action) {
        mModel.downData(getContext(), new OnCompleteListener<BoutiqueBean[]>() {
            @Override
            public void onSuccess(BoutiqueBean[] result) {
                srl.setRefreshing(false);
                tvRefresh.setVisibility(View.GONE);
                srl.setVisibility(View.VISIBLE);
                tvChunxing.setVisibility(View.GONE);
                if (result != null && result.length > 0) {
                    ArrayList<BoutiqueBean> list = ConvertUtils.array2List(result);
                    if (action == I.ACTION_DOWNLOAD || action == I.ACTION_PULL_DOWN) {
                        mAdapter.initData(list);
                    } else {
                        mAdapter.addData(list);
                    }
                } else {
                    tvChunxing.setVisibility(View.VISIBLE);
                    srl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String error) {
                srl.setRefreshing(false);
                tvChunxing.setVisibility(View.VISIBLE);
                tvRefresh.setVisibility(View.GONE);
                CommonUtils.showShortToast(error);

            }
        });

    }


    private void initView() {
        srl.setColorSchemeColors(
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_yellow),
                getResources().getColor(R.color.google_red));
        /*gm = new LinearLayoutManager(getContext());
        mRw.setLayoutManager(gm);
        mRw.setHasFixedSize(true);
        mAdapter = new BoutiqueAdapter(getContext(), mList);
        mRw.setAdapter(mAdapter);
        mRw.addItemDecoration(new SpaceItemDecoration(20));
        tvChunxing.setVisibility(View.VISIBLE);
        srl.setVisibility(View.GONE);*/
        gm = new LinearLayoutManager(getContext());
        mRw.addItemDecoration(new SpaceItemDecoration(20));
        mRw.setLayoutManager(gm);
        mRw.setHasFixedSize(true);
        mAdapter = new BoutiqueAdapter(getContext(), mList);
        mRw.setAdapter(mAdapter);
        srl.setVisibility(View.GONE);
        tvChunxing.setVisibility(View.VISIBLE);
    }


    private void setPullDownListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                tvRefresh.setVisibility(View.VISIBLE);
                initData(I.ACTION_PULL_DOWN);
            }
        });
    }

    @OnClick(R.id.tvChunxing)
    public void onClick() {
        initData(I.ACTION_DOWNLOAD);

    }
}

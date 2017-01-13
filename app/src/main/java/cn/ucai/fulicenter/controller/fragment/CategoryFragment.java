package cn.ucai.fulicenter.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.adapter.CategoryAdapter;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;
import cn.ucai.fulicenter.model.net.IModelNewCategory;
import cn.ucai.fulicenter.model.net.ModelCategory;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.ustils.ConvertUtils;

public class CategoryFragment extends Fragment {
    IModelNewCategory mModel;
    CategoryAdapter mAdapter;
    ArrayList<CategoryGroupBean> mGroupBean = new ArrayList<>();
    ArrayList<ArrayList<CategoryChildBean>> mChildBean = new ArrayList<>();
    int groupCount;


    @BindView(R.id.elv_category)
    ExpandableListView elvCategory;
    @BindView(R.id.tv_nomore)
    TextView tvNomore;

    public CategoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, layout);
        mAdapter = new CategoryAdapter(getContext(), mChildBean, mGroupBean);
        elvCategory.setAdapter(mAdapter);
        initView(false);
        initData();
        return layout;
    }


    private void initData() {
        mModel = new ModelCategory();
        mModel.downData(getContext(), new OnCompleteListener<CategoryGroupBean[]>() {
            @Override
            public void onSuccess(CategoryGroupBean[] result) {
                if (result != null) {
                    initView(true);
                    ArrayList<CategoryGroupBean> groupList = ConvertUtils.array2List(result);
                    mGroupBean.addAll(groupList);
                    for (int i = 0; i < groupList.size(); i++) {
                        mChildBean.add(new ArrayList<CategoryChildBean>());
                        downloadChildData(groupList.get(i).getId(),i);
                    }

                } else {
                    initView(false);

                }
            }

            @Override
            public void onError(String error) {
                Log.i("error", error);
            }
        });

    }


    private void downloadChildData(int id,final int index) {
        mModel.downData(getContext(), id, new OnCompleteListener<CategoryChildBean[]>() {
            @Override
            public void onSuccess(CategoryChildBean[] result) {
                groupCount++;
                if (result != null) {
                    ArrayList<CategoryChildBean> childList = ConvertUtils.array2List(result);
                    mChildBean.set(index,childList);
                }
                if (groupCount == mGroupBean.size()) {
                    mAdapter.initData(mGroupBean, mChildBean);
                }
            }

            @Override
            public void onError(String error) {
                Log.i("error", error);
            }
        });
    }

    private void initView(boolean hasData) {
        tvNomore.setVisibility(hasData ? View.GONE : View.VISIBLE);
        elvCategory.setVisibility(hasData ? View.VISIBLE : View.GONE);
    }

}

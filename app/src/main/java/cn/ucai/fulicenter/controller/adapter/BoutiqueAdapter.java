package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;

import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.activity.BoutiquechildActivity;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;

import cn.ucai.fulicenter.model.ustils.ImageLoader;

/**
 * Created by MTJ on 2017/1/11.
 */

public class BoutiqueAdapter extends RecyclerView.Adapter {
    ///private static final int TYPE_BOUTIQUE = 0;
    //private static final int TYPE_FOOTER = 1;
    Context mContext;
    ArrayList<BoutiqueBean> mList;

    public BoutiqueAdapter(Context context, ArrayList<BoutiqueBean> list) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = new BoutiqueAdapter.BoutiqueViewHolder(View.inflate(mContext, R.layout.item_boutique, null));
        return holder;
    }

    //RecyclerView.ViewHolder holder = new BoutiqueViewHolder(View.inflate(mContext, R.layout.item_boutique, null));


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        BoutiqueViewHolder holder1 = (BoutiqueViewHolder) holder;
        ImageLoader.downloadImg(mContext, holder1.ivBoutique, mList.get(position).getImageurl());
        holder1.tvBoutique1.setText(mList.get(position).getName());
        holder1.tvBoutique2.setText(mList.get(position).getTitle());
        holder1.tvBoutique3.setText(mList.get(position).getDescription());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mContext.startActivity(new Intent(mContext, BoutiquechildActivity.class)
                       .putExtra(I.NewAndBoutiqueGoods.CAT_ID,mList.get(position).getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void initData(ArrayList<BoutiqueBean> list) {
        if (mList != null) {
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(ArrayList<BoutiqueBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

static class BoutiqueViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivBoutique)
    ImageView ivBoutique;
    @BindView(R.id.tvBoutique1)
    TextView tvBoutique1;
    @BindView(R.id.tvBoutique2)
    TextView tvBoutique2;
    @BindView(R.id.tvBoutique3)
    TextView tvBoutique3;

    BoutiqueViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }
}


}

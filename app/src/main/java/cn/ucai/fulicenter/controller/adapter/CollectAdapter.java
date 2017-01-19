package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.CollectBean;
import cn.ucai.fulicenter.model.ustils.ImageLoader;
import cn.ucai.fulicenter.view.MFGT;

import static cn.ucai.fulicenter.R.id.ivGoodsThumb;
import static cn.ucai.fulicenter.R.id.tvGoodsName;

/**
 * Created by MTJ on 2017/1/11.
 */

public class CollectAdapter extends RecyclerView.Adapter {
    private static final int TYPE_GOODS = 0;
    private static final int TYPE_FOOTER = 1;
    Context mContext;
    ArrayList<CollectBean> mList;
    boolean isMore;



    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
        notifyDataSetChanged();
    }


    String footer;
    boolean isDragging;


    public boolean isDragging() {
        return isDragging;
    }

    public void setDragging(boolean dragging) {
        isDragging = dragging;
        notifyDataSetChanged();
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
        notifyDataSetChanged();
    }


    public CollectAdapter(Context context, ArrayList<CollectBean> list) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_GOODS) {
            RecyclerView.ViewHolder holder =
                    new CollectViewHolder(View.inflate(mContext, R.layout.item_collect, null));
            return holder;
        } else {
            RecyclerView.ViewHolder holder =
                    new FooterViewHolder(View.inflate(mContext, R.layout.item_footer, null));
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            FooterViewHolder vh = (FooterViewHolder) holder;
            vh.tvFooter.setText(getFooter());
        } else {
            CollectViewHolder holder1 = (CollectViewHolder) holder;
            holder1.bind(position);
        }
    }



    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_GOODS;
    }

    public void initData(ArrayList<CollectBean> list) {
        if (mList != null) {
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(ArrayList<CollectBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    class CollectViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivGoodsThumb)
        ImageView ivGoodsThumb;
        @BindView(R.id.tvGoodsName)
        TextView tvGoodsName;
        @BindView(R.id.tvGoodsPrice)
        TextView tvGoodsPrice;
        @BindView(R.id.ivCollectDel)
        ImageView ivCollectDel;

int itemPosition;
        CollectViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final int position) {

                ImageLoader.downloadImg(mContext, ivGoodsThumb, mList.get(position).getGoodsThumb());
                tvGoodsName.setText(mList.get(position).getGoodsName());
            itemPosition = position;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MFGT.gotoGoodsDetail(mContext,mList.get(position).getGoodsId());
                    }
                });
        }
    }
    static class FooterViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvFooter)
        TextView tvFooter;
        FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
    @OnClick(R.id.ivCollectDel)
    public void onClick() {
    }
}

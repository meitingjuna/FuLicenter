package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.CartBean;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.ustils.ImageLoader;

/**
 * Created by LCH on 2017/1/11.
 */

public class CartAdapter extends RecyclerView.Adapter {
    Context mContext;
    ArrayList<CartBean> mList;
    ArrayList<NewGoodsBean> mN;
    String footer;


    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
        notifyDataSetChanged();
    }


    public CartAdapter(Context context, ArrayList<CartBean> list) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder =
                new CartViewHolder(View.inflate(mContext, R.layout.item_cart, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder ParentHolder, final int position) {
        CartViewHolder vh = (CartViewHolder) ParentHolder;
        CartBean cartBean = mList.get(position);
        GoodsDetailsBean goods = cartBean.getGoods();
        if (goods != null) {
            ImageLoader.downloadImg(mContext, vh.mivGoodsThumb, goods.getGoodsThumb());
            vh.mtvGoodsName.setText(goods.getGoodsName());
            vh.mtvGoodsPrice.setText(goods.getCurrencyPrice());
        }
        vh.mtvCartCount.setText("(" + cartBean.getCount() + ")");
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void initData(ArrayList<CartBean> list) {
        if (mList != null) {
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(ArrayList<CartBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chkSelect)
        CheckBox mchkSelect;
        @BindView(R.id.ivGoodsThumb)
        ImageView mivGoodsThumb;
        @BindView(R.id.tvGoodsName)
        TextView mtvGoodsName;
        @BindView(R.id.ivAddCart)
        ImageView mivAddCart;
        @BindView(R.id.tvCartCount)
        TextView mtvCartCount;
        @BindView(R.id.ivReduceCart)
        ImageView mivReduceCart;
        @BindView(R.id.tvGoodsPrice)
        TextView mtvGoodsPrice;

        CartViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
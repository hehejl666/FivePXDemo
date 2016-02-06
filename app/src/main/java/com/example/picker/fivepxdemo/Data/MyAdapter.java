package com.example.picker.fivepxdemo.Data;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.picker.fivepxdemo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 17930 on 2016/2/4.
 */
public class MyAdapter extends BaseAdapter {
    private HuiMaiJson huimaijson;
    private Context context;
    private View mview;

    public MyAdapter(HuiMaiJson huimaijson, Context context) {
        this.huimaijson = huimaijson;
        this.context = context;
    }

    @Override
    public int getCount() {
        return huimaijson.getItem_cid().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       mview = LayoutInflater.from(context).inflate(R.layout.commodity_view_item, null, false);

        Uri uri = Uri.parse(huimaijson.getImg_maps().get(i).get(0 + "") + "");
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) mview.findViewById(R.id.id_commodity_icon);
        simpleDraweeView.setImageURI(uri);
        TextView testName = (TextView) mview.findViewById(R.id.id_commodity_name);
        testName.setText(huimaijson.getItem_name().get(i));
        TextView testNewPrice = (TextView) mview.findViewById(R.id.id_commodity_newprice);
        testNewPrice.setText("￥"+huimaijson.getItem_newPrice().get(i));
        TextView testdiscount = (TextView) mview.findViewById(R.id.id_commodity_discount);
        testdiscount.setText(huimaijson.getItem_discount().get(i)+" ");


        return mview;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'commodity_view_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */

}

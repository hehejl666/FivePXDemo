package com.example.picker.fivepxdemo.UI.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.picker.fivepxdemo.Data.HuiMaiJson;
import com.example.picker.fivepxdemo.R;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Commodity_infoView extends AppCompatActivity {

    HuiMaiJson huimai;
    Intent intent;
    int postion = 0;
    @Bind(R.id.id_info_Viewpager)
    ViewPager idInfoViewpager;
    @Bind(R.id.id_info_Name)
    TextView idInfoName;
    @Bind(R.id.id_info_discount)
    TextView idInfoDiscount;
    @Bind(R.id.id_info_newPrice)
    TextView idInfoNewPrice;
    List<String> urllist = new ArrayList<>();
    List<View> viewlist = new ArrayList<>();
    @Bind(R.id.id_info_recyclerView)
    RecyclerView idInfoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_info_view);
        ButterKnife.bind(this);
        // getIntent();
        intent = getIntent();

        //Bundle bundle = intent.getSerializableExtra("huimaijson");
        huimai = (HuiMaiJson) intent.getSerializableExtra("huimaijson");
        postion = Integer.parseInt(intent.getExtras().get("position").toString());
        setTitle(postion + "");

        initView();
    }

    private SimpleDraweeView getImage(String url) {
        Uri uri = Uri.parse(url);
        SimpleDraweeView draweeView = new SimpleDraweeView(Commodity_infoView.this);
        draweeView.setImageURI(uri);
        GenericDraweeHierarchy hierarchy = draweeView.getHierarchy();
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);

        return draweeView;

    }

    private void initView() {
        idInfoDiscount.setText(huimai.getItem_discount().get(postion));
        idInfoName.setText(huimai.getItem_name().get(postion));
        idInfoNewPrice.setText(huimai.getItem_newPrice().get(postion));
        for (int i = 0; i < huimai.getImg_maps().get(postion).size(); i++) {
            Map<String, Object> map = huimai.getImg_maps().get(postion);
            //urllist.add(map.get(i+"").toString());
            viewlist.add(getImage(map.get(i + "").toString()));
        }
        RecyclerView.LayoutManager manager1=new LinearLayoutManager(this);
        idInfoRecyclerView.setLayoutManager(manager1);
        idInfoViewpager.setAdapter(new myViewAdapter(Commodity_infoView.this, viewlist));
        idInfoRecyclerView.setAdapter(new myrecycleAdapter(huimai));

    }

    class myrecycleAdapter extends RecyclerView.Adapter<myrecycleAdapter.Viewholer> {

        public myrecycleAdapter(HuiMaiJson huiMaiJson) {
            this.huiMaiJson = huiMaiJson;
        }

       private HuiMaiJson huiMaiJson;

        @Override
        public Viewholer onCreateViewHolder(ViewGroup parent, int viewType) {

            Viewholer viewholer = new Viewholer(LayoutInflater.from(Commodity_infoView.this).inflate(R.layout.recycle_item, parent, false));
            return viewholer;
        }

        @Override
        public void onBindViewHolder(Viewholer holder, int position) {
            holder.tv.setText(huimai.getItem_cid().get(position)+"sss");
        }

        @Override
        public int getItemCount() {
            return huimai.getItem_cid().size();
        }

        class Viewholer extends RecyclerView.ViewHolder {

            TextView tv;

            public Viewholer(View itemView) {
                super(itemView);
                tv= (TextView)itemView.findViewById(R.id.id_recycle_item_textview);
            }
        }
    }


    class myViewAdapter extends PagerAdapter {

        // List<String>urls;
        List<View> viewlist;

        Context context;

        myViewAdapter(Context context, List<View> uris) {
            this.viewlist = uris;
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = viewlist.get(position);
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewlist.get(position));
        }

        @Override
        public int getCount() {
            return viewlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}

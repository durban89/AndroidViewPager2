package com.gowhich.androidviewpager2;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerTitleStrip pagerTitle;
    private List<View> list;
    private List<String> listTitle;
    private MyAdapter myAdapter = null;
    private LayoutInflater inflater = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        pagerTitle = (PagerTitleStrip) this.findViewById(R.id.pagerTitle);
        myAdapter = new MyAdapter();

        //加载布局
        inflater = LayoutInflater.from(MainActivity.this);
        //第一次被加载的布局对象
        View pager = inflater.inflate(R.layout.pager1, null);
        list = new ArrayList<View>();
        list.add(pager);

        listTitle = new ArrayList<String>();
        listTitle.add("title");

        viewPager.setAdapter(myAdapter);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //在这里开启线程去下载数据，更新UI的操作
                View view = inflater.inflate(R.layout.pager1, null);
                list.add(view);
                listTitle.add("我们");
                myAdapter.notifyDataSetChanged();
            }
        });

    }


    class MyAdapter extends PagerAdapter{

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(list.get(position));
            return list.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listTitle.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(list.get(position));
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}

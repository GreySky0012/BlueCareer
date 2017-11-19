package com.example.mercer.bluecareer.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.mercer.bluecareer.Adapter.BaseRecyclerAdapter;
import com.example.mercer.bluecareer.Adapter.SmartViewHolder;
import com.example.mercer.bluecareer.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    ImageView[] _selectImages;
    RadioGroup _nav;
    View _mView;
    ListView _list;
    List<Topic> _topicList;
    //TopicAdapter _adapter;
    private BaseRecyclerAdapter<Topic> _adapter;
    private RefreshLayout _refreshLayout;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (_mView == null){
            _mView = inflater.inflate(R.layout.fragment_main, container, false);
            setView();
            setListener();
            InitList();
        }

        // Inflate the layout for this fragment
        return _mView;
    }

    private void InitList(){
        _topicList = new ArrayList<>();
        _adapter = new BaseRecyclerAdapter<Topic>(R.layout.topic_item) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Topic model, int position) {
                holder.text(R.id.topic_title,model._title);
                holder.text(R.id.topic_content,model._content);
            }
        };
        _list.setAdapter(_adapter);
        _list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showTopic(i);
            }
        });
        setListContent(0);
    }

    private void showTopic(int index){
        setListContent(index);
    }

    private void setListContent(int index){
        if (index == 0){
            _topicList.clear();
            for(int i = 0;i<10;i++)
                _topicList.add(new Topic("B站哔哩哔哩2000万投资","《那年那兔那些事儿》创始人麻蛇向媒体公布，他们获得了哔哩哔哩弹幕网（以下简称B站）的2000万人民币投资。\n麻蛇，真名林超，如今是《那年那兔那些事儿》（简称《那兔》）动画出品方翼下之风动漫产品啪啦啪啦。。。。。"));
        }
        if(index == 2){
            _topicList.clear();
            _topicList.add(new Topic("111111111","11111111111111"));
        }
        _adapter.notifyDataSetChanged();
    }

    private void setView(){
        _refreshLayout = (RefreshLayout)_mView.findViewById(R.id.refreshLayout);
        _nav = (RadioGroup) _mView.findViewById(R.id.main_nav);
        _list = (ListView)_mView.findViewById(R.id.topic_list);
        _selectImages = new ImageView[3];
        _selectImages[0] = (ImageView)_mView.findViewById(R.id.main_select0);
        _selectImages[1] = (ImageView)_mView.findViewById(R.id.main_select1);
        _selectImages[2] = (ImageView)_mView.findViewById(R.id.main_select2);
        InitList();
    }

    private void setListener(){
        _refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        _adapter.refresh(initData());
                        refreshlayout.finishRefresh();
                        refreshlayout.setLoadmoreFinished(false);
                    }
                }, 2000);
            }
        });
        _refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        _adapter.loadmore(initData());
                        refreshlayout.finishLoadmore();
                        if (_adapter.getItemCount() > 60) {
                            //Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshlayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                        }
                    }
                }, 2000);
            }
        });

        //触发自动刷新
        _refreshLayout.autoRefresh();

        _nav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id;
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.main_mine_field:
                        id = 0;
                        break;
                    case R.id.main_hot_field:
                        id = 1;
                        break;
                    case R.id.main_other_field:
                        id = 2;
                        break;
                    default:
                        id = 0;
                }
                setListContent(id);
                for(ImageView image : _selectImages){
                    image.setVisibility(View.INVISIBLE);
                }
                _selectImages[id].setVisibility(View.VISIBLE);
            }
        });
    }

    private void moreTopic(){
        _topicList.add(new Topic("111111111","11111111111111"));
    }

    private Collection<Topic> initData() {
        moreTopic();
        return _topicList;
    }

    public class Topic{
        public String _title;
        public String _content;
        public Topic(String title,String content){
            _title = title;
            _content = content;
        }
    }
}
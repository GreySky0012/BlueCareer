package com.example.mercer.bluecareer.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mercer.bluecareer.Activities.BActivity;
import com.example.mercer.bluecareer.Adapter.BaseRecyclerAdapter;
import com.example.mercer.bluecareer.Adapter.SmartViewHolder;
import com.example.mercer.bluecareer.DataStruct.JsonStruct.ReturnArticle;
import com.example.mercer.bluecareer.DataStruct.JsonStruct.ReturnCode;
import com.example.mercer.bluecareer.DataStruct.Url.ArticleUrl;
import com.example.mercer.bluecareer.Dialog.TopicDialog;
import com.example.mercer.bluecareer.Manager.ServerManager;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.Manager.UserManager;
import com.example.mercer.bluecareer.R;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener {
    ImageView[] _selectImages;
    RadioGroup _nav;
    View _mView;
    ListView _list;
    List<Topic> _topicList;
    //TopicAdapter _adapter;
    private BaseRecyclerAdapter<Topic> _adapter;
    private RefreshLayout _refreshLayout;
    private int _groupIndex;
    private Handler _handler;

    private int _currentTopicNum;

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
        _groupIndex = 0;

        // Inflate the layout for this fragment
        return _mView;
    }

    private void InitList(){
        _adapter = new BaseRecyclerAdapter<Topic>(R.layout.topic_item) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Topic model, int position) {
                holder.text(R.id.topic_title,model.title);
                holder.text(R.id.topic_content,model.content);
            }
        };
        _adapter.setOnItemClickListener(this);
        _list.setAdapter(_adapter);
    }

    private void refresh(){
        ArticleUrl url;
        if (_groupIndex == 0){
            url = new ArticleUrl("/list?"+getJobs()+"&start="+0);
        }else if (_groupIndex == 1){
            url = new ArticleUrl("/all?start=0");
        }else {
            url = new ArticleUrl("/exclude?"+getJobs());
        }
        try {
            ServerManager.GetInstance().RequestAsync(ServerManager.Method.get, url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ((BActivity)getActivity()).showToast("刷新失败");
                    _handler.sendEmptyMessage(-1);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message msg = new Message();
                    msg.obj = getTopics(response);
                    msg.what = 0;
                    _handler.sendMessage(msg);
                }
            });
        } catch (IOException e) {
            SystemManager.getInstance().PrintLog(e.getMessage());
        }
    }

    private List<Topic> getTopics(Response response) throws IOException {
        ReturnArticle returnArticle = new Gson().fromJson(response.body().string(),ReturnArticle.class);
        if (returnArticle.code!=0)
            return null;
        return returnArticle.data;
    }

    private String getJobs(){
        String[] jobs = UserManager.getInstance()._currentUser._major.split("\\|");
        String result = "";
        for (String job:jobs){
            result+="jobs=";
            result+=job;
            result+="&";
        }
        result = result.substring(0,result.length()-1);
        return result;
    }

    private void getMore(){
        ArticleUrl url;
        if (_groupIndex == 0){
             url = new ArticleUrl("/list?"+getJobs()+"&start="+_currentTopicNum);
        }else if (_groupIndex == 1){
            url = new ArticleUrl("/all?start="+_currentTopicNum);
        }else {
            url = new ArticleUrl("exclude?"+getJobs()+"start"+_currentTopicNum);
        }
        try {
            ServerManager.GetInstance().RequestAsync(ServerManager.Method.get, url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ((BActivity)getActivity()).showToast("加载失败");
                    _handler.sendEmptyMessage(-1);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message msg = new Message();
                    msg.obj = getTopics(response);
                    msg.what = 1;
                    _handler.sendMessage(msg);
                }
            });
        } catch (IOException e) {
            SystemManager.getInstance().PrintLog(e.getMessage());
        }
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
        _handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        _adapter.refresh((List<Topic>)msg.obj);
                        _refreshLayout.finishRefresh();
                        _refreshLayout.setLoadmoreFinished(false);
                        _currentTopicNum = 10;
                        break;
                    case 1:
                        List<Topic> topics = (List<Topic>)msg.obj;
                        if (topics.isEmpty()){
                            _refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                            break;
                        }
                        _adapter.loadmore(topics);
                        _refreshLayout.finishLoadmore();
                        _currentTopicNum+=topics.size();
                        break;
                    default:
                        break;
                }
            }
        };
        _refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refresh();
            }
        });
        _refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                getMore();
                /*refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        _adapter.loadmore(initData());
                        refreshlayout.finishLoadmore();
                        if (_adapter.getItemCount() > 60) {
                            //Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshlayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                        }
                    }
                }, 2000);*/
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
                for(ImageView image : _selectImages){
                    image.setVisibility(View.INVISIBLE);
                }
                _selectImages[id].setVisibility(View.VISIBLE);
                _groupIndex = id;
                refresh();
            }
        });
    }

    private void moreTopic(){
        _topicList.add(new Topic("111111111","11111111111111"));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SystemManager.getInstance().PrintLog("OnTouch");
        new TopicDialog(getActivity(),((TextView)view.findViewById(R.id.topic_title)).getText().toString(),((TextView)view.findViewById(R.id.topic_content)).getText().toString());
    }

    public class Topic{
        private int id;
        public String title;
        public String content;
        public int viewCount;
        public String jobName;
        public Topic(String title,String content){
            this.title = title;
            this.content = content;
        }
    }
}
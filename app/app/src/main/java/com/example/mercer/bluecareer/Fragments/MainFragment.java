package com.example.mercer.bluecareer.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mercer.bluecareer.Dialog.TopicDialog;
import com.example.mercer.bluecareer.Manager.SystemManager;
import com.example.mercer.bluecareer.R;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    ImageView[] _selectImages;
    RadioGroup _nav;
    View _mView;
    ListView _list;
    List<Topic> _topicList;
    TopicAdapter _adapter;

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
        _adapter = new TopicAdapter();
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
        _nav = (RadioGroup) _mView.findViewById(R.id.main_nav);
        _list = (ListView)_mView.findViewById(R.id.topic_list);
        _selectImages = new ImageView[3];
        _selectImages[0] = (ImageView)_mView.findViewById(R.id.main_select0);
        _selectImages[1] = (ImageView)_mView.findViewById(R.id.main_select1);
        _selectImages[2] = (ImageView)_mView.findViewById(R.id.main_select2);
        InitList();
    }

    private void setListener(){
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

    private class Topic{
        public String _title;
        public String _content;
        public Topic(String title,String content){
            _title = title;
            _content = content;
        }
    }

    public final class TopicViewHolder{
        public TextView _title;
        public TextView _content;
    }

    public class TopicAdapter extends BaseAdapter{
        private LayoutInflater _inflater;

        public TopicAdapter(){
            _inflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            return _topicList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TopicViewHolder holder = null;
            if (view == null){
                holder = new TopicViewHolder();

                view = _inflater.inflate(R.layout.topic_item,null);
                holder._title = (TextView)view.findViewById(R.id.topic_title);
                holder._content = (TextView)view.findViewById(R.id.topic_content);
                view.setTag(holder);
            }
            else {
                holder = (TopicViewHolder)view.getTag();
            }

            holder._title.setText(_topicList.get(i)._title);
            holder._content.setText(_topicList.get(i)._content);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SystemManager.getInstance().PrintLog("onclick");
                    TopicViewHolder holder = (TopicViewHolder)view.getTag();
                    new TopicDialog(getActivity(),holder._title.getText().toString(),holder._content.getText().toString());
                }
            });

            return view;
        }
    }
}
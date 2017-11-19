package com.example.mercer.bluecareer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mercer.bluecareer.DataStruct.MyOccupation;
import com.example.mercer.bluecareer.Dialog.TopicDialog;
import com.example.mercer.bluecareer.R;

import java.util.ArrayList;

/**
 * author: Husen
 * date: 2017.11.15
 * description："我要了解"界面，"我的职业"碎片
 */
public class MyOccuFragment extends Fragment {
    private View _mView;
    private ListView _my_occu_list;
    private MyOccuAdapter _myOccuAdapter;
    //职业大类
    private ArrayList<MyOccupation> _occuList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _mView = inflater.inflate(R.layout.fragment_my_occupation, container, false);
        //获取ListView
        _my_occu_list = (ListView) _mView.findViewById(R.id.my_occu_list);

        // TODO-此处填充服务器数据
        _occuList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MyOccupation myOccupation = new MyOccupation("职业一", "幼儿教育", "白丁", 1, 5, "教育");
            _occuList.add(myOccupation);
        }

        _my_occu_list.setAdapter(new MyOccuAdapter());

        return _mView;
    }

    class MyOccuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return _occuList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parentViewGroup) {
            final OneOfMyOccuItem oneOfMyOccuItem;
            //如果其他职业列表为空，则创建
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.my_occupation_item, null);
                oneOfMyOccuItem = new OneOfMyOccuItem(convertView);
                // TODO-此处填充具体职位信息
                oneOfMyOccuItem.fillInContent(_occuList.get(position));
                //不是每次都new,达到重用效果
                convertView.setTag(oneOfMyOccuItem);
            }

            return convertView;
        }

        /**
         * 一个职位的内容对应的布局
         **/
        class OneOfMyOccuItem {
            TextView occupation_num;
            TextView occupation_name;
            TextView curr_level_name;
            TextView curr_level_num;
            TextView total_level_num;
            TextView occupation_type;

            public OneOfMyOccuItem(View itemView) {
                occupation_num = (TextView) itemView.findViewById(R.id.occuption_num);
                occupation_name = (TextView) itemView.findViewById(R.id.occupation_name);
                curr_level_name = (TextView) itemView.findViewById(R.id.curr_level_name);
                curr_level_num = (TextView) itemView.findViewById(R.id.curr_level_num);
                total_level_num = (TextView) itemView.findViewById(R.id.total_level_num);
                occupation_type = (TextView) itemView.findViewById(R.id.occupation_type);
            }

            /** 填充信息 **/
            public void fillInContent(MyOccupation myOccupation) {
                occupation_num.setText(myOccupation.get_occupationNum());
                occupation_name.setText(myOccupation.get_occupationName());
                curr_level_name.setText(myOccupation.get_currLevelName());
                curr_level_num.setText(myOccupation.get_currLevelNum()+"");
                total_level_num.setText("/"+myOccupation.get_totalLevelNum());
                occupation_type.setText(myOccupation.get_occupationType());

                setListener();
            }

            /**
             * 设置具体职位的点击对话框
             **/
            private void setListener() {
                occupation_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String content = "幼儿教育也有广义和狭义之分，从广义上说，凡是能够影响幼儿身体成长和认知、情感、性格等方面发展的有目的的活动，如幼儿在成人的指导下看电视、做家务、参加社会活动，等等，都可说是幼儿教育。而狭义的幼儿教育则特指幼儿园和其他专门开设的幼儿教育机构的教育。幼儿园教育在中国属于学校教育系统，和学校教育一样，幼儿园教育也具有家庭教育和社会教育所没有的优点，如计划性、系统性等。幼儿园教育以幼儿园教师为主要对象，致力于宣传党和国家的幼儿教育政策，反映幼儿教育研究与改革成果，交流幼儿园、托儿所、家庭教育经验，介绍国内外幼儿教育信息，提供幼儿教育活动材料和教学参考资料。";
                        new TopicDialog(getContext(), occupation_name.getText().toString(), content);
                    }
                });
            }
        }
    }
}
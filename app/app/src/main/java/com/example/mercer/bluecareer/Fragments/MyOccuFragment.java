package com.example.mercer.bluecareer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mercer.bluecareer.CommonUtils.OccopationInfoTool;
import com.example.mercer.bluecareer.DataStruct.MyOccupation;
import com.example.mercer.bluecareer.DataStruct.OccupationInfo;
import com.example.mercer.bluecareer.Dialog.OccupationDialog;
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

            /**
             * 填充信息
             **/
            public void fillInContent(MyOccupation myOccupation) {
                occupation_num.setText(myOccupation.get_occupationNum());
                occupation_name.setText(myOccupation.get_occupationName());
                curr_level_name.setText(myOccupation.get_currLevelName());
                curr_level_num.setText(myOccupation.get_currLevelNum() + "");
                total_level_num.setText("/" + myOccupation.get_totalLevelNum());
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
                        View view = v.inflate(getActivity(), R.layout.dialog_occuaption, null);

                        /** 填充职业信息 **/
                        setOccupationInfo(view);

                        /** 显示弹框 **/
                        OccupationDialog.showCustomizeDialog(getContext(), view);
                    }
                });
            }

            /**
             * 获取到弹框的View，并填充职业信息
             * @param view
             */
            public void setOccupationInfo(View view){
                // TODO 此处填充服务器内容
                OccupationInfo occupationInfo = OccupationInfo.getTestInstance();

                ((TextView)view.findViewById(R.id.jobName)).setText(occupationInfo.get_jobName());
                ((TextView)view.findViewById(R.id.jobDuty)).setText(occupationInfo.get_jobDuty());
                ((TextView)view.findViewById(R.id.jobRequire)).setText(occupationInfo.get_jobRequire());

                TableLayout tableLayout = view.findViewById(R.id.occ_info_show);
                for(int i = 0; i< occupationInfo.get_workPlace().size(); i++){
                    TextView tableHeader = OccopationInfoTool.tableHeadTextView(getContext());
                    TextView tableName = OccopationInfoTool.tableNameTextView(getContext());

                    tableHeader.setText(occupationInfo.get_workPlace().get(i));
                    tableName.setText(occupationInfo.get_salary().get(i) + "");

                    TableRow tableRow = new TableRow(getContext());
                    tableRow.addView(tableHeader);
                    tableRow.addView(tableName);

                    tableLayout.addView(tableRow);
                }
            }
        }
    }
}
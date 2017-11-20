package com.example.mercer.bluecareer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mercer.bluecareer.CommonUtils.ConversionTool;
import com.example.mercer.bluecareer.CommonUtils.OccopationInfoTool;
import com.example.mercer.bluecareer.DataStruct.Occupation;
import com.example.mercer.bluecareer.Dialog.OccupationDialog;
import com.example.mercer.bluecareer.Dialog.TopicDialog;
import com.example.mercer.bluecareer.R;

import java.util.ArrayList;

/**
 * author: Husen
 * date: 2017.11.15
 * description：其他职业列表子碎片
 */

public class OtherOccuFragment extends Fragment {
    private View _mView;
    private ListView _other_occu_list;
    private OtherOccuAdapter _otherOccuAdapter;
    //职业大类
    private ArrayList<String> _occuList;
    //item点击位置
    public int clickPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _mView = inflater.inflate(R.layout.fragment_other_occuption, container, false);

        //获取listView
        _other_occu_list = (ListView) _mView.findViewById(R.id.other_occu_list);

        // TODO-此处后面填充服务器内容，职业类别
        _occuList = new ArrayList<>();
        _occuList.add("软件工程");
        _occuList.add("销售");
        _occuList.add("教育");
        _occuList.add("行政");

        _other_occu_list.setAdapter(new OtherOccuAdapter());
        _other_occu_list.setOnItemClickListener(new OtherOccuListViewItemClick());

        return _mView;
    }

    class OtherOccuListViewItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final OtherOccuAdapter.OneOfOtherOccuItem oneOfOtherOccuItem = (OtherOccuAdapter.OneOfOtherOccuItem) view.getTag();

            //当状态条目处于未选中时，说明条目处于未展开状态，此时让他展开。同时切换状态图标的状态
            if (clickPosition == -1) {
                oneOfOtherOccuItem._other_occu_detail_list.setVisibility(View.VISIBLE);
                oneOfOtherOccuItem.selectorImg.animate().rotation(90);
                clickPosition = position;
            }
            //当条目状态图标为选中时，说明该条目处于展开状态，此时让它隐藏，并恢复状态图标的状态
            else if (clickPosition == position) {
                oneOfOtherOccuItem._other_occu_detail_list.setVisibility(View.GONE);
                oneOfOtherOccuItem.selectorImg.animate().rotation(0);
                clickPosition = -1;
            } else {
                _other_occu_list.performItemClick(_other_occu_list.getChildAt(clickPosition), clickPosition, _other_occu_list.getItemIdAtPosition(clickPosition));
                _other_occu_list.performItemClick(_other_occu_list.getChildAt(position), position, _other_occu_list.getItemIdAtPosition(position));
                clickPosition = position;
            }
        }
    }

     /** 职位类别ListView 适配器 **/
    class OtherOccuAdapter extends BaseAdapter {
        @Override
        public View getView(final int position, View convertView, ViewGroup parentViewGroup) {
            final OneOfOtherOccuItem oneOfOtherOccuItem;
            //如果其他职业列表为空，则创建
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.other_occupation_item, null);
                oneOfOtherOccuItem = new OneOfOtherOccuItem(convertView);
                //不是每次都new,达到重用效果
                convertView.setTag(oneOfOtherOccuItem);
            } else {
                oneOfOtherOccuItem = (OneOfOtherOccuItem) convertView.getTag();
            }
            // TODO-设置职业类型
            oneOfOtherOccuItem.occupation_type.setText(_occuList.get(position));

            return convertView;
        }

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

        class OneOfOtherOccuItem {
            TextView occupation_type;
            ImageView selectorImg;
            LinearLayout _other_occu_detail_list;
            //职业小类
            ArrayList<String> _occuDetailList;

            public OneOfOtherOccuItem(View itemView) {
                // TODO-此处填写职业具体名称，如 软件工程
                _occuDetailList = new ArrayList<>();
                for(int i=0; i<23; i++){
                    // TODO- 具体职位名称
                    _occuDetailList.add("第"+i+"个具体职位名称");
                }

                occupation_type = (TextView) itemView.findViewById(R.id.occupation_type);
                selectorImg = (ImageView) itemView.findViewById(R.id.selector_img);
                //可折叠的线性布局列表
                _other_occu_detail_list = (LinearLayout) itemView.findViewById(R.id.other_occu_detail_list);

                loadOtherOccuDetailList();
            }

            //加载职业小类
            private void loadOtherOccuDetailList() {
                int row = _occuDetailList.size() / 3;
                int colmun = _occuDetailList.size() % 3;

                for (int i = 0; i < row; i++) {
                    LinearLayout rowLine = getRowLineLayout();

                    for (int j = 0; j < 3; j++) {
                        final TextView textView = getColmunTextView();
                        textView.setBackground(getResources().getDrawable(R.drawable.other_occu_detail_background));
                        // TODO-设置职业名称
                        textView.setText("软件工程" + (i * 3 + j));

                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                View view = v.inflate(getActivity(), R.layout.dialog_occuaption, null);

                                /** 填充职业信息 **/
                                setOccupationInfo(view);

                                /** 显示弹框 **/
                                OccupationDialog.showCustomizeDialog(getContext(), view);
                            }
                        });
                        rowLine.addView(textView);
                    }

                    _other_occu_detail_list.addView(rowLine);
                }

                LinearLayout rowLine = getRowLineLayout();
                for (int j = 0; j < 3; j++) {
                    final TextView textView = getColmunTextView();

                    if (j + 1 > colmun) {
                        rowLine.addView(textView);
                        continue;
                    }
                    textView.setBackground(getResources().getDrawable(R.drawable.other_occu_detail_background));
                    // TODO-设置职业名称
                    textView.setText("软件工程" + (row * 3 + j));

                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View view = v.inflate(getActivity(), R.layout.dialog_occuaption, null);

                            /** 填充职业信息 **/
                            setOccupationInfo(view);

                            /** 显示弹框 **/
                            OccupationDialog.showCustomizeDialog(getContext(), view);
                        }
                    });
                    rowLine.addView(textView);
                }
                _other_occu_detail_list.addView(rowLine);
            }

            /**
             * 获取到弹框的View，并填充职业信息
             * @param view
             */
            public void setOccupationInfo(View view){
                // TODO 此处填充服务器内容
                Occupation occupation = Occupation.getTestInstance();

                ((TextView)view.findViewById(R.id.jobName)).setText(occupation.get_jobName());
                ((TextView)view.findViewById(R.id.jobDuty)).setText(occupation.get_jobDuty());
                ((TextView)view.findViewById(R.id.jobRequire)).setText(occupation.get_jobRequire());

                TableLayout tableLayout = view.findViewById(R.id.occ_info_show);
                for(int i=0; i<occupation.get_workPlace().size(); i++){
                    TextView tableHeader = OccopationInfoTool.tableHeadTextView(getContext());
                    TextView tableName = OccopationInfoTool.tableNameTextView(getContext());

                    tableHeader.setText(occupation.get_workPlace().get(i));
                    tableName.setText(occupation.get_salary().get(i) + "");

                    TableRow tableRow = new TableRow(getContext());
                    tableRow.addView(tableHeader);
                    tableRow.addView(tableName);

                    tableLayout.addView(tableRow);
                }
            }

            /**
             * 获取一个线性布局
             *
             * @return
             */
            public LinearLayout getRowLineLayout() {
                LinearLayout linearLayout = new LinearLayout(getContext());

                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                return linearLayout;
            }

            /**
             * 获取一个TextView
             *
             * @return
             */
            private TextView getColmunTextView() {
                TextView textView = new TextView(getContext());
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ConversionTool.dp2px(35), 5.0f);
                int dp10 = ConversionTool.dp2px(10);
                layoutParams.setMargins(dp10, dp10, dp10, dp10);

                textView.setLayoutParams(layoutParams);

                return textView;
            }
        }
    }
}
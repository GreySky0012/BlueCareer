package com.example.mercer.bluecareer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private MyAdapter _adapter;
    private ArrayList<String> _list;

    public int clickPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _mView = inflater.inflate(R.layout.second_fragment_other_occuption, container, false);

        _other_occu_list = (ListView) _mView.findViewById(R.id.other_occu_list);
        _adapter = new MyAdapter();
        _other_occu_list.setAdapter(_adapter);

        //此处后面填充服务器内容
        _list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            _list.add("我是第" + i + "个条目");
        }

        return _mView;
    }

    /**
     * 监听列表变化
     */
    class MyAdapter extends BaseAdapter implements View.OnClickListener {
        @Override
        public View getView(final int position, View convertView, ViewGroup parentViewGroup) {
            final MyViewHolder myViewHolder;
            //如果其他职业列表为空，则创建
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.other_occupation_item, null);
                myViewHolder = new MyViewHolder(convertView);
                //不是每次都new,达到重用效果
                convertView.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
            // TODO-设置职业类型
            myViewHolder.textView.setText(_list.get(position));

            /** 刷新adapter的时候，getview重新执行，此时对在点击中标记的position做处理 */
            //当条目为刚才点击的条目时
            if (clickPosition == position) {
                //当条目状态图标为选中时，说明该条目处于展开状态，此时让它隐藏，并切换状态图标的状态
                if (myViewHolder.selectorImg.isSelected()) {
                    myViewHolder.selectorImg.setSelected(false);
                    myViewHolder.ll_hide.setVisibility(View.GONE);
                    Log.e("_other_occu_list", "if执行");

                    //隐藏布局后把标记的position去除掉，否则，滑动listview让该条目划出屏幕范围
                    //当该条目重新进入屏幕后，会重新恢复原来的显示状态。经过打log可知每次else都执行一次 （条目第二次进入屏幕时会在getview中寻找他自己的状态，相当于重新执行一次getview）
                    //因为每次滑动的时候没标记的position填充会执行click
                    clickPosition = -1;
                }
                //当状态条目处于未选中时，说明条目处于未展开状态，此时让他展开。同时切换状态图标的状态
                else {
                    myViewHolder.selectorImg.setSelected(true);
                    myViewHolder.ll_hide.setVisibility(View.VISIBLE);

                    Log.e("_other_occu_list", "else执行");
                }
                /*ObjectAnimator.ofInt(vh.ll_hide, "rotationX", 0.0F, 360.0F)//
                        .setDuration(500)//
                        .start();
                 vh.selectorImg.setSelected(true);*/
            } else {//当填充的条目position不是刚才点击所标记的position时，让其隐藏，状态图标为false。

                //每次滑动的时候没标记得position填充会执行此处，把状态改变。所以如果在以上的if (vh.selectorImg.isSelected()) {}中不设置clickPosition=-1；则条目再次进入屏幕后，还是会进入clickposition==position的逻辑中
                //而之前的滑动（未标记条目的填充）时，执行此处逻辑，已经把状态图片的selected置为false。所以上面的else中的逻辑会在标记过的条目第二次进入屏幕时执行，如果之前的状态是显示，是没什么影响的，再显示一次而已，用户看不出来，但是如果是隐藏状态，就会被重新显示出来
                myViewHolder.ll_hide.setVisibility(View.GONE);
                myViewHolder.selectorImg.setSelected(false);

                Log.e("_other_occu_list", "状态改变");
            }
            myViewHolder.hide_1.setOnClickListener(this);
            myViewHolder.hide_2.setOnClickListener(this);
            myViewHolder.hide_3.setOnClickListener(this);
            myViewHolder.hide_4.setOnClickListener(this);
            myViewHolder.hide_5.setOnClickListener(this);
            myViewHolder.selectorImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "被点了", Toast.LENGTH_SHORT).show();
                    clickPosition = position;//记录点击的position
                    notifyDataSetChanged();//刷新adapter重新填充条目。在重新填充的过程中，被记录的position会做展开或隐藏的动作，具体的判断看上面代码
                    //在此处需要明确的一点是，当adapter执行刷新操作时，整个getview方法会重新执行，也就是条目重新做一次初始化被填充数据。
                    //所以标记position，不会对条目产生影响，执行刷新后 ，条目重新填充当，填充至所标记的position时，我们对他处理，达到展开和隐藏的目的。
                    //明确这一点后，每次点击代码执行逻辑就是 onclick（）---》getview（）
                }
            });
            return convertView;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.hide_1:
                    Toast.makeText(getActivity(), "加密", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.hide_2:
                    Toast.makeText(getActivity(), "解密", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.hide_3:
                    Toast.makeText(getActivity(), "分享", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.hide_4:
                    Toast.makeText(getActivity(), "删除", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.hide_5:
                    Toast.makeText(getActivity(), "属性", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public int getCount() {
            return _list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        class MyViewHolder {
            View itemView;
            TextView textView;
            TextView hide_1, hide_2, hide_3, hide_4, hide_5;
            ImageView selectorImg;
            LinearLayout ll_hide;

            public MyViewHolder(View itemView) {
                this.itemView = itemView;
                textView = (TextView) itemView.findViewById(R.id.occupation_type);
                selectorImg = (ImageView) itemView.findViewById(R.id.checkbox);
                hide_1 = (TextView) itemView.findViewById(R.id.hide_1);
                hide_2 = (TextView) itemView.findViewById(R.id.hide_2);
                hide_3 = (TextView) itemView.findViewById(R.id.hide_3);
                hide_4 = (TextView) itemView.findViewById(R.id.hide_4);
                hide_5 = (TextView) itemView.findViewById(R.id.hide_5);
                ll_hide = (LinearLayout) itemView.findViewById(R.id.ll_hide);
            }
        }
    }
}

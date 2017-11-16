package com.example.mercer.bluecareer.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mercer.bluecareer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroduceFragment extends Fragment {
    private View _mView;

    private Fragment _myOccuFragment;
    private Fragment _otherOccuFragment;

    private RadioGroup _introNavRadioGroup;
    private RadioButton _myOccuRadioBtn;
    private RadioButton _otherOccuRadioBtn;

    private ImageView _chooseMyOccu;
    private ImageView _chooseOtherOccu;

    public IntroduceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (_mView == null) {
            _mView = inflater.inflate(R.layout.fragment_introduce, container, false);
        }
        // 获取View
        setView();
        //初始化二级碎片
        initChildFragments();
        //设置监听
        setListener();

        return _mView;
    }

    /**
     * 初始化二级碎片
     */
    public void initChildFragments() {
       chooseChildFragmentClick(0);
    }

    /**
     * 二级碎片点击事件
     * @param fragmentId
     */
    public void chooseChildFragmentClick(int fragmentId){
        FragmentManager childFmManager = getChildFragmentManager();
        FragmentTransaction transaction = childFmManager.beginTransaction();

        if(fragmentId == 0){
            transaction.replace(R.id.intro_fragment_content, _myOccuFragment);
            _chooseMyOccu.setBackground(getResources().getDrawable(R.drawable.my_occuption_horizontal_line));
            _chooseOtherOccu.setBackground(getResources().getDrawable(R.drawable.bar_background));
        }
        if(fragmentId == 1){
            transaction.replace(R.id.intro_fragment_content, _otherOccuFragment);
            _chooseMyOccu.setBackground(getResources().getDrawable(R.drawable.bar_background));
            _chooseOtherOccu.setBackground(getResources().getDrawable(R.drawable.my_occuption_horizontal_line));
        }

        transaction.commit();
    }

    /**
     * 设置监听器
     */
    public void setListener() {
        _introNavRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //判断选中的id。
                if (checkedId == _myOccuRadioBtn.getId()) {
                    chooseChildFragmentClick(0);
                } else if (checkedId == _otherOccuRadioBtn.getId()) {
                    chooseChildFragmentClick(1);
                }
            }
        });
    }

    /**
     * 设置 View
     */
    private void setView() {
        _myOccuFragment = new MyOccuFragment();
        _otherOccuFragment = new OtherOccuFragment();

        _introNavRadioGroup = (RadioGroup) _mView.findViewById(R.id.introduce_nav);
        _myOccuRadioBtn = (RadioButton) _mView.findViewById(R.id.intro_my_occu);
        _otherOccuRadioBtn = (RadioButton) _mView.findViewById(R.id.intro_other_occu);

        _chooseMyOccu = (ImageView) _mView.findViewById(R.id.occu_choose_my);
        _chooseOtherOccu = (ImageView) _mView.findViewById(R.id.occu_choose_other);
    }
}
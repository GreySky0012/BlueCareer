package com.example.mercer.bluecareer.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mercer.bluecareer.Activities.InfoActivity;
import com.example.mercer.bluecareer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroduceFragment extends Fragment {
    private View _mView;
    private Fragment myOccuFragment;
    private Fragment otherOccuFragment;

    public IntroduceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (_mView == null) {
            _mView = inflater.inflate(R.layout.fragment_introduce, container, false);
        }
        //初始化二级碎片
        initChildFragments();
        //设置监听

        return _mView;
    }

    public void initChildFragments() {
        myOccuFragment = new MyOccuFragment();
        otherOccuFragment = new OtherOccuFragment();

        FragmentManager childFmManager = getChildFragmentManager();
        FragmentTransaction transaction = childFmManager.beginTransaction();
        transaction.replace(R.id.intro_fragment_content, myOccuFragment);
        transaction.commit();
    }
}
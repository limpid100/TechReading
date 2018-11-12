package com.dxl.techreading.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dxl.techreading.R;

/**
 * @author dxl
 * @date 2018/11/9 13:10
 */
public class TestFragment extends Fragment {

    TextView mTextView;

    public static TestFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        mTextView = v.findViewById(R.id.textView);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTextView.setText(arguments.get("title").toString());
        }
    }
}

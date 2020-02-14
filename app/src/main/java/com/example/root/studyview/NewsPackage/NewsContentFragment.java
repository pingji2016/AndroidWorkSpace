package com.example.root.studyview.NewsPackage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.example.root.studyview.R;


/**
  *
  * @ProjectName:
  * @Package:        com.example.root.studyview
  * @ClassName:      NewsContentFragment
  * @Description:     java类作用描述
  * @Author:         ZFM
  * @CreateDate:     2020/2/13 22:49
 */

public class NewsContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container,false);
        return view;
    }

    public void refresh(String newsTitle, String newsContent){
        View visibilitylayout = view.findViewById(R.id.visibility_layout);
        visibilitylayout.setVisibility(View.VISIBLE);
        TextView newsTitileView = (TextView)view.findViewById(R.id.news_title);
        TextView newsContentView = (TextView)view.findViewById(R.id.news_content);
        newsTitileView.setText(newsTitle);
        newsContentView.setText(newsContent);
    }

}

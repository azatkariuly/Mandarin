package com.zerotoone.n17r.mandarin.Memrise;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zerotoone.n17r.mandarin.DatabaseAccess;
import com.zerotoone.n17r.mandarin.MyDataBaseHelper;
import com.zerotoone.n17r.mandarin.Question;
import com.zerotoone.n17r.mandarin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PageFragment extends Fragment {

    WordsDatabaseOpenHelper wordsDatabase;
    List<Question> list;

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";


    int pageNumber;
    int i = 0;

    public PageFragment() {
        // Required empty public constructor
    }


    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PAGE_NUMBER, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, null);


        wordsDatabase = new WordsDatabaseOpenHelper(getActivity());
        list = wordsDatabase.getAllQuestionList();

        final TextView tvPage = (TextView) view.findViewById(R.id.tvPage);
        final TextView tvPage2 = (TextView) view.findViewById(R.id.tvPage2);
        CardView cv = (CardView) view.findViewById(R.id.cardView);


        tvPage.setText(list.get(pageNumber).getQuestion());
        tvPage2.setText(list.get(pageNumber).getAnswer());

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    tvPage.setVisibility(View.INVISIBLE);
                    tvPage2.setVisibility(View.VISIBLE);
                    i = 1;
                } else {
                    tvPage.setVisibility(View.VISIBLE);
                    tvPage2.setVisibility(View.INVISIBLE);
                    i = 0;
                }
            }
        });


        return view;
    }





}

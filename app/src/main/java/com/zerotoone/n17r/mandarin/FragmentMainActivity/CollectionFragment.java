package com.zerotoone.n17r.mandarin.FragmentMainActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerotoone.n17r.mandarin.CustomAdapter;
import com.zerotoone.n17r.mandarin.DatabaseAccess;
import com.zerotoone.n17r.mandarin.MyDataBaseHelper;
import com.zerotoone.n17r.mandarin.Question;
import com.zerotoone.n17r.mandarin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CollectionFragment extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    public static List<Question> list;
    MyDataBaseHelper myDataBaseHelper;

    SharedPreferences sharedPreferences;

    SearchView searchView;

    public CollectionFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection, container, false);




        myDataBaseHelper = new MyDataBaseHelper(getActivity());
        list = myDataBaseHelper.getAllQuestionList();

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        //searchView.setQueryHint("Search Here");
        //searchView.setQueryRefinementEnabled(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomAdapter(initData());
        recyclerView.setAdapter(adapter);
        return view;
    }


    public List<Question> initData() {

        List<Question> questions1 = new ArrayList<>();
        sharedPreferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        int q = sharedPreferences.getInt("number", 0);

        for(int i=0; i<q+5; i++) {
            questions1.add(new Question(list.get(i).getQuestion(),
                   list.get(i).getPinyin(), list.get(i).getAnswer(), list.get(i).getExample(), list.get(i).getTrans()));
        }

        return questions1;
    }



}

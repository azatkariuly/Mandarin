package com.zerotoone.n17r.mandarin.FragmentMainActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zerotoone.n17r.mandarin.DatabaseAccess;
import com.zerotoone.n17r.mandarin.Memrise.CardsActivity;
import com.zerotoone.n17r.mandarin.Memrise.DrawActivity;
import com.zerotoone.n17r.mandarin.Memrise.LearnActivity;
import com.zerotoone.n17r.mandarin.Memrise.SpellActivity;
import com.zerotoone.n17r.mandarin.Memrise.WordsDatabaseOpenHelper;
import com.zerotoone.n17r.mandarin.MyCustomAdapter;
import com.zerotoone.n17r.mandarin.MyDataBaseHelper;
import com.zerotoone.n17r.mandarin.Question;
import com.zerotoone.n17r.mandarin.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class MainActivityFragment extends Fragment implements View.OnClickListener{

    List<Question> questions, list;
    int mNumberQuestion;

    MyDataBaseHelper myDataBaseHelper;


    WordsDatabaseOpenHelper wordsDatabase;
    SQLiteDatabase db;

    private TextView mDate;

    SharedPreferences sPref;
    public static final String SAVED = "date";

    public MainActivityFragment() {
        // Required empty public constructor
    }
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);

        mDate = (TextView) view.findViewById(R.id.currDate);
        view.findViewById(R.id.flashCardsMain).setOnClickListener(this);
        view.findViewById(R.id.learnMain).setOnClickListener(this);
        view.findViewById(R.id.spellMain).setOnClickListener(this);
        view.findViewById(R.id.drawMain).setOnClickListener(this);


        //current Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM, yyyy");
        String currDate = sdf.format(new Date());
        mDate.setText(currDate);



        //saving date for the first run
        if(getDate().equals("")) {
            saveDate(currDate);
            mNumberQuestion = 0;

            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this.getActivity());
            databaseAccess.open();
            questions = databaseAccess.getAllQuestions();
            databaseAccess.close();

            myDataBaseHelper = new MyDataBaseHelper(getActivity());
            myDataBaseHelper.addWords(questions);
            list = myDataBaseHelper.getAllQuestionList();
        }

        mNumberQuestion = getNumber();

        myDataBaseHelper = new MyDataBaseHelper(getActivity());
        list = myDataBaseHelper.getAllQuestionList();



        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerVew);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        if(!checkDate()) {
            saveDate(currDate);
            saveNumber(mNumberQuestion);
            mNumberQuestion = mNumberQuestion + 5;
            MyCustomAdapter adapter = new MyCustomAdapter(updateQuestion());
            recyclerView.setAdapter(adapter);
        } else {
            
            mNumberQuestion = getNumber();
            MyCustomAdapter adapter = new MyCustomAdapter(updateQuestion());
            recyclerView.setAdapter(adapter);
        }

        getBrainstormData();

        return view;
    }



    public List<Question> updateQuestion() {
        List<Question> qw = new ArrayList<>();
        for (int i =mNumberQuestion; i<mNumberQuestion+5; i++) {
            qw.add(new Question(list.get(i).getQuestion(),
                    list.get(i).getPinyin(), list.get(i).getAnswer(), list.get(i).getExample(), list.get(i).getTrans()));
        }
        return qw;
    }

    public void saveNumber(int num) {
        sPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt("number", num+5);
        editor.commit();
    }

    public int getNumber() {
        sPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        return sPref.getInt("number", 0);
    }



    public void saveDate(String currDate) {
        sPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(SAVED, currDate);
        editor.commit();
    }

    public String getDate() {
        sPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        return sPref.getString(SAVED, "");
    }

    public boolean checkDate() {
        sPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        return mDate.getText().toString().equals(sPref.getString(SAVED, ""));
    }


//////////////////////////////
    public void getBrainstormData() {
        wordsDatabase = new WordsDatabaseOpenHelper(getActivity());
        db = wordsDatabase.getWritableDatabase();
        db.delete(WordsDatabaseOpenHelper.TABLE_QUESTION, null, null);

        /////
        List<Question> qwerty = updateQuestion();
        Collections.shuffle(qwerty);
        wordsDatabase.addWords(qwerty);
        db.close();
        wordsDatabase.close();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.flashCardsMain) {
            startActivity(new Intent(getActivity(), CardsActivity.class));
        } else if (v.getId() == R.id.learnMain) {
            startActivity(new Intent(getActivity(), LearnActivity.class));
        } else if (v.getId() == R.id.spellMain) {
            startActivity(new Intent(getActivity(), SpellActivity.class));
        }  else if (v.getId() == R.id.drawMain) {
            startActivity(new Intent(getActivity(), DrawActivity.class));
        }
    }

    @Override
    public void onPause() {
        wordsDatabase = new WordsDatabaseOpenHelper(getActivity());
        db = wordsDatabase.getWritableDatabase();
        db.delete(WordsDatabaseOpenHelper.TABLE_QUESTION, null, null);

        /////
        List<Question> qwerty = updateQuestion();
        Collections.shuffle(qwerty);
        wordsDatabase.addWords(qwerty);
        db.close();
        wordsDatabase.close();

        super.onPause();
    }
}

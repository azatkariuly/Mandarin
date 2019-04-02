package com.zerotoone.n17r.mandarin;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zerotoone.n17r.mandarin.FragmentMainActivity.MainActivityFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Azat Kun on 8/1/2017.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.ViewHolder> {

    List<Question> list;
    TextToSpeech t1;

    public MyCustomAdapter(List<Question> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Question question = list.get(position);

        holder.mQuestion.setText(question.getQuestion());
        holder.mPinyin.setText(question.getPinyin());
        holder.mAnswer.setText(question.getAnswer());
        holder.mExample.setText(question.getExample());
        holder.mTrans.setText(question.getTrans());

        holder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t1=new TextToSpeech(v.getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.CHINA);
                            t1.speak(question.getQuestion(), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mQuestion, mPinyin, mAnswer, mExample, mTrans;
        ImageView mImage;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            mQuestion = (TextView) itemView.findViewById(R.id.questionRec);
            mPinyin = (TextView) itemView.findViewById(R.id.pinyinRec);
            mAnswer = (TextView) itemView.findViewById(R.id.answerRec);
            mImage = (ImageView) itemView.findViewById(R.id.playWord);
            mExample = (TextView) itemView.findViewById(R.id.exampleRec);
            mTrans = (TextView) itemView.findViewById(R.id.translationRec);


        }
    }

}

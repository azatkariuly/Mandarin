package com.zerotoone.n17r.mandarin;

/**
 * Created by Azat Kun on 7/31/2017.
 */

public class Question {

    private String question;
    private String pinyin;
    private String answer;
    private String example;
    private String trans;

    public Question() {

    }


    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public Question(String question, String pinyin, String answer, String example, String trans) {
        this.question = question;
        this.pinyin = pinyin;
        this.answer = answer;
        this.example = example;
        this.trans = trans;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}

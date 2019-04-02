package com.zerotoone.n17r.mandarin.FragmentMainActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zerotoone.n17r.mandarin.Memrise.CardsActivity;
import com.zerotoone.n17r.mandarin.Memrise.LearnActivity;
import com.zerotoone.n17r.mandarin.Memrise.SpellActivity;
import com.zerotoone.n17r.mandarin.R;


public class BrainstormFragment extends Fragment implements View.OnClickListener {


    public BrainstormFragment() {
        // Required empty public constructor
    }

    public static BrainstormFragment newInstance(String param1, String param2) {
        BrainstormFragment fragment = new BrainstormFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brainstorm, container, false);

        view.findViewById(R.id.cardsBtn).setOnClickListener(this);
        view.findViewById(R.id.learnBtn).setOnClickListener(this);
        view.findViewById(R.id.spellBtn).setOnClickListener(this);



        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardsBtn) {
            Intent intent = new Intent(getActivity(), CardsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.learnBtn) {
            Intent intent = new Intent(getActivity(), LearnActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.spellBtn) {
            Intent intent = new Intent(getActivity(), SpellActivity.class);
            startActivity(intent);
        }


    }
}

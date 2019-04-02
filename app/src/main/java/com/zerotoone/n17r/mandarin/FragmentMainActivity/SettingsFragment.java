package com.zerotoone.n17r.mandarin.FragmentMainActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zerotoone.n17r.mandarin.R;
import com.zerotoone.n17r.mandarin.StartingActivity;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;


    TextView txt;
    Button button;
    SharedPreferences sharedPreferences;

    public SettingsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        sharedPreferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);

        txt = (TextView) view.findViewById(R.id.settingsTxt);
        view.findViewById(R.id.btn_LogOut).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this.getActivity(), StartingActivity.class));
        }

        FirebaseUser user = mAuth.getCurrentUser();

        txt.setText(user.getEmail());



        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_LogOut) {
            mAuth.signOut();
            startActivity(new Intent(this.getActivity(), StartingActivity.class));
        }
    }
}

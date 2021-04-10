package com.example.artist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class insertprofile extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container ,@NonNull Bundle savedInstanceState)
    {
         View view = inflater.inflate(R.layout.activity_insertprofile,container,false);
         return view ;
    }

    private void setContentView(int activity_insertprofile) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = getActivity().findViewById(R.id.tv05);

        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
             case R.id.tv05:
            Intent intent = new Intent(getActivity(), profile.class );
            startActivity(intent);


        }
    }
}
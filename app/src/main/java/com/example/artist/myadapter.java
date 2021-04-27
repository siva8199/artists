package com.example.artist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<AllUserMmber , myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<AllUserMmber> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull AllUserMmber model) {
        holder.name.setText(model.getName());
        holder.gender.setText(model.getGender());
        holder.profession.setText(model.getProf());
        holder.location.setText(model.getLocation());
        holder.email.setText(model.getEmail());
        holder.soc.setText(model.getSco());
        Glide.with(holder.image.getContext()).load(model.getUrl()).into(holder.image);



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_profile , parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,email,profession,location,gender,soc,option;
        Button btnq;
        ImageView image;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.iv_cp);
            name = (TextView)itemView.findViewById(R.id.et_name_cp);
            name.setEnabled(false);
            email = (TextView)itemView.findViewById(R.id.et_email_cp);
            email.setEnabled(false);
            profession = (TextView)itemView.findViewById(R.id.et_prof_cp);
            profession.setEnabled(false);
            location = (TextView)itemView.findViewById(R.id.et_loc_cp);
            location.setEnabled(false);
            gender = (TextView)itemView.findViewById(R.id.et_gen_cp);
            gender.setEnabled(false);
            soc = (TextView)itemView.findViewById(R.id.et_ph_cp);
            soc.setEnabled(false);
            option =(TextView)itemView.findViewById(R.id.diabtn);
            option.setVisibility(View.INVISIBLE);


            btnq = (Button)itemView.findViewById(R.id.btn_cp);
            btnq.setVisibility(View.INVISIBLE);

        }
    }
}

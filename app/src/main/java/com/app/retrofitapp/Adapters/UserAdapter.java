package com.app.retrofitapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.retrofitapp.Pojo.BioData;
import com.app.retrofitapp.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<BioData> list;
    Context ctx;

    public UserAdapter(List<BioData> list,Context ctx){

        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder viewHolder, int i) {

        BioData data = list.get(i);

        viewHolder.myName.setText(data.getName());
        viewHolder.myAge.setText(data.getAge());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView myName,myAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myName = itemView.findViewById(R.id.myName);
            myAge = itemView.findViewById(R.id.myAge);
        }
    }
}

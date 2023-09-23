package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PerfilesAdapter extends RecyclerView.Adapter<PerfilesAdapter.PerfilesViewHolder> {
    private List<Perfiles> perfilesLsta;
    private Context context;

    public PerfilesAdapter(List<Perfiles> pl) {
        this.perfilesLsta = pl;
    }

    @NonNull
    @Override

    public PerfilesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View view=LayoutInflater.from(context).inflate(R.layout.perfiles_layout,parent,false);
        return new PerfilesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilesViewHolder holder, int position) {
        Perfiles p=perfilesLsta.get(position);
        holder.idTextView.setText("Id:"+p.getId());
        holder.documentoTextView.setText("documento:"+p.getDocumento());
    }

    @Override
    public int getItemCount() {
        return perfilesLsta.size();
    }


    public class PerfilesViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView documentoTextView;

        public PerfilesViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView=itemView.findViewById(R.id.idTextView);
            documentoTextView=itemView.findViewById(R.id.documentoTextView);
        }
    }
}

package com.example.ishumishra97.internathmtechnologies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sumit on 01-Aug-16.
 */
public class Model {

    private String name,classs,section;

    public Model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}


   /* public String getStoppage() {
        return stoppage;
    }

    public void setStoppage(String stoppage) {
        this.stoppage = stoppage;
    }*/

class RVAdapter extends  RecyclerView.Adapter<RVAdapter.ModelViewHolder>{

     List<Model> modelsList;




     public RVAdapter(List<Model> modelsList) {
         this.modelsList = modelsList;
     }

    public RVAdapter(ArrayList<Model> modelsList){
        this.modelsList=modelsList;
    }



    @Override
     public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
         ModelViewHolder mvh=new ModelViewHolder(v);

         return mvh;
     }

     @Override
     public void onBindViewHolder(ModelViewHolder holder, int position) {
         holder.tvname.setText(modelsList.get(position).getName());
         holder.tvclass.setText(modelsList.get(position).getClasss());
         holder.tvsection.setText(modelsList.get(position).getSection());

     }

     @Override
     public int getItemCount() {

         if(modelsList!=null){
             return  modelsList.size();
         }
         return 0;
     }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public  static class ModelViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvname,tvclass,tvsection;
           public ModelViewHolder(View itemView) {
             super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.card_view);
            tvname=(TextView) itemView.findViewById(R.id.textViewname);
            tvclass=(TextView) itemView.findViewById(R.id.textViewClass);
               tvsection=(TextView)itemView.findViewById(R.id.textViewSection);
         }
     }
 }




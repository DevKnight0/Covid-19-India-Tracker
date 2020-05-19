package com.aankik.covid19indiastatus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class Covid19Adapter extends RecyclerView.Adapter<Covid19Adapter.Holder>{

    private List<StateDataModel> mstateDataModels;

    public Covid19Adapter(List<StateDataModel> mstateDataModels, Context mContext) {
        this.mstateDataModels = mstateDataModels;
        this.mContext = mContext;
    }

    private Context mContext;
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateDataModel clickedItem = mstateDataModels.get(position);
                Intent perStateIntent = new Intent(mContext, IndividualStateData.class);

                perStateIntent.putExtra("STATE_NAME", clickedItem.getState());
                perStateIntent.putExtra("STATE_CONFIRMED", clickedItem.getConfirmed());
                perStateIntent.putExtra("STATE_ACTIVE", clickedItem.getActive());
                perStateIntent.putExtra("STATE_DECEASED", clickedItem.getDeceased());
                perStateIntent.putExtra("STATE_NEW_CONFIRMED", clickedItem.getNewConfirmed());
                perStateIntent.putExtra("STATE_NEW_RECOVERED", clickedItem.getNewRecovered());
                perStateIntent.putExtra("STATE_NEW_DECEASED", clickedItem.getNewDeceased());
                perStateIntent.putExtra("STATE_RECOVERED", clickedItem.getRecovered());


                mContext.startActivity(perStateIntent);
            }
        });
        StateDataModel model = mstateDataModels.get(position);
        holder.stateName.setText(model.getState());
        holder.confirmed.setText(model.getConfirmed());
    }

    @Override
    public int getItemCount() {
        return mstateDataModels.size();
    }

    public void loadNewData(List<StateDataModel> data){
        this.mstateDataModels=data;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder{
       TextView stateName;
        TextView confirmed;

        public Holder(@NonNull View itemView) {
            super(itemView);
            stateName=(TextView) itemView.findViewById(R.id.state_name);
            confirmed=(TextView) itemView.findViewById(R.id.confirmed);

        }


    }
}

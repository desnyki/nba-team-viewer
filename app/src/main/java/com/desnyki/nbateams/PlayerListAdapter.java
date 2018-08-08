package com.desnyki.nbateams;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.desnyki.nbateams.data.Player;

import java.util.List;


public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {

    private List<Player> mPlayerItems;

    public PlayerListAdapter(List<Player> PlayerItems){
        mPlayerItems = PlayerItems;
    }

    public void replaceItems(List<Player> PlayerItems){
        mPlayerItems = PlayerItems;
        notifyDataSetChanged();
    }

    @Override
    public PlayerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        ViewHolder vh;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.fName.setText(mPlayerItems.get(i).getFirstName());
        holder.lName.setText(mPlayerItems.get(i).getLastName());
        holder.position.setText(mPlayerItems.get(i).getPosition() + "");
        holder.number.setText(mPlayerItems.get(i).getNumber() + "");
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView fName;
        TextView lName;
        TextView position;
        TextView number;

        public ViewHolder(View v) {
            super(v);
            fName = v.findViewById(R.id.first_name);
            lName = v.findViewById(R.id.last_name);
            position = v.findViewById(R.id.position);
            number = v.findViewById(R.id.number);
        }
    }

    @Override
    public int getItemCount() {
        return mPlayerItems.size();
    }
}

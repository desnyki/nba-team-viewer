package com.desnyki.nbateams;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.reactivex.functions.Action;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.ViewHolder> {

    private List<TeamListItem> mTeamItems;

    public TeamListAdapter(List<TeamListItem> teamItems){
        mTeamItems = teamItems;
    }

    public void replaceItems(List<TeamListItem> teamItems){
        mTeamItems = teamItems;
        notifyDataSetChanged();
    }

    @Override
    public TeamListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        ViewHolder vh;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_item, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mTeamItems.get(position).getTeam().getFullName());
        holder.wins.setText(mTeamItems.get(position).getTeam().getWins() + "");
        holder.losses.setText(mTeamItems.get(position).getTeam().getLosses() + "");
        holder.mOnItemClickAction = mTeamItems.get(position).getClickListener();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView wins;
        TextView losses;
        private Action mOnItemClickAction;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.team_name);
            wins = v.findViewById(R.id.wins);
            losses = v.findViewById(R.id.losses);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickAction != null) {
                try {
                    mOnItemClickAction.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mTeamItems.size();
    }
}

package com.desnyki.nbateams;

import com.desnyki.nbateams.data.Team;

import io.reactivex.functions.Action;

public class TeamListItem {
    private Team team;
    private Action clickListener;

    public TeamListItem(Team team, Action clickListener){
        this.team = team;
        this.clickListener = clickListener;
    }

    public Team getTeam() {
        return team;
    }

    public Action getClickListener() {
        return clickListener;
    }
}

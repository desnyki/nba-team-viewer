package com.desnyki.nbateams.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Team {
    int id;
    @SerializedName("full_name")
    String fullName;
    int wins;
    int losses;
    List<Player> players = new ArrayList<>();

    public Team(int teamId, String fullName, int wins, int losses, List<Player> players) {
        this.id = teamId;
        this.fullName = fullName;
        this.wins = wins;
        this.losses = losses;
        this.players = players;
    }

    public int getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + id +
                ", fullName='" + fullName + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                ", players=" + players +
                '}';
    }
}

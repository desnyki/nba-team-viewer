package com.desnyki.nbateams.data.source;

import com.desnyki.nbateams.data.Team;
import com.desnyki.nbateams.data.source.remote.TeamsRemoteDataSource;

import java.util.List;

import io.reactivex.Single;

public class Repository  {

    private static Repository INSTANCE;

    private TeamsRemoteDataSource mTeamsRemoteDataSource;

    private List<Team> teams;

    public static Repository getInstance(TeamsRemoteDataSource source){
        if(INSTANCE == null){
            INSTANCE = new Repository(source);
        }
        return INSTANCE;
    }

    private Repository(TeamsRemoteDataSource source){
        mTeamsRemoteDataSource = source;
    }

    public Single<List<Team>> getData(){
        if(teams == null){
            return mTeamsRemoteDataSource
                    .getTeams()
                    .doOnSuccess(this::setTeams); //simple caching without persistence
        } else {
            return Single.just(teams);
        }

    }

    private void setTeams(List<Team> teams){
        this.teams = teams;
    }

}

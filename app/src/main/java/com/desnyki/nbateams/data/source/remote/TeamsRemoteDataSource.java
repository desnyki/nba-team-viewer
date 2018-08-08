package com.desnyki.nbateams.data.source.remote;


import com.desnyki.nbateams.data.Team;

import java.util.List;

import io.reactivex.Single;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamsRemoteDataSource{

    private static TeamsRemoteDataSource INSTANCE;

    private TeamsRemoteDataSource() {
    }

    public static TeamsRemoteDataSource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new TeamsRemoteDataSource();
        }
        return INSTANCE;
    }

    public Single<List<Team>> getTeams() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        TeamsDataService service = retrofit.create(TeamsDataService.class);
        return service.getTeams();
    }

}

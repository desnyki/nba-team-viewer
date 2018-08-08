package com.desnyki.nbateams.data.source.remote;

import com.desnyki.nbateams.data.Team;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamsDataService {
    @GET("input.json")
    Single<List<Team>> getTeams();
}

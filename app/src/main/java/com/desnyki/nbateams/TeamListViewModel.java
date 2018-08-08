package com.desnyki.nbateams;

import com.desnyki.nbateams.data.Team;
import com.desnyki.nbateams.data.source.Repository;
import com.desnyki.nbateams.data.source.remote.TeamsRemoteDataSource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Single;

public class TeamListViewModel {

    Repository mRepository;

    Comparator alphComp = (Comparator<Team>) (a, b) -> a.getFullName().compareTo(b.getFullName());
    Comparator winsComp = (Comparator<Team>) (a, b) -> Integer.compare(b.getWins(), a.getWins());
    Comparator lossesComp = (Comparator<Team>) (a, b) -> Integer.compare(b.getLosses(), a.getLosses());

    public TeamListViewModel(){
        mRepository = Repository.getInstance(TeamsRemoteDataSource.getInstance());
    }

    public Single<List<Team>> getTeams(){
        return mRepository.getData();
    }

    public Single<List<Team>> getTeams(SortType type){
        return mRepository.getData()
                .map(list -> sortTeams(list, type));
    }

    public List<Team> sortTeams(List<Team> list, SortType type){
        switch(type){
            case ALPHABETICAL:
                Collections.sort(list, alphComp);
                break;
            case WINS:
                Collections.sort(list, winsComp);
                break;
            case LOSSES:
                Collections.sort(list, lossesComp);
                break;
        }

        return list;
    }

    public enum SortType {
        ALPHABETICAL, WINS, LOSSES
    }

}

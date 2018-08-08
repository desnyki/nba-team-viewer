package com.desnyki.nbateams;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.desnyki.nbateams.data.Team;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TeamViewActivity extends AppCompatActivity {

    TeamListViewModel mViewModel;

    private CompositeDisposable mCompositeDisposable;

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    private PlayerListAdapter mListAdapter;
    
    TextView teamNameLabel;
    TextView winsLabel;
    TextView lossesLabel;

    int teamId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_view);

        teamId = getIntent().getIntExtra("team_id", -1);

        mViewModel = new TeamListViewModel();

        mRecyclerView = findViewById(R.id.player_list);

        mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mListAdapter = new PlayerListAdapter(new ArrayList<>(0));
        mRecyclerView.setAdapter(mListAdapter);

        teamNameLabel = findViewById(R.id.team_name);
        winsLabel = findViewById(R.id.wins);
        lossesLabel = findViewById(R.id.losses);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindViewModel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindViewModel();
    }

    private void bindViewModel(){
        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(
                mViewModel.getTeams()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toObservable()
                        .flatMap(Observable::fromIterable)
                        .filter(team -> team.getId() == teamId)
                        .take(1)
                        .subscribe(this::setUI));
    }

    private void unbindViewModel(){
        mCompositeDisposable.clear();
    }
    private void setUI(Team response){
        teamNameLabel.setText(response.getFullName());
        winsLabel.setText(response.getWins()+"");
        lossesLabel.setText(response.getLosses()+"");
        mListAdapter.replaceItems(response.getPlayers());
    }
}

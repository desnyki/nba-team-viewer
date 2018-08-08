package com.desnyki.nbateams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.desnyki.nbateams.data.Team;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.desnyki.nbateams.TeamListViewModel.SortType.*;

public class TeamListActivity extends AppCompatActivity {

    TeamListViewModel mViewModel;

    private CompositeDisposable mCompositeDisposable;

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    private TeamListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);
        mViewModel = new TeamListViewModel();

        mRecyclerView = findViewById(R.id.teams_list);

        mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mListAdapter = new TeamListAdapter(new ArrayList<>(0));
        mRecyclerView.setAdapter(mListAdapter);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alphabetical:
                getTeams(ALPHABETICAL);
                return true;
            case R.id.wins:
                getTeams(WINS);
                return true;
            case R.id.losses:
                getTeams(LOSSES);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void bindViewModel(){
        mCompositeDisposable = new CompositeDisposable();
        getTeams(ALPHABETICAL);
    }

    private void unbindViewModel(){
        mCompositeDisposable.clear();
    }

    private List<TeamListItem> constructTeamListItems(List<Team> response){
        List<TeamListItem> ret = new ArrayList<>();
        response.forEach(team -> ret.add(new TeamListItem(team, () -> showActivity(team.getId()))));
        return ret;
    }

    public void getTeams(TeamListViewModel.SortType type){
        mCompositeDisposable.add(
                mViewModel.getTeams(type)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(this::constructTeamListItems)
                        .subscribe(this::setUI));
    }

    public void showActivity(int id){
        final Intent activityIntent = new Intent(getApplicationContext(), TeamViewActivity.class);
        activityIntent.putExtra("team_id", id);
        startActivity(activityIntent);
    }

    private void setUI(List<TeamListItem> response){
        mListAdapter.replaceItems(response);
    }
}

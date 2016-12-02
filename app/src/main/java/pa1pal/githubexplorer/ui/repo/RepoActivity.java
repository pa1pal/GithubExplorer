package pa1pal.githubexplorer.ui.repo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pa1pal.githubexplorer.R;
import pa1pal.githubexplorer.data.DataManager;
import pa1pal.githubexplorer.data.model.Repos;
import pa1pal.githubexplorer.data.model.Users;
import pa1pal.githubexplorer.utils.RecyclerItemClickListner;

public class RepoActivity extends AppCompatActivity implements RecyclerItemClickListner.OnItemClickListener, RepoContract.View {

    @BindView(R.id.repolist)
    RecyclerView repoRecyclerView;
    String username;
    private Users users;
    private DataManager dataManager;
    private RepoAdapter mainAdapter;
    private RepoContract.Presenter mainPresenter;

    View rootView;
    List<Users> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        ButterKnife.bind(this);
        mainAdapter = new RepoAdapter();
        mainAdapter.setContext(this);
        dataManager = new DataManager();
        mainPresenter = new RepoPresenter(dataManager, this);
        username = getIntent().getStringExtra("username");
        mainPresenter.subscribe();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpRecyclerView();
        mainPresenter.loadRepos(username);
    }

    @Override
    public void setUpRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        repoRecyclerView.setLayoutManager(layoutManager);
        repoRecyclerView.addOnItemTouchListener(new RecyclerItemClickListner(getApplicationContext(), this));
        repoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        repoRecyclerView.setAdapter(mainAdapter);
    }


    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error loading post", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showComplete() {
        Toast.makeText(this, "Completed loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUpAdapter(List<Repos> reposList) {
        mainAdapter.setUsers(reposList);
    }

    @Override
    public void setPresenter(RepoContract.Presenter presenter) {
        mainPresenter = presenter;
    }

    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.unsubscribe();
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

}

package pa1pal.githubexplorer.ui.repo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pa1pal.githubexplorer.R;
import pa1pal.githubexplorer.data.DataManager;
import pa1pal.githubexplorer.data.model.Repos;
import pa1pal.githubexplorer.utils.EndlessRecyclerViewScrollListener;
import pa1pal.githubexplorer.utils.ItemOffsetDecoration;
import pa1pal.githubexplorer.utils.RecyclerItemClickListner;
import pa1pal.githubexplorer.utils.Utils;

public class RepoActivity extends AppCompatActivity implements RecyclerItemClickListner.OnItemClickListener, RepoContract.View {

    @BindView(R.id.repolist)
    RecyclerView repoRecyclerView;

    String username;

    int ownerId;

    private DataManager dataManager;

    private RepoAdapter repoAdapter;

    private RepoContract.Presenter repoPresenter;

    private ProgressDialog progressDialog;

    List<Repos> repositoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        ButterKnife.bind(this);
        repoAdapter = new RepoAdapter();
        repositoryList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        repoAdapter.setContext(this);
        dataManager = new DataManager();
        repoPresenter = new RepoPresenter(dataManager, this);
        username = getIntent().getStringExtra(getString(R.string.username));
        ownerId = getIntent().getIntExtra("ownerId", 1);
        repoPresenter.subscribe();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpRecyclerView();

        if (Utils.isConnected(this)) {
            repoPresenter.loadRepos(username, 1, ownerId);
        } else {
            repoPresenter.loadLocalRepos(ownerId);
            repoRecyclerView.clearOnScrollListeners();
        }

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.loadingrepositories));
        progressDialog.show();
    }

    @Override
    public void setUpRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        repoRecyclerView.setLayoutManager(layoutManager);
        repoRecyclerView.addOnItemTouchListener(new RecyclerItemClickListner(getApplicationContext(), this));
        repoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        repoRecyclerView.setAdapter(repoAdapter);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this,
                R.dimen.item_offset);
        repoRecyclerView.addItemDecoration(itemDecoration);
        repoRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (Utils.isConnected(RepoActivity.this)) {
                    repoPresenter.loadRepos(username, ++page, ownerId);
                }
                Toast.makeText(RepoActivity.this, R.string.loading_more_repositories, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void showError(String message) {
        Toast.makeText(this, R.string.error_loading_repositories, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        Toast.makeText(this, R.string.complete_loading, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void setUpAdapter(List<Repos> reposList) {
        this.repositoryList.addAll(reposList);
        repoAdapter.setRepositories(reposList);
    }

    @Override
    public void setPresenter(RepoContract.Presenter presenter) {
        repoPresenter = presenter;
    }

    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repoPresenter.unsubscribe();
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

}
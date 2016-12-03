package pa1pal.githubexplorer.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pa1pal.githubexplorer.R;
import pa1pal.githubexplorer.data.DataManager;
import pa1pal.githubexplorer.data.model.Users;
import pa1pal.githubexplorer.ui.repo.RepoActivity;
import pa1pal.githubexplorer.utils.ItemOffsetDecoration;
import pa1pal.githubexplorer.utils.RecyclerItemClickListner;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListner.OnItemClickListener, MainContract.View {

    @BindView(R.id.userslist)
    RecyclerView usersList;
    String query;
    private Users users;
    private DataManager dataManager;
    private MainAdapter mainAdapter;
    private MainContract.Presenter mainPresenter;
    List<Users> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainAdapter = new MainAdapter();
        mainAdapter.setContext(this);
        dataManager = new DataManager();
        mainPresenter = new MainPresenter(dataManager, this);
        mainPresenter.subscribe();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handleIntent(getIntent());
        setUpRecyclerView();
        mainPresenter.loadFromDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setUpRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        usersList.setLayoutManager(layoutManager);
        usersList.addOnItemTouchListener(new RecyclerItemClickListner(getApplicationContext(), this));
        usersList.setItemAnimator(new DefaultItemAnimator());
        usersList.setAdapter(mainAdapter);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this,
                R.dimen.item_offset);
        usersList.addItemDecoration(itemDecoration);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mainPresenter.loadPost(query);
        }
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
    public void setUpAdapter(List<Users> list) {
        mainAdapter.setUsers(list);
        this.list = list;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mainPresenter = presenter;
    }

    @Override
    public void onItemClick(View childView, int position) {
        Intent intent = new Intent(this, RepoActivity.class);
        intent.putExtra("username", list.get(position).getLogin());
        list.get(position).save();
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.unsubscribe();
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mainPresenter.loadFromDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.loadFromDatabase();
    }
}

package pa1pal.githubexplorer.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import pa1pal.githubexplorer.ui.main.MainActivity;
import pa1pal.githubexplorer.ui.repo.RepoActivity;
import pa1pal.githubexplorer.utils.RecyclerItemClickListner;

public class HomeActivity extends AppCompatActivity implements RecyclerItemClickListner.OnItemClickListener, HomeContract.View {

    @BindView(R.id.userslist)
    RecyclerView recyclerViewGrid;
    private DataManager dataManager;
    private HomeAdapter mainAdapter;
    private HomeContract.Presenter mainPresenter;
    List<Users> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainAdapter = new HomeAdapter();
        mainAdapter.setContext(this);
        dataManager = new DataManager();
        mainPresenter = new HomePresenter(dataManager, this);
        mainPresenter.subscribe();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUpRecyclerView();
        mainPresenter.loadLocalUsers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent searchIntent = new Intent(this, MainActivity.class);
            startActivity(searchIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setUpRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewGrid.setLayoutManager(layoutManager);
        recyclerViewGrid.addOnItemTouchListener(new RecyclerItemClickListner(getApplicationContext(), this));
        recyclerViewGrid.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGrid.setAdapter(mainAdapter);
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
    public void setUpAdapter(List<Users> userses) {
        mainAdapter.setUsers(userses);
        this.list = userses;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
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

}

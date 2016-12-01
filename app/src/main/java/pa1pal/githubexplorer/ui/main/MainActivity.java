package pa1pal.githubexplorer.ui.main;

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
import pa1pal.githubexplorer.utils.RecyclerItemClickListner;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListner.OnItemClickListener, MainContract.View{

    @BindView(R.id.userslist)
    RecyclerView recyclerViewGrid;

    public static final int GRID_LAYOUT_COUNT = 2;
    private Users users;
    private DataManager dataManager;
    private MainAdapter mainAdapter;
    private MainContract.Presenter mainPresenter;

    View rootView;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUpRecyclerView();
        mainPresenter.loadPost();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
//        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getApplicationContext(),
//                R.dimen.item_offset);
        //recyclerViewGrid.addItemDecoration(itemDecoration);
        //Setting the Equal column spacing
//        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(),
//                R.dimen.item_offset);
//        recyclerViewGrid.addItemDecoration(itemDecoration);

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

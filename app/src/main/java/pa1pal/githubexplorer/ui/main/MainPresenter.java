package pa1pal.githubexplorer.ui.main;

import android.content.Context;

import java.util.List;

import pa1pal.githubexplorer.data.DataManager;
import pa1pal.githubexplorer.data.model.Search;
import pa1pal.githubexplorer.data.model.Users;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private Subscription subscription;
    private MainContract.View view;
    private DataManager dataManager;
    private MainAdapter mainAdapter;
    private Context context;

    public MainPresenter(DataManager dataManager, MainContract.View view){
        this.dataManager = dataManager;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadPost(String q) {
        subscription = dataManager.getUsers(q)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Search>() {
                    @Override
                    public void onCompleted() {
                       view.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.toString());
                    }

                    @Override
                    public void onNext(Search search) {
                        view.setUpAdapter(search.getItems());
                    }
                });

    }

    @Override
    public void loadFromDatabase() {
        subscription = dataManager.getLocalUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Users>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Users> userses) {
                        view.setUpAdapter(userses);

                    }
                });
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        if (subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}

package pa1pal.githubexplorer.ui.main;

import android.content.Context;

import pa1pal.githubexplorer.data.DataManager;
import pa1pal.githubexplorer.data.model.Search;
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
    public void loadPost() {
        subscription = dataManager.getUsers()
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
                        view.setUpAdapter(search);
                    }

                });

    }

    @Override
    public void subscribe() {
        loadPost();
    }

    @Override
    public void unsubscribe() {
        if (subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}

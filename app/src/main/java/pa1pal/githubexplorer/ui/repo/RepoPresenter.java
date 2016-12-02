package pa1pal.githubexplorer.ui.repo;

import android.content.Context;

import java.util.List;

import pa1pal.githubexplorer.data.DataManager;
import pa1pal.githubexplorer.data.model.Repos;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepoPresenter implements RepoContract.Presenter {

    private static final String TAG = RepoPresenter.class.getSimpleName();
    private Subscription subscription;
    private RepoContract.View view;
    private DataManager dataManager;
    private RepoAdapter mainAdapter;
    private Context context;

    public RepoPresenter(DataManager dataManager, RepoContract.View view) {
        this.dataManager = dataManager;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadRepos(String username) {
        subscription = dataManager.getRepos(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repos>>() {
                    @Override
                    public void onCompleted() {
                        view.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.toString());
                    }

                    @Override
                    public void onNext(List<Repos> ul) {
                        view.setUpAdapter(ul);
                    }
                });

    }

    @Override
    public void subscribe() {    }

    @Override
    public void unsubscribe() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

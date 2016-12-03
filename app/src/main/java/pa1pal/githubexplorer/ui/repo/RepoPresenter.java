package pa1pal.githubexplorer.ui.repo;

import android.content.Context;

import java.util.List;

import pa1pal.githubexplorer.data.DataManager;
import pa1pal.githubexplorer.data.model.Repos;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
    public void loadRepos(String username, Integer page, int ownerId) {
        subscription = dataManager.getRepos(username, page, ownerId)
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
    public void loadLocalRepos(int ownerId) {
        subscription = dataManager.getLocalRepos(ownerId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<Repos>>() {
                    @Override
                    public void call(List<Repos> reposes) {
                        view.showComplete();
                        view.setUpAdapter(reposes);
                    }
                });
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
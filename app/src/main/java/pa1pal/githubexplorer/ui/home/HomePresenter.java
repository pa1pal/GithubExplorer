package pa1pal.githubexplorer.ui.home;

import android.content.Context;

import java.util.List;

import pa1pal.githubexplorer.data.DataManager;
import pa1pal.githubexplorer.data.model.Users;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private static final String TAG = HomePresenter.class.getSimpleName();
    private Subscription subscription;
    private HomeContract.View view;
    private DataManager dataManager;
    private HomeAdapter mainAdapter;
    private Context context;

    public HomePresenter(DataManager dataManager, HomeContract.View view){
        this.dataManager = dataManager;
        this.view = view;
        view.setPresenter(this);
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

    @Override
    public void loadLocalUsers() {
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
}

package pa1pal.githubexplorer.ui.home;

import java.util.List;

import pa1pal.githubexplorer.data.model.Users;
import pa1pal.githubexplorer.ui.base.BasePresenter;
import pa1pal.githubexplorer.ui.base.BaseView;

public class HomeContract {
    interface View extends BaseView<Presenter> {

        void setUpRecyclerView();
        void showError(String message);
        void showComplete();
        void setUpAdapter(List<Users> userses);
    }

    interface Presenter extends BasePresenter {
        void loadLocalUsers();

    }
}

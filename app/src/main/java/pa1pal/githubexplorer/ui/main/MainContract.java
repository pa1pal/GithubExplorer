package pa1pal.githubexplorer.ui.main;

import pa1pal.githubexplorer.data.model.Search;
import pa1pal.githubexplorer.ui.base.BasePresenter;
import pa1pal.githubexplorer.ui.base.BaseView;

public class MainContract {
    interface View extends BaseView<Presenter> {

        void setUpRecyclerView();
        void showError(String message);
        void showComplete();
        void setUpAdapter(Search search);
    }

    interface Presenter extends BasePresenter {
        void loadPost(String q);

    }
}

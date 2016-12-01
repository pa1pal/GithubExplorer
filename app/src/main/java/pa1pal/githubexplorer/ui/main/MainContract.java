package pa1pal.githubexplorer.ui.main;

import java.util.List;

import pa1pal.githubexplorer.data.model.Users;
import pa1pal.githubexplorer.ui.base.BasePresenter;
import pa1pal.githubexplorer.ui.base.BaseView;

public class MainContract {
    interface View extends BaseView<Presenter> {

        void setUpRecyclerView();

        void showError(String message);
        //void showPosts(List<Post> posts);
        void showComplete();

        void setUpAdapter(List<Users> list);
        //void showProgressbar(boolean show);
    }

    interface Presenter extends BasePresenter {
        void loadPost();

    }
}

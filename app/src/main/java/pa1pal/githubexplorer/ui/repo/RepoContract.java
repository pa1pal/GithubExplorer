package pa1pal.githubexplorer.ui.repo;

import java.util.List;

import pa1pal.githubexplorer.data.model.Repos;
import pa1pal.githubexplorer.ui.base.BasePresenter;
import pa1pal.githubexplorer.ui.base.BaseView;

public class RepoContract {
    interface View extends BaseView<Presenter> {

        void setUpRecyclerView();

        void showError(String message);

        void showComplete();

        void setUpAdapter(List<Repos> reposList);
    }

    interface Presenter extends BasePresenter {
        void loadRepos(String username, Integer page, int ownerId);

        void loadLocalRepos(int ownerId);

    }
}

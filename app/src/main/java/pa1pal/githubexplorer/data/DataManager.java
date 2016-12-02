package pa1pal.githubexplorer.data;


import java.util.List;

import pa1pal.githubexplorer.data.model.Repos;
import pa1pal.githubexplorer.data.model.Search;
import pa1pal.githubexplorer.data.remote.ApiManager;
import rx.Observable;

public class DataManager {

    ApiManager apiManager;
    public DataManager(){
        apiManager=new ApiManager();
    }

    public Observable<Search> getUsers(String query) {
        return apiManager.getGithubApi().getUsers(query);
    }

    public Observable<List<Repos>> getRepos(String username) {
        return apiManager.getGithubApi().getRepos(username);
    }
}


package pa1pal.githubexplorer.data;


import java.util.List;

import pa1pal.githubexplorer.data.local.DBHelper;
import pa1pal.githubexplorer.data.model.Repos;
import pa1pal.githubexplorer.data.model.Search;
import pa1pal.githubexplorer.data.model.Users;
import pa1pal.githubexplorer.data.remote.ApiManager;
import rx.Observable;

public class DataManager {

    DBHelper dbHelper;

    ApiManager apiManager;
    public DataManager(){
        apiManager=new ApiManager();
        dbHelper = new DBHelper();
    }

    public Observable<Search> getUsers(String query) {
        return apiManager.getGithubApi().getUsers(query);
    }

    public Observable<List<Repos>> getRepos(String username) {
        return apiManager.getGithubApi().getRepos(username);
    }

    public Observable<List<Users>> getLocalUsers(){
        return dbHelper.getLocalUser();
    }
}


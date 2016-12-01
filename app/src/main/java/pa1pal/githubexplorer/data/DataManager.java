package pa1pal.githubexplorer.data;


import java.util.List;

import pa1pal.githubexplorer.data.model.Users;
import pa1pal.githubexplorer.data.remote.ApiManager;
import rx.Observable;

public class DataManager {

    ApiManager apiManager;
    public DataManager(){
        apiManager=new ApiManager();
    }

    public Observable<List<Users>> getUsers() {
        return apiManager.getGithubApi().getUsers();
    }
}


package pa1pal.githubexplorer.data;


import pa1pal.githubexplorer.data.model.Search;
import pa1pal.githubexplorer.data.remote.ApiManager;
import rx.Observable;

public class DataManager {

    ApiManager apiManager;
    public DataManager(){
        apiManager=new ApiManager();
    }

    public Observable<Search> getUsers() {
        return apiManager.getGithubApi().getUsers();
    }
}


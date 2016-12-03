package pa1pal.githubexplorer.data;


import java.util.List;

import pa1pal.githubexplorer.data.local.DBHelper;
import pa1pal.githubexplorer.data.model.Repos;
import pa1pal.githubexplorer.data.model.Search;
import pa1pal.githubexplorer.data.model.Users;
import pa1pal.githubexplorer.data.remote.ApiManager;
import rx.Observable;
import rx.functions.Func1;

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

    public Observable<List<Repos>> getRepos(String username, Integer page, final Integer ownerId) {
        return apiManager.getGithubApi().getRepos(username, page)
                .concatMap(new Func1<List<Repos>, Observable<? extends List<Repos>>>() {
                    @Override
                    public Observable<? extends List<Repos>> call(List<Repos> reposes) {
                        return dbHelper.saveRepositories(reposes, ownerId);
                    }
                });
    }

    public Observable<List<Repos>> getLocalRepos(int ownerId) {
        return dbHelper.getLocalRepositories(ownerId);
    }

    public Observable<List<Users>> getLocalUsers(){
        return dbHelper.getLocalUser();
    }
}


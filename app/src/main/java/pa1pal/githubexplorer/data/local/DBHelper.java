package pa1pal.githubexplorer.data.local;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import pa1pal.githubexplorer.data.model.Repos;
import pa1pal.githubexplorer.data.model.Repos_Table;
import pa1pal.githubexplorer.data.model.Users;
import rx.Observable;
import rx.functions.Func0;

public class DBHelper {

    public Observable<List<Users>> getLocalUser() {
        List<Users> users = SQLite.select()
                .from(Users.class)
                .queryList();
        return Observable.just(users);
    }

    public Observable<List<Repos>> saveRepositories(final List<Repos> repoList, final Integer userID){
        return Observable.defer(new Func0<Observable<List<Repos>>>() {
            @Override
            public Observable<List<Repos>> call() {
                for (Repos repos : repoList) {
                    repos.setOwnerID(userID);
                    repos.save();
                }
                return Observable.just(repoList);
            }
        });
    }
    public Observable<List<Repos>> getLocalRepositories(final Integer userId){
        return Observable.defer(new Func0<Observable<List<Repos>>>() {
            @Override
            public Observable<List<Repos>> call() {
                List<Repos> reposes = SQLite.select()
                        .from(Repos.class)
                        .where(Repos_Table.ownerID.eq(userId))
                        .queryList();

                return Observable.just(reposes);
            }
        });


    }
}

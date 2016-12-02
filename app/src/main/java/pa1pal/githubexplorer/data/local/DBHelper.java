package pa1pal.githubexplorer.data.local;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import pa1pal.githubexplorer.data.model.Users;
import rx.Observable;

public class DBHelper {

    public Observable<List<Users>> getLocalUser() {
        List<Users> users = SQLite.select()
                .from(Users.class)
                .queryList();
        return Observable.just(users);
    }
}

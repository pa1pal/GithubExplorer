package pa1pal.githubexplorer.data.remote;

import java.security.acl.Owner;
import java.util.List;

import pa1pal.githubexplorer.data.model.Users;
import retrofit2.http.GET;
import rx.Observable;

public interface GithubService {
    @GET("users/pa1pal")
    Observable<List<Users>> getUsers();

    @GET("users/{username}/repos")
    Observable<List<Owner>> getRepos();

}

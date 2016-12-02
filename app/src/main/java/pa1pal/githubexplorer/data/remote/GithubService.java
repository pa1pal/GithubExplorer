package pa1pal.githubexplorer.data.remote;

import java.security.acl.Owner;
import java.util.List;

import pa1pal.githubexplorer.data.model.Search;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GithubService {
    @GET("search/users")
    Observable<Search> getUsers(@Query("q") String query);

    @GET("users/{username}/repos")
    Observable<List<Owner>> getRepos();

}

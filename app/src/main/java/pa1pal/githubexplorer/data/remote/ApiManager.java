package pa1pal.githubexplorer.data.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    public static final String GITHUB_END_POINT = "https://api.github.com/";

    private <T> T createJsonApi(Class<T> clazz, String ENDPOINT) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

    public GithubService getGithubApi() {
        return createJsonApi(GithubService.class, GITHUB_END_POINT);
    }
}

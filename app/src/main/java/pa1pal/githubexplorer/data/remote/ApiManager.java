package pa1pal.githubexplorer.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    public static final String GITHUB_END_POINT = "https://api.github.com/";

    private <T> T createJsonApi(Class<T> clazz, String ENDPOINT) {

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        httpBuilder.addNetworkInterceptor(new StethoInterceptor());

// create the OkHttpClient instance from httpBuilder
        OkHttpClient client = httpBuilder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

    public GithubService getGithubApi() {
        return createJsonApi(GithubService.class, GITHUB_END_POINT);
    }
}

package pa1pal.githubexplorer;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
    }
}

package pa1pal.githubexplorer.data.local;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = GithubExplorerDatabase.NAME, version = GithubExplorerDatabase.VERSION)

public class GithubExplorerDatabase {
    public static final String NAME = "Github";

    public static final int VERSION = 1;

}

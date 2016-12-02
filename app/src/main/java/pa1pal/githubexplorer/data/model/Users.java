package pa1pal.githubexplorer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import pa1pal.githubexplorer.data.local.GithubExplorerDatabase;

@Table(database = GithubExplorerDatabase.class)
@ModelContainer
public class Users extends BaseModel implements Parcelable{

    @SerializedName("login")
    @Column
    private String login;

    @SerializedName("id")
    @PrimaryKey
    private Integer id;

    @SerializedName("avatar_url")
    @Column
    private String avatarUrl;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {

        return avatarUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeValue(this.id);
        dest.writeString(this.avatarUrl);
    }

    public Users() {
    }

    protected Users(Parcel in) {
        this.login = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.avatarUrl = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel source) {
            return new Users(source);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}
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
public class Repos extends BaseModel implements Parcelable {

    @Column
    Integer ownerID;

    @SerializedName("id")
    @PrimaryKey
    private Integer id;

    @SerializedName("name")
    @Column
    private String name;

    @SerializedName("full_name")
    @Column
    private String fullName;

    @SerializedName("description")
    @Column
    private String description;

    @SerializedName("stargazers_count")
    @Column
    private Integer stargazersCount;

    @SerializedName("forks_count")
    @Column
    private Integer forksCount;

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Repos() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.ownerID);
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeString(this.description);
        dest.writeValue(this.stargazersCount);
        dest.writeValue(this.forksCount);
    }

    protected Repos(Parcel in) {
        this.ownerID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.fullName = in.readString();
        this.description = in.readString();
        this.stargazersCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.forksCount = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Repos> CREATOR = new Creator<Repos>() {
        @Override
        public Repos createFromParcel(Parcel source) {
            return new Repos(source);
        }

        @Override
        public Repos[] newArray(int size) {
            return new Repos[size];
        }
    };
}
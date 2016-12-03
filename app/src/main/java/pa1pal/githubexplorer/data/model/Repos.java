package pa1pal.githubexplorer.data.model;

import com.google.gson.annotations.SerializedName;

public class Repos {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("owner")
    private Owner owner;

    @SerializedName("description")
    private String description;

    @SerializedName("stargazers_count")
    private Integer stargazersCount;

    @SerializedName("forks_count")
    private Integer forksCount;

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
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

    public Owner getOwner() {
        return owner;
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

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
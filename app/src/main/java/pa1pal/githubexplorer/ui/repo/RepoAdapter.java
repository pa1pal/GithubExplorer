package pa1pal.githubexplorer.ui.repo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pa1pal.githubexplorer.R;
import pa1pal.githubexplorer.data.model.Repos;


public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private List<Repos> list;

    Context context;

    public RepoAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_view, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.repo_name.setText(list.get(position).getName());
        holder.repo_desc.setText(list.get(position).getDescription());
        holder.stargazersCount.setText("" + list.get(position).getStargazersCount());
        holder.forksCount.setText("" + list.get(position).getForksCount());
    }

    public void setRepositories(List<Repos> reposes) {
        list.addAll(reposes);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repository_name)
        TextView repo_name;

        @BindView(R.id.repository_description)
        TextView repo_desc;

        @BindView(R.id.stargazers_count)
        TextView stargazersCount;

        @BindView(R.id.forks_count)
        TextView forksCount;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
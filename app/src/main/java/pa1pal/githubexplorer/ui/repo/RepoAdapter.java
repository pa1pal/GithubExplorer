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

    public RepoAdapter(){
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_view, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.title.setText(list.get(position).getTitle());
        holder.repo_name.setText(list.get(position).getName());
        holder.repo_desc.setText(list.get(position).getDescription());
    }

    public void setUsers(List<Repos> reposes) {
        list = reposes;
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

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void setContext(Context context){
        this.context = context;
    }
}
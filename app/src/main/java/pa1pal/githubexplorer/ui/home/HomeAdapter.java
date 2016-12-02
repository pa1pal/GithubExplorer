package pa1pal.githubexplorer.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pa1pal.githubexplorer.R;
import pa1pal.githubexplorer.data.model.Users;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Users> list;
    Context context;

    public HomeAdapter(){
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_view, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.title.setText(list.get(position).getTitle());
        holder.author.setText(list.get(position).getLogin());

        Picasso.with(context)
                .load(list.get(position).getAvatarUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.userImage);
    }

    public void setUsers(List<Users> users) {
        list = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_gravatar)
        ImageView userImage;

        @BindView(R.id.user_login)
        TextView author;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void setContext(Context context){
        this.context = context;
    }
}
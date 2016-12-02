package pa1pal.githubexplorer.ui.main;

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
import pa1pal.githubexplorer.data.model.Search;
import pa1pal.githubexplorer.data.model.Users;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Users> list;
    private Search search;
    Context context;

    public MainAdapter(){
        list = new ArrayList<>();
        search = new Search();
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
        holder.author.setText("by "+list.get(position).getLogin());

        Picasso.with(context)
                .load(list.get(position).getAvatarUrl())
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(holder.userImage);

//        imageLoader = ImageLoaderHelper.getInstance(holder.itemView.getContext()).getImageLoader();
//        holder.newsImage.setImageUrl(list.get(position).getThumb(), imageLoader);
//        holder.newsImage.setAspectRatio(list.get(position).getAspectRatio());

    }

    public void setUsers(List<Users> users) {
        list = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return search.getItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_gravatar)
        ImageView userImage;

//        @BindView(R.id.title)
//        TextView title;

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
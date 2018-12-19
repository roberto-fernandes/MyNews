package robfernandes.xyz.mynews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import robfernandes.xyz.mynews.ui.Activities.NewsDisplayActivity;
import robfernandes.xyz.mynews.network.model.APIResponseSearch;
import robfernandes.xyz.mynews.R;

/**
 * Created by Roberto Fernandes on 17/12/2018.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<APIResponseSearch.Doc> mNewsResultsList;
    private Context mContext;

    public SearchAdapter(List<APIResponseSearch.Doc> newsResultsList, Context context) {
        mNewsResultsList = newsResultsList;
        mContext = context;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, int i) {
        APIResponseSearch.Doc news = mNewsResultsList.get(i);
        String newsTitle = news.getHeadline().getMain();
        String category = news.getSectionName();
        String date = news.getPubDate();
        if (date != null && date.length() > 11) {
            date = date.substring(0, 10); //only year, month and day
        }
        String imageURL = "https://www.nytimes.com/";

        //if there is only 1 image take the url of that one, if there are more, take the medium size
        if (news.getMultimedia().size() == 1) {
            imageURL += news.getMultimedia().get(0).getUrl();
        } else if (news.getMultimedia().size() == 2) {
            imageURL += news.getMultimedia().get(1).getUrl();
        } else if (news.getMultimedia().size() > 2) {
            imageURL += news.getMultimedia().get(2).getUrl();
        }

        if (news.getMultimedia().size() > 0) {
            Glide.with(mContext).load(imageURL).into(viewHolder.image);
        }

        viewHolder.title.setText(newsTitle);
        viewHolder.date.setText(date);
        viewHolder.category.setText(category);
    }

    @Override
    public int getItemCount() {
        if (mNewsResultsList == null) {
            return 0;
        }
        return mNewsResultsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private TextView date;
        private TextView category;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.news_row_title);
            image = itemView.findViewById(R.id.news_row_image);
            date = itemView.findViewById(R.id.news_row_date);
            category = itemView.findViewById(R.id.news_row_category);


            itemView.setOnClickListener(v -> {
                int itemPosition = getAdapterPosition();
                String url = mNewsResultsList.get(itemPosition).getWebUrl();

                Intent intent = new Intent(mContext, NewsDisplayActivity.class);
                intent.putExtra("URL", url);
                mContext.startActivity(intent);
            });
        }
    }
}

package robfernandes.xyz.mynews.View;

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

import robfernandes.xyz.mynews.Controller.Activities.NewsDisplayActivity;
import robfernandes.xyz.mynews.Model.APIResponseMostPopular;
import robfernandes.xyz.mynews.R;

/**
 * Created by Roberto Fernandes on 12/12/2018.
 */
public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.ViewHolder> {
    private List<APIResponseMostPopular.Result> mNewsResultsList;
    private Context mContext;

    public MostPopularAdapter(List<APIResponseMostPopular.Result> newsResultsList, Context context)
    {
        mNewsResultsList = newsResultsList;
        mContext = context;
    }

    @NonNull
    @Override
    public MostPopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostPopularAdapter.ViewHolder viewHolder, int i) {
        APIResponseMostPopular.Result news;
        news = mNewsResultsList.get(i);
        String newsTitle = news.getTitle();
        String newsCategory = news.getSection();
        String date = news.getPublishedDate();
        if (date != null && date.length() > 11) {
            date = date.substring(0, 10); //only year, month and day
        }
        String imageURL;

        //if there is only 1 image take the url of that one, if there are more, take the medium size
        List<APIResponseMostPopular.MediaMetadatum> mediaMetadataList =
                news.getMedia().get(0).getMediaMetadata();
        if (mediaMetadataList.size() > 0) {
            imageURL = mediaMetadataList.get(0).getUrl();
            Glide.with(mContext).load(imageURL).into(viewHolder.image);
        }

        viewHolder.title.setText(newsTitle);
        viewHolder.category.setText(newsCategory);
        viewHolder.date.setText(date);
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
        private TextView category;
        private TextView date;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.news_row_title);
            image = itemView.findViewById(R.id.news_row_image);
            category = itemView.findViewById(R.id.news_row_category);
            date = itemView.findViewById(R.id.news_row_date);


            itemView.setOnClickListener(v -> {
                int itemPosition = getAdapterPosition();
                String url = mNewsResultsList.get(itemPosition).getUrl();

                Intent intent = new Intent(mContext, NewsDisplayActivity.class);
                intent.putExtra("URL", url);
                mContext.startActivity(intent);
            });
        }
    }
}

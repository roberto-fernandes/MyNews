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
import robfernandes.xyz.mynews.Model.APIResponse;
import robfernandes.xyz.mynews.R;

/**
 * Created by Roberto Fernandes on 12/12/2018.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<APIResponse.Result> mNewsResultsList = null;
    private static final String TAG = "RecyclerViewAdapter";
    private Context mContext;

    public RecyclerViewAdapter(List<APIResponse.Result> newsResultsList, Context context) {
        mNewsResultsList = newsResultsList;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {
        APIResponse.Result news = mNewsResultsList.get(i);
        String newsTitle = news.getTitle();
        String newsCategory = news.getSection();
        String date = news.getUpdatedDate();
        date = date.substring(0, 10); //only year, month and day
        String imageURL = "";

        //if there is only 1 image take the url of that one, if there are more, take the medium size
        if (news.getMultimedia().size() == 1 ) {
            imageURL = news.getMultimedia().get(0).getUrl();
            Glide.with(mContext).load(imageURL).into(viewHolder.image);
        } else if (news.getMultimedia().size() >1) {
            imageURL = news.getMultimedia().get(1).getUrl();
        }

        if (news.getMultimedia().size() > 0) {
            Glide.with(mContext).load(imageURL).into(viewHolder.image);
        }

        if (!news.getSubsection().equals("")) {
            newsCategory += " > "+ news.getSubsection();
        }


        viewHolder.title.setText(newsTitle);
        viewHolder.category.setText(newsCategory);
        viewHolder.date.setText(date);
    }

    @Override
    public int getItemCount() {
        if (mNewsResultsList==null) {
            return 0;
        }
        return mNewsResultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private TextView category;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.news_row_title);
            image = itemView.findViewById(R.id.news_row_image);
            category = itemView.findViewById(R.id.news_row_category);
            date = itemView.findViewById(R.id.news_row_date);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getAdapterPosition();
                    String url = mNewsResultsList.get(itemPosition).getUrl();

                    Intent intent = new Intent(mContext, NewsDisplayActivity.class);
                    intent.putExtra("URL", url);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}

package robfernandes.xyz.mynews.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import robfernandes.xyz.mynews.Model.APIResponse;
import robfernandes.xyz.mynews.R;

/**
 * Created by Roberto Fernandes on 12/12/2018.
 */
public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ViewHolder> {
    private List<APIResponse.Result> mNewsResultsList = null;

    public TopStoriesAdapter(List<APIResponse.Result> newsResultsList) {
        mNewsResultsList = newsResultsList;
    }

    @NonNull
    @Override
    public TopStoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoriesAdapter.ViewHolder viewHolder, int i) {
        String newsTitle = mNewsResultsList.get(i).getTitle();
        viewHolder.title.setText(newsTitle);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.news_row_title);
        }
    }
}

package robfernandes.xyz.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import robfernandes.xyz.mynews.Model.APIMethods;
import robfernandes.xyz.mynews.Model.APIResponse;
import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.View.TopStoriesAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TopStoriesAdapter mTopStoriesAdapter;
    private List<APIResponse.Result> mNewsList;
    private APIMethods mAPIMethods;

    public TopStoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_top_stories_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTopStoriesAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAPIMethods = new APIMethods();
        mNewsList = mAPIMethods.getTopStories();
        mTopStoriesAdapter = new TopStoriesAdapter(mNewsList);
    }
}

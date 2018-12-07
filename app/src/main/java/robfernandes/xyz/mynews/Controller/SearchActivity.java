package robfernandes.xyz.mynews.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import robfernandes.xyz.mynews.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        CategoriesMenuFragment categoriesMenuFragment = new CategoriesMenuFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_search_linear_layout, categoriesMenuFragment)
                .commit();
    }
}

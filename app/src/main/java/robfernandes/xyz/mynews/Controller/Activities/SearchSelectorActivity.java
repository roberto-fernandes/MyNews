package robfernandes.xyz.mynews.Controller.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import robfernandes.xyz.mynews.Model.DataManager;
import robfernandes.xyz.mynews.R;

import static robfernandes.xyz.mynews.Utils.Constants.ARTS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.BEGIN_DATE_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.BUSINESS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.END_DATE_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.OTHER_CATEGORIES_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.POLITICS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.QUERY_TERM_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.SPORTS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.TRAVEL_STATUS_KEY;

public class SearchSelectorActivity extends AppCompatActivity {

    private TextView beginDateTextView;
    private TextView endDateTextView;
    private EditText searchQueryInput;
    private Calendar mRightNow;
    private Calendar mBeginDateCalendar;
    protected DatePickerDialog.OnDateSetListener mEndDateSetListener;
    private Calendar mEndDateCalendar;
    private String term;
    private String mBeginDate;
    private String mEndDate;
    private CheckBox sportsCheckbox;
    private CheckBox artsCheckbox;
    private CheckBox travelCheckbox;
    private CheckBox politicsCheckbox;
    private CheckBox otherCheckbox;
    private CheckBox businessCheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setViews();
        setClickListeners();
        mBeginDate = DataManager.formatDateToCallAPI(mRightNow);
        mEndDate = DataManager.formatDateToCallAPI(mRightNow);
    }

    private void setClickListeners() {
        DatePickerDialog.OnDateSetListener beginDateSetListener =
                (view, year, month, dayOfMonth) -> {
                    //Set the date on the view when the user finish selecting a date
                    mBeginDateCalendar = Calendar.getInstance();
                    mBeginDateCalendar.set(year, month, dayOfMonth);
                    mBeginDate = DataManager.formatDateToCallAPI(mBeginDateCalendar);
                    displayTime(beginDateTextView, mBeginDateCalendar);
                };
        mEndDateSetListener = (view, year, month, dayOfMonth) -> {
            //Set the date on the view when the user finish selecting a date
            mEndDateCalendar = Calendar.getInstance();
            mEndDateCalendar.set(year, month, dayOfMonth);
            mEndDate = DataManager.formatDateToCallAPI(mEndDateCalendar);
            displayTime(endDateTextView, mEndDateCalendar);
        };
        beginDateTextView.setOnClickListener(setDatePicker(beginDateSetListener));
        endDateTextView.setOnClickListener(setDatePicker(mEndDateSetListener));

        Button button = findViewById(R.id.activity_search_button);
        button.setOnClickListener(v -> {
            if (sportsCheckbox.isChecked() || artsCheckbox.isChecked()
                    || travelCheckbox.isChecked()
                    || politicsCheckbox.isChecked()
                    || otherCheckbox.isChecked()
                    || businessCheckbox.isChecked()) {
                term = searchQueryInput.getText().toString();

                Intent intent = new Intent(SearchSelectorActivity.this,
                        SearchDisplayActivity.class);
                intent.putExtra(QUERY_TERM_KEY, term);
                intent.putExtra(BEGIN_DATE_KEY, mBeginDate); //YYYYMMDD
                intent.putExtra(END_DATE_KEY, mEndDate); //YYYYMMDD
                intent.putExtra(SPORTS_STATUS_KEY, sportsCheckbox.isChecked());
                intent.putExtra(ARTS_STATUS_KEY, artsCheckbox.isChecked());
                intent.putExtra(TRAVEL_STATUS_KEY, travelCheckbox.isChecked());
                intent.putExtra(POLITICS_STATUS_KEY, politicsCheckbox.isChecked());
                intent.putExtra(OTHER_CATEGORIES_STATUS_KEY, otherCheckbox.isChecked());
                intent.putExtra(BUSINESS_STATUS_KEY, businessCheckbox.isChecked());
                startActivity(intent);
            } else {
                Toast.makeText(SearchSelectorActivity.this,
                        "Please select at least one category", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @NonNull
    private View.OnClickListener setDatePicker(
            final DatePickerDialog.OnDateSetListener mDateSetListener) {
        return v -> {
            DatePickerDialog dialog;
            dialog = new DatePickerDialog(SearchSelectorActivity.this,
                    mDateSetListener,
                    mRightNow.get(Calendar.YEAR),
                    mRightNow.get(Calendar.MONTH),
                    mRightNow.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        };
    }

    private void setViews() {
        searchQueryInput = findViewById(R.id.search_query_input_edit_text);
        beginDateTextView = findViewById(R.id.activity_search_begin_date_selector);
        endDateTextView = findViewById(R.id.activity_search_end_date_selector);
        mRightNow = Calendar.getInstance();
        displayTime(beginDateTextView, mRightNow);
        displayTime(endDateTextView, mRightNow);
        sportsCheckbox = findViewById(R.id.categories_checkboxes_sports);
        artsCheckbox = findViewById(R.id.categories_checkboxes_arts);
        travelCheckbox = findViewById(R.id.categories_checkboxes_travel);
        politicsCheckbox = findViewById(R.id.categories_checkboxes_politics);
        otherCheckbox = findViewById(R.id.categories_checkboxes_other_topics);
        businessCheckbox = findViewById(R.id.categories_checkboxes_business);
    }

    @SuppressLint("SimpleDateFormat")
    private void displayTime(TextView textView, Calendar date) {
        SimpleDateFormat format;
        format = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = format.format(date.getTime());
        textView.setText(dateString);
    }
}

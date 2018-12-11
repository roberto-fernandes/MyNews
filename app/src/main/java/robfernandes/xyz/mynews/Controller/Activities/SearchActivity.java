package robfernandes.xyz.mynews.Controller.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import robfernandes.xyz.mynews.R;

public class SearchActivity extends AppCompatActivity {

    private TextView beginDateTextView;
    private TextView endDateTextView;
    private Calendar mRightNow;
    private Calendar mBeginDate;
    private DatePickerDialog.OnDateSetListener mBeginDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;
    private Calendar mEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setViews();
        setClickListeners();
    }

    private void setClickListeners() {
        mBeginDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Set the date on the view when the user finish selecting a date
                mBeginDate = Calendar.getInstance();
                mBeginDate.set(year, month, dayOfMonth);
                displayTime(beginDateTextView, mBeginDate);
            }
        };
        mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Set the date on the view when the user finish selecting a date
                mEndDate = Calendar.getInstance();
                mEndDate.set(year, month, dayOfMonth);
                displayTime(endDateTextView, mEndDate);
            }
        };
        beginDateTextView.setOnClickListener(setDatePicker(mBeginDateSetListener));
        endDateTextView.setOnClickListener(setDatePicker(mEndDateSetListener));
    }

    @NonNull
    private View.OnClickListener setDatePicker(final DatePickerDialog.OnDateSetListener mDateSetListener) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog;
                dialog = new DatePickerDialog(SearchActivity.this,
                        mDateSetListener,
                        mRightNow.get(Calendar.YEAR),
                        mRightNow.get(Calendar.MONTH),
                        mRightNow.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        };
    }

    private void setViews() {
        beginDateTextView = findViewById(R.id.activity_search_begin_date_selector);
        endDateTextView = findViewById(R.id.activity_search_end_date_selector);
        mRightNow = Calendar.getInstance();
        displayTime(beginDateTextView, mRightNow);
        displayTime(endDateTextView, mRightNow);
    }

    private void displayTime(TextView textView, Calendar date) {
        SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy");
        String dateString = format.format(date.getTime());
        textView.setText(dateString);
    }
}

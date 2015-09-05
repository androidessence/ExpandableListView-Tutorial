package androidessence.expandablelistviewsample;

import android.location.GpsStatus;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView mListView;
    private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        mAdapter = new EventAdapter(this, getDates(), getEventDates());
        mListView.setAdapter(mAdapter);

        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                boolean shouldExpand = !mListView.isGroupExpanded(groupPosition);
                mListView.collapseGroup(groupPosition);

                if (shouldExpand) {
                    mListView.expandGroup(groupPosition);
                    // Scroll to sixth element in group 2
                    if (groupPosition == 1) {
                        // Get # of children in group one
                        int numPreviousElements = mAdapter.getChildrenCount(0);
                        // Count for two group squares
                        numPreviousElements += 2;
                        mListView.smoothScrollToPosition(numPreviousElements + 6);
                    }
                }

                return true;
            }
        });
    }

    private List<LocalDate> getDates(){
        List<LocalDate> dates = new ArrayList<>();

        dates.add(new LocalDate(2015, 9, 1));
        dates.add(new LocalDate(2015, 9, 2));
        dates.add(new LocalDate(2015, 9, 3));

        return dates;
    }

    private HashMap<LocalDate, List<Event>> getEventDates(){
        HashMap<LocalDate, List<Event>> eventDates = new HashMap<>();

        List<Event> firstDay = new ArrayList<>();
        firstDay.add(new Event("One", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        firstDay.add(new Event("Two", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        firstDay.add(new Event("Three", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        firstDay.add(new Event("Four", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        firstDay.add(new Event("Five", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        firstDay.add(new Event("Six", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        firstDay.add(new Event("Seven", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        firstDay.add(new Event("Eight", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        firstDay.add(new Event("Nine", new LocalDateTime(2015, 9, 1, 10, 0, 0)));

        List<Event> secondDay = new ArrayList<>();
        secondDay.add(new Event("One", new LocalDateTime(2015, 9, 2, 10, 0, 0)));
        secondDay.add(new Event("Two", new LocalDateTime(2015, 9, 2, 10, 0, 0)));
        secondDay.add(new Event("Three", new LocalDateTime(2015, 9, 2, 10, 0, 0)));
        secondDay.add(new Event("Four", new LocalDateTime(2015, 9, 2, 10, 0, 0)));
        secondDay.add(new Event("Five", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        secondDay.add(new Event("Six", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        secondDay.add(new Event("Seven", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        secondDay.add(new Event("Eight", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        secondDay.add(new Event("Nine", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        secondDay.add(new Event("Ten", new LocalDateTime(2015, 9, 1, 10, 0, 0)));

        List<Event> thirdDay = new ArrayList<>();
        thirdDay.add(new Event("One", new LocalDateTime(2015, 9, 3, 10, 0, 0)));
        thirdDay.add(new Event("Two", new LocalDateTime(2015, 9, 3, 10, 0, 0)));
        thirdDay.add(new Event("Three", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        thirdDay.add(new Event("Four", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        thirdDay.add(new Event("Five", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        thirdDay.add(new Event("Six", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        thirdDay.add(new Event("Seven", new LocalDateTime(2015, 9, 1, 10, 0, 0)));
        thirdDay.add(new Event("Eight", new LocalDateTime(2015, 9, 1, 10, 0, 0)));

        eventDates.put(new LocalDate(2015, 9, 1), firstDay);
        eventDates.put(new LocalDate(2015, 9, 2), secondDay);
        eventDates.put(new LocalDate(2015, 9, 3), thirdDay);

        return eventDates;
    }
}

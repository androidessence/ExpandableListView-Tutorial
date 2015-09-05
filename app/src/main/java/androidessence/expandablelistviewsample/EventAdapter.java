package androidessence.expandablelistviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by adammcneilly on 9/4/15.
 */
public class EventAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<LocalDate> mDates;
    private HashMap<LocalDate, List<Event>> mEventDates;

    public EventAdapter(Context context, List<LocalDate> dates, HashMap<LocalDate, List<Event>> eventDates){
        this.mContext = context;
        this.mDates = dates;
        this.mEventDates = eventDates;
    }

    @Override
    public int getGroupCount() {
        return mDates.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mEventDates.get(getGroup(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDates.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mEventDates.get(getGroup(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        DateViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_day, parent, false);
            viewHolder = new DateViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (DateViewHolder) convertView.getTag();
        }

        LocalDate date = (LocalDate) getGroup(groupPosition);

        viewHolder.dateView.setText(getDateString(date));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        EventViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_event, parent, false);
            viewHolder = new EventViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (EventViewHolder) convertView.getTag();
        }

        Event event = (Event) getChild(groupPosition, childPosition);

        viewHolder.descriptionView.setText(event.getDescription());
        viewHolder.timeView.setText(event.getTimeString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public static String getDateString(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MMMM dd, yyyy");
        return dtf.print(date);
    }

    private class DateViewHolder{
        public final TextView dateView;

        public DateViewHolder(View view){
            dateView = (TextView) view.findViewById(R.id.date);
        }
    }

    private class EventViewHolder{
        public final TextView descriptionView;
        public final TextView timeView;

        public EventViewHolder(View view){
            descriptionView = (TextView) view.findViewById(R.id.event_description);
            timeView = (TextView) view.findViewById(R.id.event_time);
        }
    }
}

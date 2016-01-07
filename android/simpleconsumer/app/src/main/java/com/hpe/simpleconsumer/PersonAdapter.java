package com.hpe.simpleconsumer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    private final Context mContext;
    private final List<Person> values;

    public PersonAdapter(Context context, List<Person> values) {
        super(context, R.layout.rowlayout, values);
        this.mContext = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    /*
     * Creates the row in the ListView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Generate the row from a layout
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        //Get the data
        Person s = values.get(position);

        //Place the data in the views
        textView.setText(s.getFirstName() + " " + s.getLastName());

        if (s.getGender().equalsIgnoreCase("m")) {
            imageView.setImageResource(R.drawable.male_icon);
        } else {
            imageView.setImageResource(R.drawable.female_icon);
        }

        return rowView;
    }
}

package com.example.autocompletetextviewcustomadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AutocompleteCustomArrayAdapter extends ArrayAdapter<KeyValue> {

	final String TAG = "AutocompleteCustomArrayAdapter.java";

	Context mContext;
	int layoutResourceId;
	KeyValue data[] = null;

	public AutocompleteCustomArrayAdapter(Context mContext,
			int layoutResourceId, KeyValue[] data) {

		super(mContext, layoutResourceId, data);

		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.data = data;
	}

	public void changeData(KeyValue[] data) {
		this.data = data;

		notifyDataSetChanged();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		try {

			if (convertView == null) {
				// inflate the layout
				LayoutInflater inflater = ((MainActivity) mContext)
						.getLayoutInflater();
				convertView = inflater.inflate(layoutResourceId, parent, false);
			}

			// object item based on the position
			KeyValue objectItem = data[position];

			// get the TextView and then set the text (item name) and tag (item
			// ID) values
			TextView textViewItem = (TextView) convertView
					.findViewById(R.id.textViewItem);
			textViewItem.setText(objectItem.getValue());

			// in case you want to add some style, you can do something like:
			textViewItem.setBackgroundColor(Color.CYAN);

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;

	}
}
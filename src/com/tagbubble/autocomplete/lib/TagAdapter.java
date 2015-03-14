package com.tagbubble.autocomplete.lib;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TagAdapter<T extends TagItemDeletedListener> extends BaseAdapter {

	private Context ctx;
	private ArrayList<KeyValue> data;
	TagItemDeletedListener listener;
	KeyValue tempValues = null;
	int i = 0;

	public TagAdapter(Context _ctx, ArrayList<KeyValue> d) {

		ctx = _ctx;
		data = d;
		listener = (T) _ctx;

	}

	public int getCount() {
		if (data != null && !data.isEmpty()) {
			return data.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		return position;
	}

	public void changeData(ArrayList<KeyValue> _data) {
		data = _data;
		notifyDataSetChanged();
	}

	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {

		public TextView tag_name;
		public ImageView bubble_cancel;

	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		if (data.get(position).isShown()) {
			View vi = convertView;
			ViewHolder holder;

			if (convertView == null) {
				LayoutInflater mInflater = (LayoutInflater) ctx
						.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
				vi = mInflater.inflate(R.layout.tag, null);
				holder = new ViewHolder();
				holder.tag_name = (TextView) vi.findViewById(R.id.tag_name);
				holder.bubble_cancel = (ImageView) vi
						.findViewById(R.id.bubble_cancel);

				vi.setTag(holder);
			} else
				holder = (ViewHolder) vi.getTag();

			holder.tag_name.setText(data.get(position).getValue());
			holder.bubble_cancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					listener.onTagItemsDeleted(data.get(position));
				}
			});

			return vi;
		}
		return null;
	}

}

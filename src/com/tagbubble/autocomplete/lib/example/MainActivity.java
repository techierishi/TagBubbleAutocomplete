package com.tagbubble.autocomplete.lib.example;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.tagbubble.autocomplete.lib.ACItemClickListener;
import com.tagbubble.autocomplete.lib.AutocompleteCustomArrayAdapter;
import com.tagbubble.autocomplete.lib.CustomAutoCompleteTextChangedListener;
import com.tagbubble.autocomplete.lib.CustomAutoCompleteView;
import com.tagbubble.autocomplete.lib.KeyValue;
import com.tagbubble.autocomplete.lib.R;
import com.tagbubble.autocomplete.lib.TagAdapter;
import com.tagbubble.autocomplete.lib.TagItemDeletedListener;
import com.tagbubble.autocomplete.lib.WrapLayout;

public class MainActivity extends Activity implements ACItemClickListener,
		TagItemDeletedListener {

	/*
	 * Change to type CustomAutoCompleteView instead of AutoCompleteTextView
	 * since we are extending to customize the view and disable filter The same
	 * with the XML view, type will be CustomAutoCompleteView
	 */
	CustomAutoCompleteView myAutoComplete;
	AutocompleteCustomArrayAdapter<MainActivity> myAdapter;
	CustomAutoCompleteTextChangedListener ccObj;
	ArrayList<KeyValue> kvArr2;
	TagAdapter<MainActivity> tAdapter;

	// adapter for auto-complete

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		WrapLayout main_wrap_layout;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {

			// put sample data to database

			kvArr2 = new ArrayList<KeyValue>();

			kvArr2.add(new KeyValue("0", "Android", false));
			kvArr2.add(new KeyValue("1", "iOs", false));
			kvArr2.add(new KeyValue("2", "Java", false));
			kvArr2.add(new KeyValue("3", "Javascript", false));
			kvArr2.add(new KeyValue("4", "PHP", false));
			kvArr2.add(new KeyValue("5", "Ruby", false));
			kvArr2.add(new KeyValue("6", "Perl", false));
			kvArr2.add(new KeyValue("7", "Python", false));

			tAdapter = new TagAdapter<MainActivity>(this, kvArr2);
			// autocompletetextview is in activity_main.xml
			myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.myautocomplete);

			ccObj = new CustomAutoCompleteTextChangedListener(this, kvArr2);
			myAutoComplete.addTextChangedListener(ccObj);

			myAdapter = new AutocompleteCustomArrayAdapter<MainActivity>(this,
					R.layout.list_view_row_item, kvArr2);
			myAutoComplete.setAdapter(myAdapter);

			main_wrap_layout = (WrapLayout) findViewById(R.id.main_wrap_layout);

			main_wrap_layout.setAdapter(tAdapter);

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onACItemClickListener(KeyValue kv) {
		int position = actualPosition(kvArr2, kv);

		kvArr2.get(position).setShown(true);

		tAdapter.changeData(kvArr2);
		myAutoComplete.setText("");

	}

	@Override
	public void onTagItemsDeleted(KeyValue kv) {
		int position = actualPosition(kvArr2, kv);
		kvArr2.get(position).setShown(false);
		tAdapter.changeData(kvArr2);
		if (ccObj != null) {
			ccObj.changeData(kvArr2);
		}
	}

	private int actualPosition(ArrayList<KeyValue> all_kvs,
			KeyValue searchthis_kv) {
		int i = 0;
		if (all_kvs != null && !all_kvs.isEmpty()) {
			for (KeyValue kv : all_kvs) {
				if (kv.getKey().equals(searchthis_kv.getKey())) {
					return i;
				}

				i++;
			}
		}
		return -1;

	}
}

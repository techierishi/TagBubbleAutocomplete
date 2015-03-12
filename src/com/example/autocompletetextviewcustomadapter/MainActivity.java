package com.example.autocompletetextviewcustomadapter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	/*
	 * Change to type CustomAutoCompleteView instead of AutoCompleteTextView
	 * since we are extending to customize the view and disable filter The same
	 * with the XML view, type will be CustomAutoCompleteView
	 */
	CustomAutoCompleteView myAutoComplete;
	AutocompleteCustomArrayAdapter myAdapter;
	ArrayList<KeyValue> kvArr2;
	TagAdapter tAdapter;

	// adapter for auto-complete

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		WrapLayout main_wrap_layout;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {

			// put sample data to database

			kvArr2 = new ArrayList<KeyValue>();
			tAdapter = new TagAdapter(this, kvArr2);
			// autocompletetextview is in activity_main.xml
			myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.myautocomplete);

			myAutoComplete.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View arg1,
						int pos, long id) {

					RelativeLayout rl = (RelativeLayout) arg1;
					TextView tv = (TextView) rl.getChildAt(0);

					kvArr2.add(new KeyValue("0", "" + tv.getText().toString()));

					tAdapter.changeData(kvArr2);
					myAutoComplete.setText(tv.getText().toString());

				}

			});

			KeyValue kvArr[] = new KeyValue[5];

			kvArr[0] = new KeyValue("0", "Android");
			kvArr[1] = new KeyValue("0", "iOs");
			kvArr[2] = new KeyValue("0", "Java");
			kvArr[3] = new KeyValue("0", "Javascript");
			kvArr[4] = new KeyValue("0", "Andy");

			myAutoComplete
					.addTextChangedListener(new CustomAutoCompleteTextChangedListener(
							this, kvArr));

			myAdapter = new AutocompleteCustomArrayAdapter(this,
					R.layout.list_view_row_item, kvArr);
			myAutoComplete.setAdapter(myAdapter);

			main_wrap_layout = (WrapLayout) findViewById(R.id.main_wrap_layout);

			main_wrap_layout.setAdapter(tAdapter);

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

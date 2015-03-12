package com.example.autocompletetextviewcustomadapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

public class CustomAutoCompleteTextChangedListener implements TextWatcher {

	public static final String TAG = "CustomAutoCompleteTextChangedListener.java";
	Context context;
	KeyValue[] myObjs;

	public CustomAutoCompleteTextChangedListener(Context context,
			KeyValue[] myObjs) {
		this.context = context;
		this.myObjs = myObjs;
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence userInput, int start, int before,
			int count) {

		try {

			// if you want to see in the logcat what the user types
			Log.e(TAG, "User input: " + userInput);

			MainActivity mainActivity = ((MainActivity) context);

			// get suggestions from the database
			KeyValue[] myObjs = filterKeyVal(this.myObjs, userInput.toString());

			if (myObjs != null)
				mainActivity.myAdapter.changeData(myObjs);

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public KeyValue[] filterKeyVal(KeyValue[] kvs, String search) {

		int j = 0;
		if (kvs != null) {
			KeyValue kvs_l[] = new KeyValue[kvs.length];

			for (int i = 0; i <= kvs.length; i++) {
				if (kvs[i].getValue().trim().contains(search)) {
					kvs_l[j] = kvs[i];

					j++;
				}
			}

			return kvs_l;

		}

		return null;
	}

}

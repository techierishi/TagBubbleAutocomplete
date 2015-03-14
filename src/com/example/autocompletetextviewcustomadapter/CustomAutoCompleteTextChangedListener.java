package com.example.autocompletetextviewcustomadapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

public class CustomAutoCompleteTextChangedListener implements TextWatcher {

	public static final String TAG = "CustomAutoCompleteTextChangedListener.java";
	Context context;
	ArrayList<KeyValue> myObjs;

	public CustomAutoCompleteTextChangedListener(Context context,
			ArrayList<KeyValue> myObjs) {
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
			ArrayList<KeyValue> myObjs = filterKeyVal(this.myObjs,
					userInput.toString());

			if (myObjs != null)
				mainActivity.myAdapter.changeData(myObjs);

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<KeyValue> filterKeyVal(ArrayList<KeyValue> kvs,
			String search) {
		ArrayList<KeyValue> kvs_l = new ArrayList<KeyValue>();

		if (kvs != null) {

			for (KeyValue kvl : kvs) {
				if (kvl.getValue().trim().toLowerCase()
						.contains(search.toLowerCase())
						&& !kvl.isShown()) {
					kvs_l.add(kvl);

				}
			}

		}

		return kvs_l;
	}

}

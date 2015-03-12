package com.example.autocompletetextviewcustomadapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class WrapLayout extends ViewGroup {

	BaseAdapter aAdapter;

	public WrapLayout(Context context) {
		super(context);
		init(context);
	}

	public WrapLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public WrapLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		// TODO Auto-generated method stub
		final int count = getChildCount();
		int curWidth, curHeight, curLeft, curTop, maxHeight;

		// get the available size of child view
		int childLeft = this.getPaddingLeft();
		int childTop = this.getPaddingTop();
		int childRight = this.getMeasuredWidth() - this.getPaddingRight();
		int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
		int childWidth = childRight - childLeft;
		int childHeight = childBottom - childTop;

		maxHeight = 0;
		curLeft = childLeft;
		curTop = childTop;
		// walk through each child, and arrange it from left to right
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() != GONE) {
				// Get the maximum size of the child
				child.measure(MeasureSpec.makeMeasureSpec(childWidth,
						MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(
						childHeight, MeasureSpec.AT_MOST));
				curWidth = child.getMeasuredWidth();
				curHeight = child.getMeasuredHeight();
				// wrap is reach to the end
				if (curLeft + curWidth >= childRight) {
					curLeft = childLeft;
					curTop += maxHeight;
					maxHeight = 0;
				}
				// do the layout
				child.layout(curLeft, curTop, curLeft + curWidth, curTop
						+ curHeight);
				// store the max height
				if (maxHeight < curHeight)
					maxHeight = curHeight;
				curLeft += curWidth;
			}
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		final int count = getChildCount();
		int curWidth, curHeight, curLeft, curTop, maxHeight;

		// get the available size of child view
		int childLeft = this.getPaddingLeft();
		int childTop = this.getPaddingTop();
		int childRight = this.getMeasuredWidth() - this.getPaddingRight();
		int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
		int childWidth = childRight - childLeft;
		int childHeight = childBottom - childTop;

		maxHeight = 0;
		curLeft = childLeft;
		curTop = childTop;
		// walk through each child, and arrange it from left to right
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() != GONE) {
				// Get the maximum size of the child
				child.measure(MeasureSpec.makeMeasureSpec(childWidth,
						MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(
						childHeight, MeasureSpec.AT_MOST));
				curWidth = child.getMeasuredWidth();
				curHeight = child.getMeasuredHeight();
				// wrap is reach to the end
				if (curLeft + curWidth >= childRight) {
					curLeft = childLeft;
					curTop += maxHeight;
					maxHeight = 0;
				}
				// do the layout
				child.layout(curLeft, curTop, curLeft + curWidth, curTop
						+ curHeight);
				// store the max height
				if (maxHeight < curHeight)
					maxHeight = curHeight;
				curLeft += curWidth;
			}
		}
	}

	private DataSetObserver dataSetObserver = new DataSetObserver() {
		@Override
		public void onChanged() {
			super.onChanged();
			reloadChildViews();
		}
	};

	public void setAdapter(BaseAdapter adapter) {

		// aAdapter = adapter;
		// if (this.aAdapter == adapter)
		// return;
		this.aAdapter = adapter;
		if (adapter != null) {
			adapter.registerDataSetObserver(dataSetObserver);
		}
		reloadChildViews();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (aAdapter != null)
			aAdapter.unregisterDataSetObserver(dataSetObserver);
	}

	private void reloadChildViews() {
		removeAllViews();

		if (aAdapter == null)
			return;

		int count = aAdapter.getCount();
		for (int position = 0; position < count; ++position) {
			View v = aAdapter.getView(position, null, this);
			if (v != null)
				addView(v);
		}

		requestLayout();
	}

	public interface OnItemClickListener {
		public boolean onItemClick(int position);
	}
}
package com.example.jisuanqi;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class History extends ListActivity {

	ArrayList<Event> list;
	EventAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		list = new ArrayList<Event>();

		list.add(new Event("sad", "fdsa", 10, 28));
		list.add(new Event("sad", "fdsa", 10, 28));
		list.add(new Event("sad", "fdsa", 10, 28));
		list.add(new Event("sad", "fdsa", 10, 28));
		list.add(new Event("sad", "fdsa", 10, 28));
		list.add(new Event("sad", "fdsa", 10, 28));
		list.add(new Event("sad", "fdsa", 10, 28));

		adapter = new EventAdapter(this, R.layout.item_history, list);

		setListAdapter(adapter);
	}

	public class EventAdapter extends ArrayAdapter<Event> {

		private LayoutInflater mInflater;

		public EventAdapter(Context context, int resource, List<Event> list) {
			super(context, R.layout.item_history, list);
			mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_history, parent,
						false);
				holder = new ViewHolder();
				holder.mTvName = (TextView) convertView
						.findViewById(R.id.tv_name);
				holder.mTvTime = (TextView) convertView
						.findViewById(R.id.tv_time);
				holder.mTvA = (TextView) convertView.findViewById(R.id.tv_A);
				holder.mTvB = (TextView) convertView.findViewById(R.id.tv_B);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			Event event = getItem(position);
			holder.mTvName.setText(event.name);
			holder.mTvTime.setText(event.time);
			holder.mTvA.setText(String.valueOf(event.A));
			holder.mTvB.setText(String.valueOf(event.B));

			return convertView;
		}

	}

	public class ViewHolder {
		TextView mTvName;
		TextView mTvTime;
		TextView mTvA;
		TextView mTvB;
	}

}

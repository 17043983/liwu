package com.example.jisuanqi;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class History extends ListActivity {

	ArrayList<Event> list;
	EventAdapter adapter;
	Cursor cursor;
	SQLite mMyDB;
	Event event;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		list = new ArrayList<Event>();
		
		mMyDB=new SQLite(this, "activity.db", 1);
		
		cursor=mMyDB.getWritableDatabase().rawQuery("select * from event", null);
		while(cursor.moveToNext()){
			String name=cursor.getString(1);
			String time=cursor.getString(2);
			String A=cursor.getString(3);
			String B=cursor.getString(4);
			
			event=new Event(name, time, A, B);
			
			list.add(event);
		}
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

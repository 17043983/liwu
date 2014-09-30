package com.example.jisuanqi;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private SQLite mMyDB;
	private TextView mTvname, mTvnum, mTvHQnum, mTvLBnum;
	private Button mBtnHQ, mBtnLB;
	private int mHQnum, mLBnum, mNum;
	private String mName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mMyDB = new SQLite(MainActivity.this, "activity.db", 1);

		initView();

//		if (savedInstanceState != null) {
//			savedInstanceState.getString("msg");
//			mTvLBnum.setText(savedInstanceState.getString("msg"));
//			Toast.makeText(this, savedInstanceState.getString("msg"),
//					Toast.LENGTH_SHORT).show();
//		}

		mHQnum = 0;
		mLBnum = 0;
		mNum = 0;
	}

	private void initView() {
		mTvname = (TextView) findViewById(R.id.tv_name);
		mTvnum = (TextView) findViewById(R.id.tv_num);
		mTvHQnum = (TextView) findViewById(R.id.tv_huanquan_num);
		mTvLBnum = (TextView) findViewById(R.id.tv_linba_num);

		mBtnHQ = (Button) findViewById(R.id.btn_huanquan);
		mBtnLB = (Button) findViewById(R.id.btn_linba);

		mBtnHQ.setOnClickListener(this);
		mBtnLB.setOnClickListener(this);
		mTvname.setOnClickListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("mTvname", mTvname.getText().toString());
		
		outState.putString("mTvnum", mTvnum.getText().toString());
		outState.putString("mTvHQnum", mTvHQnum.getText().toString());
		outState.putString("mTvLBnum", mTvLBnum.getText().toString());

	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mName=savedInstanceState.getString("mTvname");
		
		mNum = Integer.parseInt(savedInstanceState.getString("mTvnum"));
		mHQnum = Integer.parseInt(savedInstanceState.getString("mTvHQnum"));
		mLBnum = Integer.parseInt(savedInstanceState.getString("mTvLBnum"));
		
		mTvnum.setText(mNum+"");
		mTvHQnum.setText(mHQnum+"");
		mTvLBnum.setText(mLBnum+"");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			Toast.makeText(this, "猪之歌", Toast.LENGTH_SHORT).show();
			break;

		case R.id.action_history:
			startActivity(new Intent(this, History.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_huanquan:
			mHQnum += 1;
			mTvHQnum.setText(String.valueOf(mHQnum));

			mNum += 1;
			mTvnum.setText(String.valueOf(mNum));
			break;

		case R.id.btn_linba:
			mLBnum += 1;
			mTvLBnum.setText(String.valueOf(mLBnum));

			mNum += 1;
			mTvnum.setText(String.valueOf(mNum));
			break;

		case R.id.tv_name:
			Toast.makeText(this, "你要重命名吗？", Toast.LENGTH_SHORT).show();
			break;
		}

		if (mNum == 10) {
			mMyDB.getWritableDatabase().execSQL(
					"insert into event values(null,?,?,?,?)",
					new String[] { mTvname.getText().toString(), getTime(),
							mHQnum + "", mLBnum + "" });
			mBtnHQ.setEnabled(false);
			mBtnLB.setEnabled(false);
			mNum++;
		}
	}

	private String getTime() {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.getDefault());
		return sdf.format(date);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mMyDB != null) {
			mMyDB.close();
		}
	}
}

package com.example.cgli.mygamegrouptest.block;

import com.example.cgli.mygamegrouptest.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class BlockActivity extends Activity {
	private String TAG = BlockActivity.class.getSimpleName();
	private Context mContext = BlockActivity.this;
	private BlockGameView gameView;
	private Toast mToast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "create activity");
		LinearLayout linearLayout = new LinearLayout(mContext);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		gameView = new BlockGameView(mContext);
		linearLayout.addView(gameView);

		View block_control = LayoutInflater.from(mContext).inflate(R.layout.block_control, linearLayout);
		block_control.findViewById(R.id.btn_left).setOnClickListener(onClickListener);
		block_control.findViewById(R.id.btn_turn).setOnClickListener(onClickListener);
		block_control.findViewById(R.id.btn_right).setOnClickListener(onClickListener);
		block_control.findViewById(R.id.btn_down).setOnClickListener(onClickListener);
		setContentView(linearLayout);
	}

	OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btn_turn:
					gameView.turn();
					break;
				case R.id.btn_left:
					gameView.moveLeft();
					break;
				case R.id.btn_down:
					/*to decrease difficulat, not move down by now*/
					break;
				case R.id.btn_right:
					gameView.moveRight();
					break;
			}
		}
	};

	@Override
	protected void onPause() {
		super.onPause();
		//Log.d(TAG,"onPause");
		BlockGameView.stopThread();
		int mScore = BlockGameView.getFinalScore();
		String gameOverMsg = "Hi Chris, your final score is " + mScore;
		showToast(gameOverMsg);
	}

	private void showToast(String msg) {
		/*if (mToast == null) {
			mToast = Toast.makeText(mContext, "", Toast.LENGTH_LONG);
		}
		mToast.setText(msg);
		mToast.show();*/
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.block_custom_toast,
				(ViewGroup) findViewById(R.id.toast_layout_root));

		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText(msg);

		Toast toast = new Toast(mContext);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();

	}
}

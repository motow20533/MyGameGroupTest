package com.example.cgli.mygamegrouptest;

import com.example.cgli.mygamegrouptest.bird.BirdActivity;
import com.example.cgli.mygamegrouptest.block.BlockActivity;
import com.example.cgli.mygamegrouptest.snake.Snake;
import com.example.cgli.mygamegrouptest.fish.FishActivity;
import com.example.cgli.mygamegrouptest.five.FiveActivity;
import com.example.cgli.mygamegrouptest.link.LianActivity;
import com.example.cgli.mygamegrouptest.jpong.JPongActivity;
import com.example.cgli.mygamegrouptest.popstar.PopStarActivity;
import com.example.cgli.mygamegrouptest.puzzle.PuzzleActivity;
import com.example.cgli.mygamegrouptest.tank.TankActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Context mContext = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn_bird;
		Button btn_fish;
		Button btn_block;
		Button btn_lian;
		Button btn_tank;
		Button btn_puzzle;
		Button btn_five;
		Button btn_snake;
		Button btn_popstar;
		Button btn_jpong;

		// jpong
		btn_jpong = (Button) findViewById(R.id.btn_jpong);
		btn_jpong.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, JPongActivity.class));
			}
		});

		// popStar
		btn_popstar = (Button) findViewById(R.id.btn_popstar);
		btn_popstar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, PopStarActivity.class));
			}
		});

		// Snake
		btn_snake = (Button) findViewById(R.id.btn_snake);
		btn_snake.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, Snake.class));
			}
		});

		// FlappyBird
		btn_bird = (Button) findViewById(R.id.btn_bird);
		btn_bird.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, BirdActivity.class));
			}
		});
		// 大鱼吃小鱼
		btn_fish = (Button) findViewById(R.id.btn_fish);
		btn_fish.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, FishActivity.class));
			}
		});
		// 俄罗斯方块
		btn_block = (Button) findViewById(R.id.btn_block);
		btn_block.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, BlockActivity.class));
			}
		});
		// 连连看游戏
		btn_lian = (Button) findViewById(R.id.btn_lian);
		btn_lian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, LianActivity.class));
			}
		});
		// 坦克大战
		btn_tank = (Button) findViewById(R.id.btn_tank);
		btn_tank.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, TankActivity.class));
			}
		});
		// 拼图
		btn_puzzle = (Button) findViewById(R.id.btn_puzzle);
		btn_puzzle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, PuzzleActivity.class));
			}
		});
		// 五子棋
		btn_five = (Button) findViewById(R.id.btn_five);
		btn_five.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, FiveActivity.class));
			}
		});
	}
}

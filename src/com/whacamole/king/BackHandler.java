package com.whacamole.king;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/*
 * date:5th.June
 * author:king
 */
public class BackHandler extends Handler {
	BackHole backhole;

	public BackHandler(Looper looper) {
		super(looper);
	}

	@Override
	public void handleMessage(Message msg) {
		int index = msg.arg1;
		int hit = msg.arg2;
		if (msg.what == 0) { // 是否为第一次执行
			backhole = new BackHole(index, hit);
			Thread t = new Thread(backhole);
			t.start();
		} else {
			if (hit == 1) {
				State.score++;
				State.mainactivity.score.setText("score:" + State.score);
			}
			int id = State.mainactivity.btn[index].getId();
			// AnimationDrawable
			// drawable=(AnimationDrawable)findViewById(id).getBackground();
			// Drawable draw = State.mainactivity.imgMouse2.getBackground();
			State.mainactivity.btn[index]
					.setBackgroundResource(R.drawable.mouse_2);
			AnimationDrawable drawable = (AnimationDrawable) State.mainactivity.btn[index]
					.getBackground();
			drawable.start();
			// State.mainactivity.btn[index].setImageResource(R.drawable.dong_2);
			State.status[index] = 0;
		}
	}
}

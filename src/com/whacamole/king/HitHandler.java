package com.whacamole.king;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/*
 * date:5th.June
 * author:king
 */
public class HitHandler extends Handler {

	public HitHandler(Looper looper) {
		super(looper);
	}

	@Override
	public void handleMessage(Message msg) {
		Message backMsg = State.mainactivity.backhandler.obtainMessage();
		int index = msg.arg1;
		backMsg.arg1 = index;
		backMsg.arg2 = 1;
		backMsg.what = 0; // 第一次调用
		State.mainactivity.btn[index].setBackgroundResource(R.drawable.mouse_3);
		AnimationDrawable drawable = (AnimationDrawable) State.mainactivity.btn[index]
				.getBackground();
		drawable.start();
		// State.mainactivity.btn[index].setImageResource(R.drawable.dong_2);
		State.mainactivity.backhandler.sendMessage(backMsg);
	}

}

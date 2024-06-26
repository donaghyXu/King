package com.whacamole.king;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/*
 * date:5th.June
 * author:king
 */
public class ReceiveMessageHandler extends Handler {
	public ReceiveMessageHandler(Looper looper) {
		super(looper);
	}

	@Override
	public void handleMessage(Message msg) {
		Message back_msg = State.mainactivity.backhandler.obtainMessage();
		int index = msg.arg1;
		back_msg.arg1 = index;
		back_msg.arg2 = 0;
		back_msg.what = 0;
		if (State.status[index] == 0) {
			State.mainactivity.btn[index]
					.setBackgroundResource(R.drawable.mouse_1);
			AnimationDrawable drawable = (AnimationDrawable) State.mainactivity.btn[index]
					.getBackground();
			drawable.start();
			// State.mainactivity.btn[index].setImageResource(R.drawable.show6);
			State.status[index] = 1;
			State.mainactivity.backhandler.sendMessage(back_msg);
		}
		super.handleMessage(msg);
	}

}

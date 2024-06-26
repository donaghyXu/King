package com.whacamole.king;

import android.os.Message;

/*
 * date:5th.June
 * author:king
 */
public class BackHole implements Runnable {

	int index;
	int hit;

	public BackHole(int index, int hit) {
		this.index = index;
		this.hit = hit;
	}

	@Override
	public void run() {
		Message msg = State.mainactivity.backhandler.obtainMessage();
		try {
			if (hit == 1) {
				Thread.sleep(State.dizzy_time);
			} else {
				Thread.sleep(State.stay_time);
			}
			msg.arg1 = index;
			msg.arg2 = hit;
			msg.what = 1; // 第二次访问为1，第一次访问为0
			State.mainactivity.backhandler.sendMessage(msg); // 发送信息
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

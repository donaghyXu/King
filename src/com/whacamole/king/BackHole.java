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
			msg.what = 1; // �ڶ��η���Ϊ1����һ�η���Ϊ0
			State.mainactivity.backhandler.sendMessage(msg); // ������Ϣ
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

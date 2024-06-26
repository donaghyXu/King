package com.whacamole.king;

import java.util.Random;

import android.os.Message;

/*
 * date:5th.June
 * author:king
 */
public class AppearSusliks implements Runnable {

	Random ran;

	public void run() {
		ran = new Random();
		while (State.if_burn_mouse) {
			int num;
			Message msg1 = State.mainactivity.rmhandler.obtainMessage();
			num = ran.nextInt(9);
			msg1.arg1 = num;
			State.mainactivity.rmhandler.sendMessage(msg1);
			try {
				Thread.sleep(State.burn_time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

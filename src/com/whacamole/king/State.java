package com.whacamole.king;

/*
 * date:5th.June
 * author:king
 */
public class State {
	static int status[] = new int[9]; // 9个鼠洞的状态
	static int stay_time = 2000; // 停留时间
	static int burn_time = 500; // 产生地鼠的时间
	static int dizzy_time = 500; // 打晕时间
	static int score = 0; // 得分
	static boolean if_burn_mouse = true; // 是否产生地鼠

	static MainActivity mainactivity = new MainActivity();
	static OtherActivity otheractivity = new OtherActivity();
	static AppearSusliks appearsusliks;

	State(MainActivity mainActivity) {
		mainactivity = mainActivity;
	}

	State(AppearSusliks appearSusliks) {
		appearsusliks = appearSusliks;
	}

	State(OtherActivity otherActivity) {
		otheractivity = otherActivity;
	}
}

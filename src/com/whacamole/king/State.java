package com.whacamole.king;

/*
 * date:5th.June
 * author:king
 */
public class State {
	static int status[] = new int[9]; // 9���󶴵�״̬
	static int stay_time = 2000; // ͣ��ʱ��
	static int burn_time = 500; // ���������ʱ��
	static int dizzy_time = 500; // ����ʱ��
	static int score = 0; // �÷�
	static boolean if_burn_mouse = true; // �Ƿ��������

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

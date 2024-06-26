package com.whacamole.king;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/*
 * date:5th.June
 * author:king
 */
public class MainActivity extends Activity {

	public ImageButton btn[]; // ����ť
	public State state; // ״̬
	public HitHandler hithandler; // �������
	public ReceiveMessageHandler rmhandler; // ������Ϣ����
	public BackHandler backhandler; // ���ش���
	public AppearSusliks appearSusliks; // �������
	public TextView score; // �÷�
	public Message msg; // ��Ϣ
	public Button btnStart;// ��ʼ��ť
	public Button btnExit;// �˳���ť
	public Button btnBack; // ���ذ�ť
	public Button btnHelpBack;
	public TextView tvShow;// ��ʾ����ʱ
	public TextView tvLevel;// ��ʾ������
	public MyCount mc, mc2;// ����ʱ��
	Thread t;// ���߳�
	int levelNum = 1; // ��־�����Ĺ���

	 private Intent intent = new Intent("com.angel.Android.MUSIC");
	// public ImageButton imgMouse1,imgMouse2,imgMouse3;
	// Thread makeThread;
	// Thread countThread;

	class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			if (state.score >= 40 && state.otheractivity.num == 2) {
				new AlertDialog.Builder(MainActivity.this).setTitle("Win")
						.setMessage("��ϲ���ʤ����").setPositiveButton("ȷ��", null)
						.show();
			} else if (state.otheractivity.num == 2) {
				new AlertDialog.Builder(MainActivity.this).setTitle("Lose")
						.setMessage("û�򹻣�����Ŭ��Ӵ")
						.setPositiveButton("���������", null).show();
			}
			if (state.score >= 40 && state.otheractivity.num == 1) {
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("��ϲ����")
						.setMessage("���ߣ���������")
						.setPositiveButton("��",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										levelNum++;
										if (levelNum == 2) {
											State.burn_time = 300;
											tvLevel.setText("�ڶ���");
										}
										if (levelNum == 3) {
											State.stay_time = 1000;
											tvLevel.setText("������");
										}

									}

								}).setNegativeButton("��", null).show();
			} else if (state.otheractivity.num == 1) {
				new AlertDialog.Builder(MainActivity.this).setTitle("Lose")
						.setMessage("����ʧ��").setPositiveButton("������ս", null)
						.setNegativeButton("����", null).show();
			}
			// Toast.makeText(getApplicationContext(), "��Ӌ�r��",
			// Toast.LENGTH_SHORT)
			// .show();
			state.if_burn_mouse = false;
		}

		@Override
		public void onTick(long millisUntilFinished) {
			if (state.otheractivity.num == 2) {
				tvShow.setText("����ʱ:" + millisUntilFinished / 1000);
			}

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startService(intent);

		Log.i("result", "num" + state.otheractivity.num);

		if (state.otheractivity.num == 1) {
			setContentView(R.layout.level);
		} else if (state.otheractivity.num == 2) {
			setContentView(R.layout.jishi);
		} else {
			setContentView(R.layout.activity_main);
		}

		tvShow = (TextView) findViewById(R.id.tvTime);
		tvLevel = (TextView) findViewById(R.id.tvLevel);
		rmhandler = new ReceiveMessageHandler(getMainLooper());
		hithandler = new HitHandler(getMainLooper());
		backhandler = new BackHandler(getMainLooper());
		state = new State(MainActivity.this);
		score = (TextView) findViewById(R.id.score);
		score.setText("Score: " + State.score);
		btn = new ImageButton[9];
		btn[0] = (ImageButton) findViewById(R.id.imgBtn1);
		btn[1] = (ImageButton) findViewById(R.id.imgBtn2);
		btn[2] = (ImageButton) findViewById(R.id.imgBtn3);
		btn[3] = (ImageButton) findViewById(R.id.imgBtn4);
		btn[4] = (ImageButton) findViewById(R.id.imgBtn5);
		btn[5] = (ImageButton) findViewById(R.id.imgBtn6);
		btn[6] = (ImageButton) findViewById(R.id.imgBtn7);
		btn[7] = (ImageButton) findViewById(R.id.imgBtn8);
		btn[8] = (ImageButton) findViewById(R.id.imgBtn9);
		// imgMouse1 = (ImageButton) findViewById(R.id.imgMouse1);
		// imgMouse2 = (ImageButton) findViewById(R.id.imgMouse2);
		// imgMouse3 = (ImageButton) findViewById(R.id.imgMouse3);

		// ������ť��Ӽ���
		for (int i = 0; i < 9; i++) {
			btn[i].setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					msg = State.mainactivity.hithandler.obtainMessage();
					int state = v.getId();
					switch (state) {
					case R.id.imgBtn1:
						if (State.status[0] == 1) {
							msg.arg1 = 0;
							State.mainactivity.hithandler.sendMessage(msg);
						}
						break;
					case R.id.imgBtn2:
						if (State.status[1] == 1) {
							msg.arg1 = 1;
							State.mainactivity.hithandler.sendMessage(msg);
						}
						break;
					case R.id.imgBtn3:
						if (State.status[2] == 1) {
							msg.arg1 = 2;
							State.mainactivity.hithandler.sendMessage(msg);
						}
						break;
					case R.id.imgBtn4:
						if (State.status[3] == 1) {
							msg.arg1 = 3;
							State.mainactivity.hithandler.sendMessage(msg);
						}
						break;
					case R.id.imgBtn5:
						if (State.status[4] == 1) {
							msg.arg1 = 4;
							State.mainactivity.hithandler.sendMessage(msg);
						}
						break;
					case R.id.imgBtn6:
						if (State.status[5] == 1) {
							msg.arg1 = 5;
							State.mainactivity.hithandler.sendMessage(msg);
						}
						break;
					case R.id.imgBtn7:
						if (State.status[6] == 1) {
							msg.arg1 = 6;
							State.mainactivity.hithandler.sendMessage(msg);
						}
						break;
					case R.id.imgBtn8:
						if (State.status[7] == 1) {
							msg.arg1 = 7;
							State.mainactivity.hithandler.sendMessage(msg);
						}
						break;
					case R.id.imgBtn9:
						if (State.status[8] == 1) {
							msg.arg1 = 8;
							State.mainactivity.hithandler.sendMessage(msg);
						}
						break;
					}
				}
			});
		}

		// ��ʼ��ť�����¼�
		btnStart = (Button) findViewById(R.id.btnStart);

		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AppearSusliks appearSusliks = new AppearSusliks(); // ��������
				State.if_burn_mouse = true;
				state.score = 0;
				score.setText("score:" + state.score);
				// state.passTime = 0;
				t = new Thread(appearSusliks);
				t.start();
				if (state.otheractivity.num == 2) {
					mc = new MyCount(30000, 1000);
					mc.start();
				}
				if (state.otheractivity.num == 1) {
					mc2 = new MyCount(30000, 1000);
					mc2.start();
				}

				// AnimationDrawable
				// drawable=(AnimationDrawable)findViewById(R.id.imgBtn1).getBackground();
				// drawable.start();
				// AnimationDrawable
				// drawable=(AnimationDrawable)findViewById(R.id.imgBtn1).getBackground();
				// drawable.start();
			}
		});

		// �˳���ť�����¼�
		btnExit = (Button) findViewById(R.id.btnExit);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stopService(intent);

				int sdk_Version = android.os.Build.VERSION.SDK_INT;
				if (sdk_Version >= 8) {
					Intent startMain = new Intent(Intent.ACTION_MAIN);
					startMain.addCategory(Intent.CATEGORY_HOME);
					startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(startMain);
					System.exit(0);
				} else if (sdk_Version < 8) {
					ActivityManager activityMgr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
					activityMgr.restartPackage(getPackageName());
				}

			}
		});

		// ���ذ�ť�����¼�
		btnBack = (Button) findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						OtherActivity.class);
				startActivity(intent);
				finish();
				state.if_burn_mouse = false;

			}
		});

	}

	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) { // ��ʾѡ��˵�
	// menu.add(0, start, 0, "��ʼ");
	// menu.add(0, exit, 1, "�˳�");
	// return super.onCreateOptionsMenu(menu);
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) { // ѡ��˵��ļ����¼�
	// int id = item.getItemId();
	// if (id == start) {
	// AppearSusliks appearSusliks = new AppearSusliks(); // ��������
	// // makeThread = new Thread(appearSusliks);
	// State.if_burn_mouse = true;
	// state.score = 0;
	// score.setText("score:" + state.score);
	// state.passTime = 0;
	// Thread t = new Thread(appearSusliks);
	// t.start();
	// // TimeCounter timeCounter = new TimeCounter();
	// // countThread = new Thread(timeCounter);
	// // makeThread.start();
	// // countThread.start();
	// }
	// if (id == exit) { // �˳�
	// finish();
	// }
	// return super.onOptionsItemSelected(item);
	// }
}

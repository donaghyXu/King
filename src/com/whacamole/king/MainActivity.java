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

	public ImageButton btn[]; // 地鼠按钮
	public State state; // 状态
	public HitHandler hithandler; // 击打地鼠
	public ReceiveMessageHandler rmhandler; // 地鼠消息接收
	public BackHandler backhandler; // 返回处理
	public AppearSusliks appearSusliks; // 地鼠出现
	public TextView score; // 得分
	public Message msg; // 消息
	public Button btnStart;// 开始按钮
	public Button btnExit;// 退出按钮
	public Button btnBack; // 返回按钮
	public Button btnHelpBack;
	public TextView tvShow;// 显示倒计时
	public TextView tvLevel;// 显示闯关数
	public MyCount mc, mc2;// 倒计时类
	Thread t;// 主线程
	int levelNum = 1; // 标志经过的关数

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
						.setMessage("恭喜你获胜啦！").setPositiveButton("确定", null)
						.show();
			} else if (state.otheractivity.num == 2) {
				new AlertDialog.Builder(MainActivity.this).setTitle("Lose")
						.setMessage("没打够，还需努力哟")
						.setPositiveButton("继续打地鼠", null).show();
			}
			if (state.score >= 40 && state.otheractivity.num == 1) {
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("恭喜过关")
						.setMessage("勇者，继续闯关")
						.setPositiveButton("是",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										levelNum++;
										if (levelNum == 2) {
											State.burn_time = 300;
											tvLevel.setText("第二关");
										}
										if (levelNum == 3) {
											State.stay_time = 1000;
											tvLevel.setText("第三关");
										}

									}

								}).setNegativeButton("否", null).show();
			} else if (state.otheractivity.num == 1) {
				new AlertDialog.Builder(MainActivity.this).setTitle("Lose")
						.setMessage("闯关失败").setPositiveButton("继续挑战", null)
						.setNegativeButton("返回", null).show();
			}
			// Toast.makeText(getApplicationContext(), "倒計時完",
			// Toast.LENGTH_SHORT)
			// .show();
			state.if_burn_mouse = false;
		}

		@Override
		public void onTick(long millisUntilFinished) {
			if (state.otheractivity.num == 2) {
				tvShow.setText("倒计时:" + millisUntilFinished / 1000);
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

		// 给地鼠按钮添加监听
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

		// 开始按钮监听事件
		btnStart = (Button) findViewById(R.id.btnStart);

		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AppearSusliks appearSusliks = new AppearSusliks(); // 产生地鼠
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

		// 退出按钮监听事件
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

		// 返回按钮监听事件
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
	// public boolean onCreateOptionsMenu(Menu menu) { // 显示选项菜单
	// menu.add(0, start, 0, "开始");
	// menu.add(0, exit, 1, "退出");
	// return super.onCreateOptionsMenu(menu);
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) { // 选项菜单的监听事件
	// int id = item.getItemId();
	// if (id == start) {
	// AppearSusliks appearSusliks = new AppearSusliks(); // 产生地鼠
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
	// if (id == exit) { // 退出
	// finish();
	// }
	// return super.onOptionsItemSelected(item);
	// }
}

package com.whacamole.king;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OtherActivity extends Activity {

	public Button btn1, btn2, btn3, btn4;
	public static int num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);

		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button)findViewById(R.id.btn4);
		// �ؿ�ģʽ
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				num = 1;
				Intent intent = new Intent(OtherActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		// ��ʱģʽ
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				num = 2;
				Intent intent = new Intent(OtherActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		// �޾�ģʽ
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				num = 3;
				Intent intent = new Intent(OtherActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();

			}
		});

		// ��Ϸ˵��
		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OtherActivity.this,
						HelpActivity.class);
				startActivity(intent);
				finish();

			}
		});
	}

}

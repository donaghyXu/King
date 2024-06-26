package com.whacamole.king;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelpActivity extends Activity {
	public Button btnHelpBack;// 帮助界面返回按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		// 帮助返回按钮监听事件
		btnHelpBack = (Button) findViewById(R.id.btnHelpBack);
		btnHelpBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HelpActivity.this,
						OtherActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}

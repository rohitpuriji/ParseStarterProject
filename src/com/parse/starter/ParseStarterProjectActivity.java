package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	
	private Button loginbutton;
	private Button signup;
	private Button logout;

	private String usernametxt;
	private String passwordtxt;
	private EditText password;
	private EditText username;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpenedInBackground(getIntent());
		
//		ParseObject test = new ParseObject("TestObject");
//		test.put("foo", "bar");
//		test.saveInBackground();
		
		username = (EditText) findViewById(R.id.etName);
		password = (EditText) findViewById(R.id.etpswd);
		loginbutton = (Button)findViewById(R.id.btnLogin);
		signup = (Button)findViewById(R.id.btnSignUp);
		logout = (Button)findViewById(R.id.btnLogout);

//		if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
//			Toast.makeText(getApplicationContext(), "Anonymous user", 40).show();
//		} else {
//			ParseUser user = ParseUser.getCurrentUser();
//			if (user != null) {
//				Toast.makeText(getApplicationContext(), "Welcome user, you are valid", 40).show();
//				
//			} else {
//				Toast.makeText(getApplicationContext(), "Not a valid user", 40).show();
//				
//			}
//		}
		
		loginbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();
				
				//send data to parse.com for user verifications
				
				ParseUser.logInInBackground(usernametxt, passwordtxt, new LogInCallback() {
					
					@Override
					public void done(ParseUser user, ParseException e) {
						
						if (user != null) {
							Toast.makeText(getApplicationContext(), "Welcome user, you are valid", 40).show();
							
						} else {
							Toast.makeText(getApplicationContext(), "Not a valid user", 40).show();
							
						}
					}
						
					}
				);
			}
		});
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();
				
				ParseUser user = new ParseUser();
				user.setUsername(usernametxt);
				user.setPassword(passwordtxt);
				user.signUpInBackground(new SignUpCallback() {
					
					@Override
					public void done(ParseException e) {
						if (e == null) {
							Toast.makeText(getApplicationContext(), "successfully signed up.", 40).show();
							
						} else {
							Toast.makeText(getApplicationContext(), "sign up error", 40).show();
							
						}
					}
				});
				
			}
		});
		
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ParseUser.logOut();
				finish();
				
			}
		});
	}
}

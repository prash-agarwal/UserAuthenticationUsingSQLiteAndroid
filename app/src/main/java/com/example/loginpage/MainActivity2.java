package com.example.loginpage;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button signin;
    EditText username,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        signin=findViewById(R.id.Signin);
        username=findViewById(R.id.Username);
        pwd=findViewById(R.id.Pwd);
    }
    int c=0;
    dbHelper cred=new dbHelper(this);
    public void onClickSignin(View view){
        int f;
        String s1=String.valueOf(username.getText());
        String s2=String.valueOf(pwd.getText());
        dbHelper cred=new dbHelper(this);
        f=cred.fetchContact(s1,s2);
        if (f==1)
            Toast.makeText(this, "Login Succesfull", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Please Enter the Correct Username and Password", Toast.LENGTH_SHORT).show();
    }
}
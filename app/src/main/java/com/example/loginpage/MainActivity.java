package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button signin,signup;
EditText username,pwd,cpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin=findViewById(R.id.Signin);
        signup=findViewById(R.id.Signup);
        username=findViewById(R.id.USERNAME);
        pwd=findViewById(R.id.PASSWORD);
        cpwd=findViewById(R.id.CPASSWORD);
    }

    dbHelper cred=new dbHelper(this);
    public void onClickSignin(View view){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    public static int c=1;
    public void onClickSignup(View view){
//        Intent intent=new Intent(this,MainActivity3.class);
        String s1= String.valueOf(username.getText());
        String s2= String.valueOf(pwd.getText());
        String s3= String.valueOf(cpwd.getText());
        Boolean a=cred.checkUsername(s1);
        Boolean b=cred.insertDetails(c,s1,s2,s3);
        if (a){
            Toast.makeText(this, "Account already Exists. Plz SignIn", Toast.LENGTH_SHORT).show();
        }
        else{
            if (b)
                Toast.makeText(this, "Registered Succesfully!!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }

    }
}
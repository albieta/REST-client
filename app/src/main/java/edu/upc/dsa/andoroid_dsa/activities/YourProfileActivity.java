package edu.upc.dsa.andoroid_dsa.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.models.UserInformation;

public class YourProfileActivity extends AppCompatActivity {
    public String username;
    public String surname;
    public String birthday;
    public String email;
    public String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_profile_main);
        this.getVariables();
        this.updateLabels();
    }
    public void updateLabels(){
        String updateTitle =getString(R.string.updating_title);
        updateTitle=this.username+" !";
        EditText editorTitle = (EditText) findViewById (R.id.title_profile);
        editorTitle.setText(updateTitle);
        String updateUsername =getString(R.string.updating_username);
        updateUsername=this.username;
        EditText editorUsername = (EditText) findViewById (R.id.user_name);
        editorUsername.setText(updateUsername);
        String updateSurname =getString(R.string.updating_surname);
        updateSurname=this.surname;
        EditText editorSurname = (EditText) findViewById (R.id.sur_name);
        editorSurname.setText(updateSurname);
        String updateBirthday =getString(R.string.updating_birthday);
        updateBirthday=this.birthday;
        EditText editorBirthday = (EditText) findViewById (R.id.birth_day);
        editorBirthday.setText(updateBirthday);
        String updateEmail =getString(R.string.updating_email);
        updateEmail=this.email;
        EditText editorEmail = (EditText) findViewById (R.id.e_mail);
        editorEmail.setText(updateEmail);
        String updatePassword =getString(R.string.updating_password);
        updatePassword=this.password;
        EditText editorPassword = (EditText) findViewById (R.id.pass_word);
        editorPassword.setText(updatePassword);
    }
    public void getVariables(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInformation", Context.MODE_PRIVATE);
        this.username = sharedPreferences.getString("username",null).toString();
        this.surname = sharedPreferences.getString("surname",null).toString();
        this.birthday = sharedPreferences.getString("birthday",null).toString();
        this.email = sharedPreferences.getString("email",null).toString();
        this.password = sharedPreferences.getString("password",null).toString();
    }
    public void Return(View view){
        Intent intentRegister = new Intent(YourProfileActivity.this, DashBoardActivity.class);
        YourProfileActivity.this.startActivity(intentRegister);
    }
}

package edu.upc.dsa.rest_client.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import edu.upc.dsa.rest_client.Api;
import edu.upc.dsa.rest_client.R;
import edu.upc.dsa.rest_client.RetrofitClient;
import edu.upc.dsa.rest_client.models.UserInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {
    public CardView yourProfile, gadgetShop, logOut;
    public String userId;
    public String username;
    Api APIservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_main);

        SharedPreferences sharedPreferences = getSharedPreferences("userId", Context.MODE_PRIVATE);
        this.userId = sharedPreferences.getString("userId",null).toString();
        this.getUserById(this.userId);
        this.getCardViewsReady();
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.profiles:
                i=new Intent(this,YourProfileActivity.class);
                startActivity(i);
                break;
            case R.id.gadgetCard:
                i=new Intent(this,GadgetActivity.class);
                startActivity(i);
                break;
            case R.id.returnBtn:
                i=new Intent(this, PrincipalActivity.class);
                startActivity(i);
                break;
        }
    }
    public void getUserById(String userId){
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<UserInformation> call =APIservice.getUser(this.userId);
        call.enqueue(new Callback<UserInformation>() {
            @Override
            public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {
                switch (response.code()){
                    case 201:
                        UserInformation userInformation=response.body();
                        assert userInformation != null;
                        updateLabel(userInformation);
                        saveVariables(userInformation);
                        Toast.makeText(DashBoardActivity.this,"Correctly received UserInformation", Toast.LENGTH_SHORT).show();
                        break;
                    case 409:
                        Toast.makeText(DashBoardActivity.this,"Could not reach UserInformation!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onFailure(Call<UserInformation> call, Throwable t) {
                Toast.makeText(DashBoardActivity.this,"NETWORK FAILURE :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateLabel(UserInformation userInformation){
        this.username=userInformation.getName();
        String update_title =getString(R.string.updating_title);
        update_title=this.username+" !";
        EditText e = (EditText) findViewById (R.id.Edit_Title_Update);
        e.setText(update_title);
    }
    public void getCardViewsReady(){
        yourProfile=(CardView) findViewById(R.id.profiles);
        gadgetShop=(CardView) findViewById(R.id.gadgetCard);

        yourProfile.setOnClickListener(this);
        gadgetShop.setOnClickListener(this);

    }
    public void saveVariables(UserInformation userInformation) {
        SharedPreferences sharedPreferences= getSharedPreferences("userInformation", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("username",userInformation.getName());
        editor.putString("surname",userInformation.getSurname());
        editor.putString("birthday",userInformation.getBirthday());
        editor.putString("email",userInformation.getEmail());
        editor.putString("password",userInformation.getPassword());
        Log.i("SAVING: ",userInformation.getName());
        Log.i("SAVING: ",userInformation.getSurname());
        Log.i("SAVING: ",userInformation.getBirthday());
        Log.i("SAVING: ",userInformation.getEmail());
        Log.i("SAVING: ",userInformation.getPassword());
        editor.apply();
    }

}
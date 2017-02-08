package com.example.cbluser3.jsonparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cbluser3.jsonparse.SignUpDataModel.PojoBase;
import com.example.cbluser3.jsonparse.webservice.RestClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText etName,etEmail,etPhone,etPassword;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ConnectionDetector connectDetect=new ConnectionDetector(this);

        etName=(EditText)findViewById(R.id.etName);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        progressBar=(ProgressBar)findViewById(R.id.pbBar);
        Button button=(Button)findViewById(R.id.btUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                signup();
            }

            private void signup() {

                if(connectDetect.isConnectingToInternet()){

                    Map<String,String> map=new HashMap<>();
                    String name=etName.getText().toString().trim();
                    String email=etEmail.getText().toString().trim();
                    String phone=etPhone.getText().toString().trim();
                    map.put("phoneNo",phone);
                    map.put("appVersion","1");
                    map.put("deviceToken","123213");
                    map.put("deviceType","ANDROID");
                    map.put("email",email);
                    map.put("password","123213");
                    map.put("countryCode","+91");
                    map.put("name",name);
                    map.put("cityId","58930e59731ddfe7477ac95f");


                   /* SignupModel model=new SignupModel();
                    model.appVersion="1";
                    model.phoneNo=etPhone.getText().toString().trim();
                    model.deviceToken="23132131";
                    model.deviceType="ANDROID";
                    model.email=etEmail.getText().toString().trim();
                    model.password=etPassword.getText().toString().trim();
                    model.countryCode="+91";
                    model.name=etName.getText().toString().trim();
                    model.cityId="58930e59731ddfe7477ac95f";*/

                    RestClient.getClient().signup(map).enqueue(new Callback<PojoBase>() {
                        @Override
                        public void onResponse(Response<PojoBase> response, Retrofit retrofit) {
                            if(response.isSuccess())
                            {
                                PojoBase base=response.body();
                                String name=base.data.data.name;
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
                            }
                            else {
                                try {
                                    //json bad response body objects
                                    progressBar.setVisibility(View.INVISIBLE);
                                    JSONObject jsonObject=new JSONObject(response.errorBody().string());
                                    Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });


                }
            }
        });

    }
}

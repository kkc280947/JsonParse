package com.example.cbluser3.jsonparse.webservice;

import com.example.cbluser3.jsonparse.SignUpDataModel.PojoBase;
import com.example.cbluser3.jsonparse.SignUpDataModel.SignupModel;
import com.example.cbluser3.jsonparse.utils.Constants;

import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by cbluser3 on 8/2/17.
 */

public interface API {


    @POST(Constants.SIGNUP_URL)
    Call<PojoBase> signup(@Body SignupModel model);

    @POST(Constants.SIGNIN_URL)

    @FormUrlEncoded
    @POST(Constants.SIGNUP_URL)
    Call<PojoBase> signup(@FieldMap Map<String,String> map);


}

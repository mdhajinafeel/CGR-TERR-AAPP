package com.cgr.codrinterraerp.services;

import com.cgr.codrinterraerp.constants.IAPIConstants;
import com.cgr.codrinterraerp.model.request.LoginRequest;
import com.cgr.codrinterraerp.model.request.LogoutRequest;
import com.cgr.codrinterraerp.model.response.LoginResponse;
import com.cgr.codrinterraerp.model.response.LogoutResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthApiService {

    @POST(IAPIConstants.LOGIN)
    Call<LoginResponse> postLogin(@Body LoginRequest loginRequest);

    @POST(IAPIConstants.LOGOUT)
    Call<LogoutResponse> postLogout(@Body LogoutRequest logoutRequest);
}
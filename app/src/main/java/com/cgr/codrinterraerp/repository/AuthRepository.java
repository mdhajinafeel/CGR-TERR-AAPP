package com.cgr.codrinterraerp.repository;

import com.cgr.codrinterraerp.model.request.LoginRequest;
import com.cgr.codrinterraerp.model.request.LogoutRequest;
import com.cgr.codrinterraerp.model.response.LoginResponse;
import com.cgr.codrinterraerp.model.response.LogoutResponse;
import com.cgr.codrinterraerp.services.IAuthApiService;

import javax.inject.Inject;

import retrofit2.Call;

public class AuthRepository {

    private final IAuthApiService authApiService;

    @Inject
    public AuthRepository(IAuthApiService authApiService) {
        this.authApiService = authApiService;
    }

    public Call<LoginResponse> authLogin(LoginRequest loginRequest) {
        return authApiService.postLogin(loginRequest);
    }

    public Call<LogoutResponse> authLogout(LogoutRequest logoutRequest) {
        return authApiService.postLogout(logoutRequest);
    }
}
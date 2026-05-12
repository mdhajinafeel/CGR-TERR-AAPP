package com.cgr.codrinterraerp.services;

import com.cgr.codrinterraerp.constants.IAPIConstants;
import com.cgr.codrinterraerp.model.response.DownloadMasterResponse;
import com.cgr.codrinterraerp.model.response.DownloadTransactionsResponse;
import com.cgr.codrinterraerp.model.response.OriginsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMasterApiService {

    @GET(IAPIConstants.ORIGINS)
    Call<OriginsResponse> getOrigins();

    @GET(IAPIConstants.DOWNLOAD_MASTERS)
    Call<DownloadMasterResponse> masterDownload();

    @GET(IAPIConstants.DOWNLOAD_TRANSACTIONS)
    Call<DownloadTransactionsResponse> transactionDownload(@Query("lastSync") long lastSync);
}
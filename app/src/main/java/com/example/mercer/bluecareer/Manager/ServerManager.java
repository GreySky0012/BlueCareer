package com.example.mercer.bluecareer.Manager;

import com.example.mercer.bluecareer.DataStruct.RetureCode;
import com.example.mercer.bluecareer.DataStruct.Url.Url;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by GreySky on 2017/11/8.
 */
public class ServerManager {
    private static ServerManager _instance;

    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private ServerManager(){

    }

    public static ServerManager GetInstance(){
        if (_instance == null)
            _instance = new ServerManager();
        return _instance;
    }
    public RetureCode GetSync(Url url) throws IOException {
        Request request = new Request.Builder().url(url.Get()).get().build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException(response.toString());
        return new Gson().fromJson(response.body().string(),RetureCode.class);
    }

    public RetureCode PostSync(Url url, String json) throws IOException {
        Request request = new Request.Builder().url(url.Get()).post(RequestBody.create(JSON,json)).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException(response.toString());
        return new Gson().fromJson(response.body().string(),RetureCode.class);
    }

    public RetureCode PostSync(Url url) throws IOException {
        Request request = new Request.Builder().url(url.Get()).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException(response.toString());
        return new Gson().fromJson(response.body().string(),RetureCode.class);
    }
}

package com.example.mercer.bluecareer.Manager;

import com.example.mercer.bluecareer.DataStruct.RetureCode;
import com.example.mercer.bluecareer.DataStruct.Url.Url;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
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

    enum Method{get,put,post};

    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private ServerManager(){

    }

    public static ServerManager GetInstance(){
        if (_instance == null)
            _instance = new ServerManager();
        return _instance;
    }
    public RetureCode RequestSync(Method method,Url url) throws IOException {
        Request.Builder builder = AddMethod(new Request.Builder(),method).url(url.Get());
        Response response = RequestSyne(builder);
        return new Gson().fromJson(response.body().string(),RetureCode.class);
    }

    public RetureCode RequestSync(Method method,Url url, String json) throws IOException {
        Request.Builder builder = AddMethod(new Request.Builder(),method,RequestBody.create(JSON,json)).url(url.Get());
        Response response = RequestSyne(builder);
        return new Gson().fromJson(response.body().string(),RetureCode.class);
    }

    public RetureCode RequestSync(Method method, Url url, HashMap<String,String> headers) throws IOException {
        Request.Builder builder = AddHeader(AddMethod(new Request.Builder(),method),headers).url(url.Get());
        Response response = RequestSyne(builder);
        return new Gson().fromJson(response.body().string(),RetureCode.class);
    }

    public RetureCode RequestSync(Method method,Url url, String json,HashMap<String,String> headers) throws IOException {
        Request.Builder builder = AddHeader(AddMethod(new Request.Builder(),method,RequestBody.create(JSON,json)),headers).url(url.Get());
        Response response = RequestSyne(builder);
        return new Gson().fromJson(response.body().string(),RetureCode.class);
    }

    private Response RequestSyne(Request.Builder builder) throws IOException {
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException(response.toString());
        return response;
    }

    private Request.Builder AddHeader(Request.Builder builder,HashMap<String,String> headers){
        Headers.Builder headersBuilder = new Headers.Builder();
        for (Map.Entry<String,String> header: headers.entrySet()){
            headersBuilder.add(header.getKey(),header.getValue());
        }
        builder.headers(headersBuilder.build());
        return builder;
    }

    private Request.Builder AddMethod(Request.Builder builder, Method method,RequestBody body){
        if (body == null){
            return builder;
        }
        switch (method){
            case post:
                return builder.post(body);
            case put:
                return builder.put(body);
            default:
                return builder;
        }
    }
    private Request.Builder AddMethod(Request.Builder builder,Method method){
        switch (method){
            case get:
                return builder.get();
            default:
                return builder;
        }
    }
}
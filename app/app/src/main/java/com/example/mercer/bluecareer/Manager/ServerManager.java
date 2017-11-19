package com.example.mercer.bluecareer.Manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mercer.bluecareer.DataStruct.ReturnCode;
import com.example.mercer.bluecareer.DataStruct.Url.Url;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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

    public void RequestAsync(Method method,Url url,String json,HashMap<String,String> headers) throws IOException{
        RequestBody requestBody = RequestBody.create(JSON,json);
        Request.Builder builder = AddHeader(AddMethod(new Request.Builder(),method,requestBody).url(url.Get()),headers);
        RequestAsync(builder);
    }

    public void RequestAsync(Method method,Url url,File file,HashMap<String,String> headers,Callback callback) throws IOException{
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png; charset=utf-8"),file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file","form-data; name=mFile; filename=image.png",fileBody).build();
        Request.Builder builder = AddHeader(AddMethod(new Request.Builder(),method,requestBody).url(url.Get()),headers);
        RequestAsync(builder,callback);
    }

    public void RequestAsync(Method method,Url url,Callback callback) throws IOException{
        Request.Builder builder = AddMethod(new Request.Builder(),method).url(url.Get());
        RequestAsync(builder,callback);
    }

    public ReturnCode RequestSync(Method method,Url url) throws IOException {
        Request.Builder builder = AddMethod(new Request.Builder(),method).url(url.Get());
        Response response = RequestSync(builder);
        return new Gson().fromJson(response.body().string(),ReturnCode.class);
    }

    public ReturnCode RequestSync(Method method,Url url, String json) throws IOException {
        Request.Builder builder = AddMethod(new Request.Builder(),method,RequestBody.create(JSON,json)).url(url.Get());
        Response response = RequestSync(builder);
        return new Gson().fromJson(response.body().string(),ReturnCode.class);
    }

    public ReturnCode RequestSync(Method method, Url url, HashMap<String,String> headers) throws IOException {
        Request.Builder builder = AddHeader(AddMethod(new Request.Builder(),method),headers).url(url.Get());
        Response response = RequestSync(builder);
        return new Gson().fromJson(response.body().string(),ReturnCode.class);
    }

    public ReturnCode RequestSync(Method method,Url url, String json,HashMap<String,String> headers) throws IOException {
        Request.Builder builder = AddHeader(AddMethod(new Request.Builder(),method,RequestBody.create(JSON,json)),headers).url(url.Get());
        Response response = RequestSync(builder);
        return new Gson().fromJson(response.body().string(),ReturnCode.class);
    }

    public Bitmap RequestFileSync(Url url, HashMap<String,String> header) throws IOException {
        Request.Builder builder = AddHeader(AddMethod(new Request.Builder(),Method.get),header).url(url.Get());
        return BitmapFactory.decodeStream(RequestSync(builder).body().byteStream());
    }

    private Response RequestSync(Request.Builder builder) throws IOException {
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException(response.toString());
        return response;
    }

    private void RequestAsync(Request.Builder builder) throws IOException {
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void RequestAsync(Request.Builder builder, Callback callback) throws IOException {
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(callback);
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
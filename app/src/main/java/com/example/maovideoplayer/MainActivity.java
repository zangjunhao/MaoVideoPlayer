package com.example.maovideoplayer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String>VideoUrl=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getVideoUrl(this);
    }
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    private void init()
    {

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(VideoUrl);
        recyclerView.setAdapter(recyclerAdapter);
    }
    private void getVideoUrl(Context context)
    {
        if(isNetworkConnected(context)){
            HttpConnect httpConnect=new HttpConnect("http://route.showapi.com/255-1?showapi_appid=38518&showapi_sign=f00376d530ae4e799eae6fc301811c5f&type=41&title=&page=&");

            httpConnect.sendRequestWithHttpURLConnection(new HttpConnect.Callback() {
                @Override
                public void finish(String respone) {
                parseJSON(respone);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        init();
                    }
                });
                }
            });
        }
        else {
            Toast.makeText(context,"网络连接不成功",Toast.LENGTH_SHORT).show();
        }
    }

    private void parseJSON(String respone) {
        try {
            JSONObject jsonObject=new JSONObject(respone);
            jsonObject=jsonObject.getJSONObject("showapi_res_body");
            jsonObject=jsonObject.getJSONObject("pagebean");
            String result=jsonObject.getString("contentlist");
            JSONArray jsonArray=new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                VideoUrl.add(jsonObject1.getString("video_uri"));

            }
            Log.d("网址数量", "一共有 "+VideoUrl.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

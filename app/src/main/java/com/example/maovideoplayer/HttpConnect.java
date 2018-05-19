package com.example.maovideoplayer;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 67698 on 2018/5/19.
 */

public class HttpConnect {
    private String url1;
    public HttpConnect(String url)
    {
        url1=url;
    }
    interface Callback{
        void finish(String respone);
    }
    public void sendRequestWithHttpURLConnection(final Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Http", "run:进行网络连接 ");
                HttpURLConnection connection=null;
                BufferedReader reader;
                try {
                    URL url=new URL(url1);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(6000);
                    connection.setReadTimeout(6000);
                    InputStream inputStream=connection.getInputStream();
                    //if(inputStream!=null) Log.d("难受呀嘤嘤毛", "inputstream不为空 ");
                    reader=new BufferedReader(new InputStreamReader(inputStream));
                    // if(reader!=null) Log.d("难受呀嘤嘤毛", "BufferedReader不为空"+reader);
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null)
                    {
                        response.append(line);
                    }
                    //  Log.d("response", "内容为 "+response.toString());
                    if(callback!=null){
                        Log.d("Callback", "执行了finish");
                        callback.finish(response.toString());
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null)connection.disconnect();
                }
            }
        }).start();
    }

}

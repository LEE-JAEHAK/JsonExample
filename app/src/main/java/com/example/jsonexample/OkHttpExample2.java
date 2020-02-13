//package com.example.jsonexample;
//
//import android.graphics.Movie;
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import okhttp3.*;
//
//import java.io.IOException;
//
////import static com.example.jsonexample.MainActivity.send;
//
//public class OkHttpExample2 {
//
//    // only one client
//    private final OkHttpClient httpClient = new OkHttpClient();
//
//    public void sendGET() throws IOException {
//
//        Request request = new Request.Builder()
//                .url("https://openapi.naver.com/v1/search/news.json?query=인하대&display=1")
//                .addHeader("X-Naver-Client-Id", "YYVcX2Awo6gErINXvrUM")
//                .addHeader("X-Naver-Client-Secret", "wrAmpDdqIx")
//                .build();
//
//        httpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                try (final ResponseBody responseBody = response.body()) {
//                    if (!response.isSuccessful())
//                        throw new IOException("Unexpected code " + response);
//
//                    // Get response headers
//                    Headers responseHeaders = response.headers();
//                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                    }
//
//                    JsonParser jsonParser = new JsonParser();
//                    String json = responseBody.string();
//                    Log.d("json", json);
//
//                    JsonObject object = (JsonObject) jsonParser.parse(json);
//
//                    String LBD = object.get("lastBuildDate").getAsString();
//                    String total = object.get("total").getAsString();
//                    String start = object.get("start").getAsString();
//                    String display = object.get("display").getAsString();
//                    JsonArray jsonArray = object.getAsJsonArray("items");
//                    JsonElement jsonElement1=jsonArray.get(0);
//                    //JsonElement jsonElement2=jsonArray.get(1);
//                    String title=jsonElement1.getAsJsonObject().get("title").getAsString();
//                    String link=jsonElement1.getAsJsonObject().get("originallink").getAsString();
//
//                    System.out.println(LBD+total+start+display+title+link);
//                    //send(LBD,total,start,display,title,link);
//                }
//            }
//
//        });
//
//    }
//
//
//
//}
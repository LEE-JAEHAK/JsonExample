package com.example.jsonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jsonexample.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    TextView mTitle;
    TextView mLink;
    TextView mDescription;
    TextView mPubdate;
    BlogList mBlogList = new BlogList();
    private String path = "https://openapi.naver.com/v1/search/news.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        OkHttpExample2 ok = new OkHttpExample2();
//        try {
//            ok.sendGET();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        mTitle = findViewById(R.id.textView10);
        mLink = findViewById(R.id.textView12);
        mDescription = findViewById(R.id.textView14);
        mPubdate = findViewById(R.id.textView16);

        ExampleAsyncTask asyncTask = new ExampleAsyncTask();
        asyncTask.execute();

//        Log.d("json1", "onCreate: "+blogList.getTitle()); //null

        //title.setText(blogList.getTitle());
//        link.setText(blogList.getLink());
//        description.setText(blogList.getDescription());

    }

//    public class OkHttpExample2 {
//
//        // only one client
//        private final OkHttpClient httpClient = new OkHttpClient();
//
//        public void sendGET() throws IOException {
//
//            Request request = new Request.Builder()
//                    .url("https://openapi.naver.com/v1/search/news.json?query=인하대&display=1")
//                    .addHeader("X-Naver-Client-Id", "YYVcX2Awo6gErINXvrUM")
//                    .addHeader("X-Naver-Client-Secret", "wrAmpDdqIx")
//                    .build();
//
//            httpClient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, final Response response) throws IOException {
//                    try (final ResponseBody responseBody = response.body()) {
//                        if (!response.isSuccessful())
//                            throw new IOException("Unexpected code " + response);
//
//                        // Get response headers ? 이게 뭐지 이거 그냥 예제에서 가져온거 아 별로 아무 의미 없ㄴㅡㄴ거
//                        Headers responseHeaders = response.headers();
//                        for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                        }
//
//                        JsonParser jsonParser = new JsonParser();
//                        String json = responseBody.string();
//                        Log.d("json1", json);
//
//
//
//                        JsonObject object = (JsonObject) jsonParser.parse(json);
//
//                        String LBD = object.get("lastBuildDate").getAsString();
//                        String total = object.get("total").getAsString();
//                        String start = object.get("start").getAsString();
//                        String display = object.get("display").getAsString();
//                        JsonArray jsonArray = object.getAsJsonArray("items");
//                        JsonElement jsonElement1 = jsonArray.get(0);
//                        //JsonElement jsonElement2=jsonArray.get(1);
//                        String title = jsonElement1.getAsJsonObject().get("title").getAsString();
//                        String link = jsonElement1.getAsJsonObject().get("originallink").getAsString();
//
//                        System.out.println(LBD + total + start + display + title + link);
//
//                        blogList.setTitle(title);
//                        blogList.setLink(link);
//                        blogList.setDescription(LBD);
//                    }
//                }
//
//            });
//        }
//
//
//    }

    //AsyncTask
    public class ExampleAsyncTask extends AsyncTask<String, Void, JsonObject> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("\t로딩중...");
            progressDialog.show();
        }

        @Override
        protected JsonObject doInBackground(String... params) {


            Request request = new Request.Builder()
                    .url("https://openapi.naver.com/v1/search/news.json?query=인하대&display=1")
                    .addHeader("X-Naver-Client-Id", "YYVcX2Awo6gErINXvrUM")
                    .addHeader("X-Naver-Client-Secret", "wrAmpDdqIx")
                    .build();

            try {
                Response response = client.newCall(request).execute();

                Gson gson = new GsonBuilder().create();
                JsonParser parser = new JsonParser();
                JsonObject rootObject = parser.parse(response.body().charStream()).getAsJsonObject();
                Log.d("json1", "doInBackground: "+rootObject);
                return rootObject;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JsonObject result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Log.d("json1", "onPostExecute: "+result);

            //요청 가져온 것들 여기서 처리
            JsonArray array = result.getAsJsonArray("items");
            JsonElement element = array.get(0);
            String title = element.getAsJsonObject().get("title").getAsString();
            String originallink = element.getAsJsonObject().get("originallink").getAsString();
            String link = element.getAsJsonObject().get("link").getAsString();
            String description = element.getAsJsonObject().get("description").getAsString();
            String pubDate = element.getAsJsonObject().get("pubDate").getAsString();

            mTitle.setText(title);
            mLink.setText(link);
            mDescription.setText(description);
            mPubdate.setText(pubDate);

        }
    }
}

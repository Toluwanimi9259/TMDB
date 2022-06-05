package com.apps.themafia.tmdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    TextView mTextView1 , mTextView2 , mTextView3 , mTextView4 , mTextView5 , mTextView6 , mTextView7 , mTextView8 , mTextView9 , mTextView10 , mTextView11;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        mTextView1 = findViewById(R.id.titleTextview);
        mTextView2 = findViewById(R.id.textView2);
        mTextView3 = findViewById(R.id.textView3);
        mTextView4 = findViewById(R.id.textView4);
        mTextView5 = findViewById(R.id.textView5);
        mTextView6 = findViewById(R.id.textView6);
        mTextView7 = findViewById(R.id.textView7);
        mTextView8 = findViewById(R.id.textView8);
        mTextView9 = findViewById(R.id.textView9);
        mTextView10 = findViewById(R.id.textView10);
        mTextView11 = findViewById(R.id.textView11);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent myIntent = getIntent();
        String title = myIntent.getStringExtra("Title");
        String year = myIntent.getStringExtra("Year");
        if (title != null){
            RequestParams params = new RequestParams();
            params.put("t" , title);
            params.put("y" , year);
            getMovieData(params);
        }else{
            Log.d("ZXCVBNM: " , " IN ELSE METHOD");
            finish();
        }
    }

    private void getMovieData(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://omdbapi.com/?apikey=a2b565d7&" , params , new JsonHttpResponseHandler(){
            /**
             * Returns when request succeeds
             *
             * @param statusCode http response status line
             * @param headers    response headers if any
             * @param response   parsed response if any
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("ZXCVBNM Sucess  " , "" );
                DataModel data = DataModel.sDataModel(response);
                Log.d("ZXCVBNM Director  " , "" + data.getDirector() );
                Log.d("ZXCVBNM Title  " , "" + data.getTitle());
                Log.d("ZXCVBNM Actors  " , "" + data.getActors());
                Log.d("ZXCVBNM Awards " , "" + data.getAwards());
                Log.d("ZXCVBNM BoxOffice  " , "" + data.getBoxOffice());
                Log.d("ZXCVBNM Plot  " , "" + data.getPlot());
                Log.d("ZXCVBNM Rottten Tomatoes  " , "" + data.getImageURL());
                updateView(data);
                ImageDownloader task = new ImageDownloader();
                Bitmap myImage = null;
                try {
                    myImage = task.execute(data.getImageURL()).get();
                    image.setImageBitmap(myImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            /**
             * Returns when request failed
             *
             * @param statusCode    http response status line
             * @param headers       response headers if any
             * @param throwable     throwable describing the way request failed
             * @param errorResponse parsed response if any
             */
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("ZXCVBNM Failure " , " " + throwable.toString());
            }
        });
    }

    public void updateView(DataModel dataModel){
        mTextView1.setText("Title : " + dataModel.getTitle() + " (" + dataModel.getYear() + ")");
        mTextView2.setText("Actors: " + dataModel.getActors());
        mTextView3.setText("Director (s) : " + dataModel.getDirector());
        mTextView4.setText("BoxOffice : " + dataModel.getBoxOffice());
        mTextView5.setText("Awards : " + dataModel.getAwards());
        mTextView7.setText("IMDB Rating : " + dataModel.getImdbRating() + "/10");
        mTextView6.setText("Genre : " + dataModel.getGenre());
        mTextView8.setText("Rotten Tomatoes : " + dataModel.getRottenTomatoes());
        mTextView9.setText("MetaScore : " + dataModel.getMetaScore());
        mTextView10.setText("Type : " + dataModel.getType());
        mTextView11.setText("Runtime : " + dataModel.getRuntime());

    }

    public class ImageDownloader extends AsyncTask<String , Void , Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream in = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}

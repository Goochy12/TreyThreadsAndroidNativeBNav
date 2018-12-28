package au.com.liamgooch.treythreads;

import android.os.AsyncTask;

public class LoadInstagram extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private final String instagramAPI = "https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN";

    public static String INSTAGRAM_CLIENT_ID = "6bf87fc1a66642c5b26081552d6ace9a";
    public static String INSTAGRAM_CLIENT_ID_SECRET = "e174aeafbc3440cd9eaea12bb2950ec4";

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {

        return null;
    }
}

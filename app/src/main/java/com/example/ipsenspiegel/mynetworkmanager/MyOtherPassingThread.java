package com.example.ipsenspiegel.mynetworkmanager;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by A5Alumno on 28/11/2016.
 */

public class MyOtherPassingThread extends AsyncTask<String, Void, String> {


    private static final String TAG = "TAG_MY_OTHER_PASSING_THREAD";
    private Context threadContext;


    @Override
    protected @Nullable String doInBackground(String[] params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();
            int respCode = connection.getResponseCode();
            Log.i(TAG, "Responce code: " + respCode);

            if (respCode == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = connection.getInputStream();

                XmlPullParser xmlPullParser = Xml.newPullParser();
                xmlPullParser.setInput(inputStream, null);

                StringBuilder stringBuilder = new StringBuilder("");


                int event = xmlPullParser.nextTag();
                //done while the documend end is not reached

                while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {

                    switch (event) {
                        case XmlPullParser.START_TAG:
                            if (xmlPullParser.getName().equals("item")) {
                                xmlPullParser.nextTag();
                                xmlPullParser.next();
                                stringBuilder.append(xmlPullParser.getText()).append("\n");
                            }
                            break;
                    }

                    event = xmlPullParser.next();
                }

                inputStream.close();
                Log.i(TAG, stringBuilder.toString());
                return stringBuilder.toString();
            }
        } catch (XmlPullParserException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(@Nullable String returnString) {
        super.onPostExecute(returnString);
        if (returnString != null){
            Toast.makeText(this.threadContext, returnString, Toast.LENGTH_SHORT).show();
        }
    }
}

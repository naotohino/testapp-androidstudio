package com.example.naoto.testapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Xml;
import android.widget.ArrayAdapter;

import com.example.naoto.testapp.utils.LogUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Naoto on 2015/01/01.
 */
public class RssParserTask extends AsyncTask<String, Integer, ArrayAdapter> {

    private RssReaderFragment mRssReaderFragment;
    private Context mContext;
    private ArrayAdapter<Item> mAdapter;
    private ProgressDialog mProgressDialog;

    // コンストラクタ
    public RssParserTask(RssReaderFragment fragment, Context context,ArrayAdapter adapter) {
        LogUtil.d();
        mRssReaderFragment = fragment;
        mContext = context;
        mAdapter = adapter;

        if(fragment == null){
            throw new IllegalArgumentException("fragment is null");
        }

        if(adapter == null){
            throw new IllegalArgumentException("adapter is null");
        }

    }

    // タスクを実行した直後にコールされる
    @Override
    protected void onPreExecute() {
        // プログレスバーを表示する
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Now Loading...");
        mProgressDialog.show();
    }

    // バックグラウンドにおける処理を担う。タスク実行時に渡された値を引数とする
    @Override
    protected ArrayAdapter doInBackground(String... params) {
        LogUtil.d();
        ArrayAdapter result = null;
        try {
            // HTTP経由でアクセスし、InputStreamを取得する
            URL url = new URL(params[0]);
            InputStream is = url.openConnection().getInputStream();
            result = parseXml(is);
        } catch (Exception e) {
            LogUtil.e(e.toString());
            e.printStackTrace();
        }
        // ここで返した値は、onPostExecuteメソッドの引数として渡される
        return result;
    }

    // メインスレッド上で実行される
    @Override
    protected void onPostExecute(ArrayAdapter result) {
        LogUtil.d();
        mProgressDialog.dismiss();
        mRssReaderFragment.setListAdapter(result);
    }

    // XMLをパースする
    public ArrayAdapter parseXml(InputStream is) throws IOException, XmlPullParserException {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(is, null);
            int eventType = parser.getEventType();
            Item currentItem = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tag = null;
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        LogUtil.d(tag);
                        if (tag.equals("item")) {
                            currentItem = new Item();
                        } else if (currentItem != null) {
                            if (tag.equals("title")) {
                                currentItem.setTitle(parser.nextText());
                            } else if (tag.equals("description")) {
                                currentItem.setDescription(parser.nextText());
                            } else if (tag.equals("enclosure")){
                                currentItem.setEnclosure(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        LogUtil.d(tag);
                        if (tag.equals("item")) {
                            LogUtil.d(currentItem.toString());
                            mAdapter.add(currentItem);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mAdapter;
    }
}
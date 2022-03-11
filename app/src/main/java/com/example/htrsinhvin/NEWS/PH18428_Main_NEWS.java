package com.example.htrsinhvin.NEWS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.htrsinhvin.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PH18428_Main_NEWS extends AppCompatActivity {

    ListView lvNew;
    List<String> lsTitle = new ArrayList<>();
    List<String> lsLink = new ArrayList<>();

    ArrayAdapter<String> arrayAdapter;

    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_news);

        lvNew = findViewById(R.id.listViewNews);

        new tinTuc().execute("https://vnexpress.net/rss/giao-duc.rss");
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lsTitle);
        lvNew.setAdapter(arrayAdapter);
        lvNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String link = lsLink.get(i);
                in = new Intent(PH18428_Main_NEWS.this, PH18428_detail_News.class);

                in.putExtra("linkid",link);
                startActivity(in);
            }
        });
    }

    public class tinTuc extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder builder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);

                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(reader);
                //doc tung dong
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    builder.append(line);

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            PH18428_XMLPar par = new PH18428_XMLPar();
            try {
                Document document = par.getDocument(s);

                NodeList nodeList = document.getElementsByTagName("item");

                String link ="";
                String title="";

                for (int i =0; i<nodeList.getLength();i++){
                    Element element = (Element) nodeList.item(i);

                    title=par.getValue(element,"title");
                    lsTitle.add(title);

                    link = par.getValue(element,"link");
                    lsLink.add(link);
                }

                arrayAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }
    }
}
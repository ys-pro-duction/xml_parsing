package com.ys_production.xmlparsing;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ys_production.xmlparsing.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
//        DownloadfiLe downloadfiLe = new DownloadfiLe();
//        downloadfiLe.execute("http://ax.itunes.apple.com./WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");
        binding.et1.setText("http://ax.itunes.apple.com./WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");
        binding.et1.setVisibility(View.GONE);
        binding.b1.setVisibility(View.GONE);
        new DownloadfiLe().execute(binding.et1.getText().toString());
        binding.b1.setOnClickListener(v -> new DownloadfiLe().execute(binding.et1.getText().toString()));

    }
    private class DownloadfiLe extends AsyncTask<String, Object, String> {
        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: ");
            parseXml pxml = new parseXml();
            pxml.parse(s);
//            binding.listView1.setAdapter(new ArrayAdapter<>(MainActivity.this, R.layout.list_item1, pxml.getApplication()));
//            binding.listView1.setFooterDividersEnabled(true);
//            binding.listView1.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(MainActivity.this, position+" position", Toast.LENGTH_SHORT).show());
            binding.b1.setVisibility(View.GONE);
            binding.et1.setVisibility(View.GONE);
            binding.listView1.setAdapter(new listAdepter(MainActivity.this,R.layout.custom_adepter,pxml.getApplication()));
            binding.listView1.setOnItemClickListener((parent, view, position, id) -> {
                Toast.makeText(MainActivity.this, position+"item", Toast.LENGTH_SHORT).show();
//                view.setVisibility(View.INVISIBLE);
                view.setElevation(10);
            });
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            return downloadData(strings[0]);
        }
        private String downloadData(String urlPath){
            StringBuilder  xmlResult = new StringBuilder();
            try {
            URL url;
                url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();
                Log.d(TAG, "resPonseCode: "+responseCode);
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while((line = reader.readLine()) != null){
                    xmlResult.append(line);
                }
//                int charRead;
//                char[] inputBuffer = new char[500];
//                while(true){
//                    charRead = reader.read(inputBuffer);
//                    if (charRead<0){
//                        break;
//                    }
//                    if (charRead > 0){
//                        xmlResult.append(String.copyValueOf(inputBuffer,0,charRead));
//                    }
//                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return String.valueOf(xmlResult);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();
       if(id == R.id.t_25){
           new DownloadfiLe().execute("http://ax.itunes.apple.com./WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=25/xml");
       }else if (id == R.id.t_100){
           new DownloadfiLe().execute("http://ax.itunes.apple.com./WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=100/xml");
       }else if (id == R.id.t_200){
           new DownloadfiLe().execute("http://ax.itunes.apple.com./WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=200/xml");
       }
        return super.onOptionsItemSelected(item);
    }
}


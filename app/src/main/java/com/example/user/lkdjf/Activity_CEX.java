package com.example.user.lkdjf;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_CEX extends AppCompatActivity {

    public GetInterfaceC getInterface;
    public Retrofit retrofit;
    public CEX data;
    public float timeIndex=0;
    public String symbol1="BTC";
    public String symbol2="USD";
    Response<CEX> res;
    public ArrayList<CEX> bidList = new ArrayList<>();
    public CEXAPI gow = new CEXAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cex);
        retrofit = new Retrofit.Builder().baseUrl("https://cex.io").addConverterFactory(GsonConverterFactory.create()).build();
        getInterface = retrofit.create(GetInterfaceC.class);
        gow.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bchusd:
                symbol1 = "BCH";
                symbol2 = "USD";
                timeIndex = 0;
                TextView v=findViewById(R.id.text);
                v.setText("bchusd");
                bidList.clear();

                break;
            case R.id.btcusd:
                symbol1 = "BTC";
                symbol2 = "USD";
                timeIndex = 0;
                TextView v1=findViewById(R.id.text);
                v1.setText("btcusd");
                bidList.clear();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class CEXAPI extends AsyncTask<Void, Response<CEX>, Response<CEX>> {

        @Override
        protected Response<CEX> doInBackground(Void... voids) {
            res = null;
            while (!isCancelled()) {
                try {
                    Call<CEX> responseCall = getInterface.getData(symbol1, symbol2);
                    res = responseCall.execute();

                } catch (IOException e) { }
                res.body().setTimestamp(String.valueOf(timeIndex));
                publishProgress(res);
                try {
                    timeIndex = timeIndex + 1;
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            return res;
        }

        @Override
        protected void onProgressUpdate(Response<CEX>... CEXResponse) {
            super.onProgressUpdate(CEXResponse);
            data = CEXResponse[0].body();
            bidList.add(data);

            List<Entry> bidEntries = new ArrayList<>();
            List<Entry> askEntries = new ArrayList<>();
            List<Entry> lastEntries = new ArrayList<>();
            for (CEX i : bidList) {
                Float bid = i.getBid();
                Float ask = i.getAsk();
                Float last = i.getLast();
                Float timestamp = i.getTimestamp();

                lastEntries.add(new Entry(timestamp, last));
                bidEntries.add(new Entry(timestamp, bid));
                askEntries.add(new Entry(timestamp, ask));
            }
            LineDataSet bidChart = new LineDataSet(bidEntries, "Bid");
            bidChart.setColor(Color.GREEN);

            LineDataSet askChart = new LineDataSet(askEntries, "Ask");
            askChart.setColor(Color.RED);

            LineDataSet lastChart = new LineDataSet(lastEntries, "Last Price");
            lastChart.setColor(Color.BLACK);

            LineChart chart = findViewById(R.id.lineChart);

            LineData chartData = new LineData();
            chartData.addDataSet(bidChart);
            chartData.addDataSet(askChart);
            chartData.addDataSet(lastChart);
            chart.setData(chartData);
            chart.getAxisLeft().setEnabled(false);
            chart.getXAxis().setAxisMinimum(0);
            chart.getXAxis().setAxisMaximum(8 + timeIndex);
            chart.invalidate();
        }

        @Override
        protected void onPostExecute(Response<CEX> CEXResponse) {
            super.onPostExecute(CEXResponse);
        }
    }

}

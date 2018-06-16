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

public class Activity_Bitfinex extends AppCompatActivity {

    public GetInterfaceB getInterface;
    public Retrofit retrofit;
    public Bitfinex data;
    public float timeIndex=0;
    public String symbol="btcusd";
    Response<Bitfinex> res;
    public ArrayList<Bitfinex> bidList = new ArrayList<>();
    public BitfinexAPI gow = new BitfinexAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitfinex);
        retrofit = new Retrofit.Builder().baseUrl("https://api.bitfinex.com").addConverterFactory(GsonConverterFactory.create()).build();
        getInterface = retrofit.create(GetInterfaceB.class);
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
                symbol = "bchusd";
                timeIndex = 0;
                TextView v=findViewById(R.id.text);
                v.setText("btceur");
                bidList.clear();

                break;
            case R.id.btcusd:
                symbol = "btcusd";
                timeIndex = 0;
                TextView v1=findViewById(R.id.text);
                v1.setText("btcusd");
                bidList.clear();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class BitfinexAPI extends AsyncTask<Void, Response<Bitfinex>, Response<Bitfinex>> {

        @Override
        protected Response<Bitfinex> doInBackground(Void... voids) {
            res = null;
            while (!isCancelled()) {
                try {
                    Call<Bitfinex> responseCall = getInterface.getData(symbol);
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
        protected void onProgressUpdate(Response<Bitfinex>... bitfinexResponse) {
            super.onProgressUpdate(bitfinexResponse);
            data = bitfinexResponse[0].body();
            bidList.add(data);

            List<Entry> bidEntries = new ArrayList<>();
            List<Entry> askEntries = new ArrayList<>();
            List<Entry> lastEntries = new ArrayList<>();
            for (Bitfinex i : bidList) {
                Float bid = i.getBid();
                Float ask = i.getAsk();
                Float last = i.getLastPrice();
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
        protected void onPostExecute(Response<Bitfinex> bitfinexResponse) {
            super.onPostExecute(bitfinexResponse);
        }
    }

}

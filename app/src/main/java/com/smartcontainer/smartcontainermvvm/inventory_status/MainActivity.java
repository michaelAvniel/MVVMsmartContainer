package com.smartcontainer.smartcontainermvvm.inventory_status;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.smartcontainer.smartcontainermvvm.R;
import com.smartcontainer.smartcontainermvvm.data.DataSourceRepository;
import com.smartcontainer.smartcontainermvvm.data.model.Product;
import com.smartcontainer.smartcontainermvvm.data.model.StatusResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {
    private StatusViewModel mStatusViewModel;

    @BindView(R.id.chart)
    PieChart mPieChart;
    MyRecyclerAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setPieChart();
        mStatusViewModel.onProduct();
    }

    private void setPieChart() {

        mPieChart = findViewById(R.id.chart);
        List<PieEntry> entries = new ArrayList<>();


        entries.add(new PieEntry(45f, "Tomato"));
        entries.add(new PieEntry(25f, "Sweet Potato"));
        entries.add(new PieEntry(63f, "Mushroom"));
        entries.add(new PieEntry(92f, "Onion"));
        entries.add(new PieEntry(32f, "Red Pepper"));
        entries.add(new PieEntry(81f, "corn"));

        PieDataSet set = new PieDataSet(entries, null);
        PieData data = new PieData(set);

        int orange = 0xFFFEA22F;
        int bej = 0xFFDD815F;
        int purple = 0xFFD54D59;
        int red = 0xFFFE5601;
        int green = 0xFF73b800;
        int yellow = 0xFFFAD540;
        set.setColors(red, orange, bej, purple, green, yellow);
        set.setValueTextSize(13);
        mPieChart.setData(data);

        mPieChart.getDescription().setEnabled(false);
        mPieChart.setEntryLabelColor(Color.TRANSPARENT);

        mPieChart.invalidate(); // refresh

//update
    }

    private void init() {

        mAdapter = new MyRecyclerAdapter(this, new ArrayList<Product>());
        mRecyclerView = findViewById(R.id.rv_product);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        StatusViewModelFactory factory = new StatusViewModelFactory(new CompositeDisposable(), new DataSourceRepository());
        mStatusViewModel = ViewModelProviders.of(this, factory).get(StatusViewModel.class);
        mStatusViewModel.start();
        observe();
    }

    private void observe() {
        mStatusViewModel.ProductResponse().observe(this, this::renderProductResult);
    }

    private void renderProductResult(StatusResponse statusResponse) {

        mAdapter.setData(statusResponse.data);
        mAdapter.notifyDataSetChanged();
    }
}

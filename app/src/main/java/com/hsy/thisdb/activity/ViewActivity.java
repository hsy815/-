package com.hsy.thisdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.adapter.PersonalAdapter;
import com.hsy.thisdb.db_control.DbControl;
import com.hsy.thisdb.eitity.Personal;

import java.util.List;

@Deprecated
public class ViewActivity extends AppCompatActivity {

    private TextView mBack;
    private TextView mTitle;
    private RecyclerView mRecycle;

    private DbControl dbControl;
    private List<Personal> personals;
    private PersonalAdapter personalAdapter;
    private String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view);
        initUI();
    }

    private void initUI() {
        mBack = findViewById(R.id.view_back);
        mTitle = findViewById(R.id.view_title);
        mRecycle = findViewById(R.id.view_rlv);
        mTitle.setText("搜索结果");
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycle.setLayoutManager(layoutManager);
        mRecycle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initData();
    }

    private void initData() {
//        personals = (List<Personal>) getIntent().getSerializableExtra("presonal");
        dbControl = DbControl.getInstent(this);
        sql = getIntent().getStringExtra("sql");
        personals = dbControl.select(sql);
        personalAdapter = new PersonalAdapter(this, personals);
        mRecycle.setAdapter(personalAdapter);
        personalAdapter.setOnItemClickListener(new PersonalAdapter.OnItemClickListener() {
            @Override
            public void Item(int i, Personal personal) {
                Intent intent = new Intent(ViewActivity.this, DetailsActivity.class);
                intent.putExtra("personal", personal);
                startActivity(intent);
            }
        });
    }

}

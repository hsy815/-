package com.hsy.thisdb.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.adapter.MoreAdapter;
import com.hsy.thisdb.db_control.DbControl;
import com.hsy.thisdb.eitity.Peacetime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Deprecated
public class MoreActivity extends AppCompatActivity {

    @BindView(R.id.more_back)
    TextView moreBack;
    @BindView(R.id.more_title)
    TextView moreTitle;
    @BindView(R.id.more_more)
    TextView moreMore;
    @BindView(R.id.more_cyc)
    RecyclerView moreCyc;

    private DbControl dbControl;
    private MoreAdapter moreAdapter;
    private String content;
    private String[] select_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initData();
    }

    private void initUI(int spanCount) {
//        moreCyc.setLayoutManager(new GridLayoutManager(this, spanCount));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        moreCyc.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        moreCyc.addItemDecoration(decoration);
        moreAdapter = new MoreAdapter(this);
        moreAdapter.setPeacetimeList(getinvestigate0All());
        moreCyc.setAdapter(moreAdapter);
    }

    private void initData() {
        dbControl = DbControl.getInstent(this);
        content = getIntent().getStringExtra("more_sql");
        select_content = new String[]{"Fullname", "PersonalIdCard", "WorkUnit", "Date", "Effect", "Type"};
        initUI(select_content.length);
    }

    private List<String[]> getinvestigate0() {
        List<String[]> list = new ArrayList<>();
        list.add(select_content);
        list.addAll(dbControl.investigate0Select(content));
        return list;
    }

    private List<Peacetime> getinvestigate0All() {
        String sql = "select p.FullName, p.WorkUnit, p.BirthDay, Date, Effect, Type from investigate0 i left join personal p on i.personalIdCard = p.personalIdCard " +
                "where p.personalIdCard = '" + content + "'";
        return dbControl.PeacetimeSelect(sql);
    }

    @OnClick({R.id.more_back, R.id.more_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more_back:
                finish();
                break;
            case R.id.more_more:
                finish();
                break;
        }
    }
}

package com.hsy.thisdb.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.adapter.PopWindowAdapter;

import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.util
 * @创始人: hsy
 * @创建时间: 2018/12/20 11:04
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/20 11:04
 * @修改描述:
 */
public class HomeHelpUtil {

    private Context context;

    private static HomeHelpUtil helpUtil;
    private PopWindowAdapter popWindowAdapter;
    private PopupWindow popupWindow;
    private HomeHelpUtilClick homeHelpUtilClick;

    public static HomeHelpUtil getInstent(Context context) {
        if (helpUtil == null)
            helpUtil = new HomeHelpUtil(context);
        return helpUtil;
    }

    private HomeHelpUtil(Context context) {
        this.context = context;
        popupWindow = new PopupWindow(context);
    }

    public void HomeTitleClick(View view, String str) {
        if (homeHelpUtilClick != null)
            switch (str) {
                case "所有单位":
                    homeHelpUtilClick.home_sql("所有单位");
                    break;
                case "基本情况":
                    homeHelpUtilClick.home_sql("基本情况");
                    break;
                case "督查信息":
                    homeHelpUtilClick.home_sql("督查信息");
                    break;
                case "分管情况":

                    break;
                case "平时考察":
                    homeHelpUtilClick.home_sql("平时考察");
                    break;
                case "考察情况":
                    showPop(view, TitleUtil.getInvestiData());
                    break;
                case "专项督查":

                    break;
                case "培训信息":

                    break;
                case "编制情况":
                    homeHelpUtilClick.home_sql("编制情况");
                    break;
                case "年龄性别结构":
                    homeHelpUtilClick.home_sql("年龄性别结构");
                    break;
                case "综合查询":
                    homeHelpUtilClick.home_sql("综合查询");
                    break;
                case "日常考察":
                    homeHelpUtilClick.home_sql("日常考察");
                    break;
            }

    }

    private void setUpSql(String str) {
        switch (str) {
            case "平时考察":
                homeHelpUtilClick.home_sql("平时考察");
                break;
            case "更新":
                homeHelpUtilClick.home_sql("更新");
                break;
            case "上传":
                homeHelpUtilClick.home_sql("上传");
                break;
            case "领导班子":
                homeHelpUtilClick.home_sql("领导班子");
                break;
            case "中层干部":
                homeHelpUtilClick.home_sql("中层干部");
                break;
        }
        popupWindow.dismiss();
    }

    public PopWindowAdapter showPop(View btnTools, List<String> stringList) {
        View mainView = LayoutInflater.from(context).inflate(R.layout.popwin, null);
        RecyclerView popwin_cyc = mainView.findViewById(R.id.popwin_cyc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        popwin_cyc.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.custom_divider));
        popwin_cyc.addItemDecoration(decoration);
        popWindowAdapter = new PopWindowAdapter(stringList, context);
        popwin_cyc.setAdapter(popWindowAdapter);
        popWindowAdapter.setOnPopwinListener(new PopWindowAdapter.OnPopwinListener() {
            @Override
            public void popwin(String text) {
                setUpSql(text);
            }
        });
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setContentView(mainView);
        //设置显示隐藏动画
        popupWindow.setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop可点击，为false点击事件无效，默认为true
        popupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        popupWindow.setOutsideTouchable(true);
        //设置默认获取焦点
        popupWindow.setFocusable(true);
        //以某个控件的x和y的偏移量位置开始显示窗口
        popupWindow.showAsDropDown(btnTools, 0, 0);
        return popWindowAdapter;
    }

    public interface HomeHelpUtilClick {
        void home_sql(String str);
    }

    public void setHomeHelpUtilClick(HomeHelpUtilClick homeHelpUtilClick) {
        this.homeHelpUtilClick = homeHelpUtilClick;
    }
}

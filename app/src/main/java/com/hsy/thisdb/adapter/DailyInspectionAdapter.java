package com.hsy.thisdb.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.BasePickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.DailyInspectionData;
import com.hsy.thisdb.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.adapter
 * @创始人: hsy
 * @创建时间: 2019/6/24 11:01
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/6/24 11:01
 * @修改描述:
 */
public class DailyInspectionAdapter extends RecyclerView.Adapter {
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;

    private Context context;
    private List<DailyInspectionData> dataList = new ArrayList<>();
    private Date mStartDate;
    private Date mEndDate;
    private OnClickSubmit onClickSubmit;
    private OnLongClick onLongClick;

    public DailyInspectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if (viewType == TYPE_ONE) {
            view = inflater.inflate(R.layout.daily_inspection_item_one, parent, false);
            return new ViewHolderOne(view);
        } else {
            view = inflater.inflate(R.layout.daily_inspection_item_view, parent, false);
            return new ViewHolderContent(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final DailyInspectionData inspectionData = dataList.get(position);
        if (holder instanceof ViewHolderOne) {
            final ViewHolderOne holderOne = ((ViewHolderOne) holder);
            ((ViewHolderOne) holder).dailySubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickSubmit != null) {
                        String name = holderOne.edDailyName.getText().toString();
                        String start = holderOne.edDailyStart.getText().toString();
                        String end = holderOne.edDailyEnd.getText().toString();
                        onClickSubmit.data(name, start, end);
                    }
                }
            });
            ((ViewHolderOne) holder).edDailyStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getStartDate(holderOne.edDailyStart);
                }
            });
            ((ViewHolderOne) holder).edDailyEnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getEndDate(holderOne.edDailyEnd);
                }
            });
        }
        if (holder instanceof ViewHolderContent) {
            ((ViewHolderContent) holder).mDailyName.setText(inspectionData.getFullname());
            ((ViewHolderContent) holder).mDailyDate.setText(inspectionData.getDate());
            ((ViewHolderContent) holder).mDailyInspection.setText(inspectionData.getDailyInspection());
            ((ViewHolderContent) holder).mDailyReferee.setText(inspectionData.getRecordEvaluate());
            ((ViewHolderContent) holder).mDailyRel.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onLongClick != null) {
                        onLongClick.data(inspectionData);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        private final EditText edDailyName;
        private final TextView edDailyStart;
        private final TextView edDailyEnd;
        private final TextView dailySubmit;

        public ViewHolderOne(View itemView) {
            super(itemView);
            edDailyName = itemView.findViewById(R.id.ed_daily_name);
            edDailyStart = itemView.findViewById(R.id.ed_daily_start);
            edDailyEnd = itemView.findViewById(R.id.ed_daily_end);
            dailySubmit = itemView.findViewById(R.id.daily_submit);
        }
    }

    static class ViewHolderContent extends RecyclerView.ViewHolder {
        private final LinearLayout mDailyRel;
        private final TextView mDailyName;
        private final TextView mDailyDate;
        private final TextView mDailyInspection;
        private final TextView mDailyReferee;

        public ViewHolderContent(View itemView) {
            super(itemView);
            mDailyRel = itemView.findViewById(R.id.personal_rel);
            mDailyName = itemView.findViewById(R.id.daily_fullname);
            mDailyDate = itemView.findViewById(R.id.daily_date);
            mDailyInspection = itemView.findViewById(R.id.daily_inspection);
            mDailyReferee = itemView.findViewById(R.id.daily_referee);
        }
    }

    public void setDataList(List<DailyInspectionData> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public interface OnClickSubmit {
        void data(String name, String start, String end);
    }

    public void setOnClickSubmit(OnClickSubmit onClickSubmit) {
        this.onClickSubmit = onClickSubmit;
    }

    public interface OnLongClick {
        void data(DailyInspectionData inspectionData);
    }

    public void setOnLongClick(OnLongClick onLongClick) {
        this.onLongClick = onLongClick;
    }

    /**
     * 控制开始时间
     */
    private void getStartDate(final TextView view) {
        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (mEndDate != null) {
                    if (date.getTime() > mEndDate.getTime()) {
                        Toast.makeText(context, "开始时间不能晚于结束时间", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mStartDate = date;
                view.setText(DateUtil.getDate2_(date));
            }
        }).isDialog(true).build();
        setDialogBottom(pvTime);
        pvTime.show();
    }

    /**
     * 控制结束时间
     */
    private void getEndDate(final TextView view) {
        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (mStartDate != null) {
                    if (mStartDate.getTime() > date.getTime()) {
                        Toast.makeText(context, "结束时间不能早于开始时间", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mEndDate = date;
                view.setText(DateUtil.getDate2_(date));
            }
        }).isDialog(true).build();
        setDialogBottom(pvTime);
        pvTime.show();
    }

    /**
     * 设置底部弹出日期选择器
     *
     * @param view
     */
    private void setDialogBottom(BasePickerView view) {
        Dialog mDialog = view.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            view.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

}

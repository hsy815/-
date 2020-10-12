package com.hsy.thisdb.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.Personal;

import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb
 * @创始人: hsy
 * @创建时间: 2018/11/20 10:45
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/20 10:45
 * @修改描述:
 */
public class NewPersonalAdapter extends RecyclerView.Adapter {
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;

    private Context context;
    private List<Personal> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public NewPersonalAdapter(Context context, List<Personal> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
            view = inflater.inflate(R.layout.personal_item_view_one, parent, false);
            return new ViewHolderOne(view);
        } else {
            view = inflater.inflate(R.layout.personal_new_item_view, parent, false);
            return new PersonalViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Personal personal = list.get(position);

        if (holder instanceof ViewHolderOne) {
            if (TextUtils.isEmpty(personal.getFullname())) {
                ((ViewHolderOne) holder).mPersonalOneText.setText("基本信息");
            } else {
                ((ViewHolderOne) holder).mPersonalOneText.setText(("基本信息(" + personal.getFullname() + ")"));
            }
        }
        if (holder instanceof PersonalViewHolder) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            if (position == 1) {
                layoutParams.height = 100;
                ((PersonalViewHolder) holder).mPersonalRel.setLayoutParams(layoutParams);
                ((PersonalViewHolder) holder).mPersonalId.setText("");
                ((PersonalViewHolder) holder).mPersonalWorkUnit.setText((personal.getWorkUnit() + personal.getSectionName() + personal.getDuty()));
                ((PersonalViewHolder) holder).mPersonalFullname.setGravity(Gravity.CENTER);
                ((PersonalViewHolder) holder).mPersonalWorkUnit.setGravity(Gravity.CENTER);
            } else {
                ((PersonalViewHolder) holder).mPersonalRel.setLayoutParams(layoutParams);
                ((PersonalViewHolder) holder).mPersonalRel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null)
                            onItemClickListener.Item(position, personal);
                    }
                });
                ((PersonalViewHolder) holder).mPersonalRel.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (onItemLongClickListener != null)
                            onItemLongClickListener.Item(position, personal);
                        return true;
                    }
                });
                ((PersonalViewHolder) holder).mPersonalId.setText(((position - 1) + ""));
                ((PersonalViewHolder) holder).mPersonalWorkUnit.setText((personal.getWorkUnit() + " " + personal.getSectionName() + personal.getDuty()));
                ((PersonalViewHolder) holder).mPersonalFullname.setGravity(Gravity.CENTER_VERTICAL);
                ((PersonalViewHolder) holder).mPersonalWorkUnit.setGravity(Gravity.CENTER_VERTICAL);
            }

            ((PersonalViewHolder) holder).mPersonalFullname.setText(personal.getFullname());
            ((PersonalViewHolder) holder).mPersonalSex.setText(personal.getGender());
            ((PersonalViewHolder) holder).mPersonalDate.setText(personal.getBirthday());
            ((PersonalViewHolder) holder).mPersonalFullmajor.setText(personal.getFullMajor());
            ((PersonalViewHolder) holder).mPersonalFullbackground.setText((personal.getFullBackgroundDegree().replace("<br/>", "\n")));
            ((PersonalViewHolder) holder).mPersonalSchool.setText(personal.getGoOnSchoolMajor());
            ((PersonalViewHolder) holder).mPersonalSchoolbackground.setText((personal.getGoOnBackgroundDegree().replace("<br/>", "\n")));
            ((PersonalViewHolder) holder).mDeptTime.setText(personal.getCurrentDeptStartTime());
            ((PersonalViewHolder) holder).mDutyTime.setText(personal.getCurrentDutyStartTime());
            ((PersonalViewHolder) holder).mWorkDate.setText(personal.getWorkDate());
            ((PersonalViewHolder) holder).mPoliticsStatus.setText(personal.getPoliticsStatus());

            ((PersonalViewHolder) holder).mPersonalRel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.Item(position, personal);
                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        private TextView mPersonalOneText;

        public ViewHolderOne(View itemView) {
            super(itemView);
            mPersonalOneText = itemView.findViewById(R.id.more_item_one_text);
        }
    }

    public static class PersonalViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mPersonalRel;
        private TextView mPersonalId;
        private TextView mPersonalFullname;
        private TextView mPersonalWorkUnit;
        private TextView mPersonalSex;
        private TextView mPersonalDate;
        private TextView mPersonalFullmajor;
        private TextView mPersonalFullbackground;
        private TextView mPersonalSchool;
        private TextView mPersonalSchoolbackground;
        private TextView mDeptTime;
        private TextView mDutyTime;
        private TextView mWorkDate;
        private TextView mPoliticsStatus;

        public PersonalViewHolder(View itemView) {
            super(itemView);
            mPersonalRel = itemView.findViewById(R.id.personal_rel);
            mPersonalId = itemView.findViewById(R.id.personal_id);
            mPersonalFullname = itemView.findViewById(R.id.personal_fullname);
            mPersonalWorkUnit = itemView.findViewById(R.id.personal_work_unit);
            mPersonalSex = itemView.findViewById(R.id.personal_sex);
            mPersonalDate = itemView.findViewById(R.id.personal_date);
            mPersonalFullmajor = itemView.findViewById(R.id.personal_fullmajor);
            mPersonalFullbackground = itemView.findViewById(R.id.personal_fullbackground);
            mPersonalSchool = itemView.findViewById(R.id.personal_school);
            mPersonalSchoolbackground = itemView.findViewById(R.id.personal_schoolbackground);
            mDeptTime = itemView.findViewById(R.id.dept_time);
            mDutyTime = itemView.findViewById(R.id.duty_time);
            mWorkDate = itemView.findViewById(R.id.work_date);
            mPoliticsStatus = itemView.findViewById(R.id.politics_status);
        }
    }

    public interface OnItemClickListener {
        void Item(int i, Personal personal);
    }

    public interface OnItemLongClickListener {
        void Item(int i, Personal personal);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}

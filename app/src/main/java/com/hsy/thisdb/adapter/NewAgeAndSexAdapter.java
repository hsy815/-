package com.hsy.thisdb.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.AgeAndGender;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.adapter
 * @创始人: hsy
 * @创建时间: 2018/11/27 16:25
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/27 16:25
 * @修改描述:
 */
public class NewAgeAndSexAdapter extends RecyclerView.Adapter {
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;

    private Context context;
    private List<AgeAndGender> genderList = new ArrayList<>();
    private LayoutInflater inflater;
    private OnAgeSexItemClick onAgeSexItemClick;

    public NewAgeAndSexAdapter(Context context) {
        this.context = context;
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
            view = inflater.inflate(R.layout.age_item_view_one, parent, false);
            return new ViewHolderOne(view);
        } else {
            view = inflater.inflate(R.layout.age_item_view_two, parent, false);
            return new ViewHolderTwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final AgeAndGender ageAndGender = genderList.get(position);
        if (holder instanceof ViewHolderOne) {

        }
        if (holder instanceof ViewHolderTwo) {

            ((ViewHolderTwo) holder).mAgeLin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAgeSexItemClick != null)
                        onAgeSexItemClick.ageItem(ageAndGender);
                }
            });

            ((ViewHolderTwo) holder).mAgeId.setText((position + ""));
            ((ViewHolderTwo) holder).mAgeWork.setText(ageAndGender.getWorkUnit());
            ((ViewHolderTwo) holder).mAge35.setText((ageAndGender.getUnder35() + ""));
            ((ViewHolderTwo) holder).mAge45.setText((ageAndGender.getBetween36_45() + ""));
            ((ViewHolderTwo) holder).mAge55.setText((ageAndGender.getBetween46_55() + ""));
            ((ViewHolderTwo) holder).mAge56.setText((ageAndGender.getUp56() + ""));
            ((ViewHolderTwo) holder).mAgeAverage.setText((ageAndGender.getAgeAvgValue() + ""));
            ((ViewHolderTwo) holder).mAgeNan.setText((ageAndGender.getMan() + ""));
            ((ViewHolderTwo) holder).mAgeNv.setText((ageAndGender.getWomen() + ""));
        }

    }

    @Override
    public int getItemCount() {
        return genderList.size();
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public ViewHolderOne(View itemView) {
            super(itemView);
        }
    }

    static class ViewHolderTwo extends RecyclerView.ViewHolder {
        private final LinearLayout mAgeLin;
        private final TextView mAgeId;
        private final TextView mAgeWork;
        private final TextView mAge35;
        private final TextView mAge45;
        private final TextView mAge55;
        private final TextView mAge56;
        private final TextView mAgeAverage;
        private final TextView mAgeNan;
        private final TextView mAgeNv;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            mAgeLin = itemView.findViewById(R.id.age_item_view_lin);
            mAgeId = itemView.findViewById(R.id.age_item_view_id);
            mAgeWork = itemView.findViewById(R.id.age_item_view_work);
            mAge35 = itemView.findViewById(R.id.age_item_view_35);
            mAge45 = itemView.findViewById(R.id.age_item_view_45);
            mAge55 = itemView.findViewById(R.id.age_item_view_55);
            mAge56 = itemView.findViewById(R.id.age_item_view_56);
            mAgeAverage = itemView.findViewById(R.id.age_item_view_Average);
            mAgeNan = itemView.findViewById(R.id.age_item_view_nan);
            mAgeNv = itemView.findViewById(R.id.age_item_view_nv);
        }
    }

    public void setGenderList(List<AgeAndGender> genderList) {
        this.genderList = genderList;
    }

    public void setOnAgeSexItemClick(OnAgeSexItemClick onAgeSexItemClick) {
        this.onAgeSexItemClick = onAgeSexItemClick;
    }

    public interface OnAgeSexItemClick {
        void ageItem(AgeAndGender ageAndGender);
    }
}

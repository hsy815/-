package com.hsy.thisdb.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.Peacetime;

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
public class MoreAdapter extends RecyclerView.Adapter {
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;

    private Context context;
    private List<Peacetime> peacetimeList = new ArrayList<>();
    private LayoutInflater inflater;
    private OnMoreItemClick onMoreItemClick;

    public MoreAdapter(Context context) {
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
            view = inflater.inflate(R.layout.more_item_view_one, parent, false);
            return new ViewHolderOne(view);
        } else {
            view = inflater.inflate(R.layout.more_item_view, parent, false);
            return new ViewHolderTwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Peacetime peacetime = peacetimeList.get(position);
        if (holder instanceof ViewHolderOne) {
            if (TextUtils.isEmpty(peacetime.getFullname())) {
                ((ViewHolderOne) holder).more_item_one_text.setText("平时考察");
            } else {
                ((ViewHolderOne) holder).more_item_one_text.setText("平时考察(" + peacetime.getFullname() + ")");
            }
        }
        if (holder instanceof ViewHolderTwo) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            if (position == 1) {
                layoutParams.height = 100;
                ((ViewHolderTwo) holder).mMoreLin.setLayoutParams(layoutParams);
                ((ViewHolderTwo) holder).mMoreId.setText("");
                ((ViewHolderTwo) holder).mMoreWork.setGravity(Gravity.CENTER);
                ((ViewHolderTwo) holder).mMoreEffect.setGravity(Gravity.CENTER);
            } else {
                ((ViewHolderTwo) holder).mMoreLin.setLayoutParams(layoutParams);
                ((ViewHolderTwo) holder).mMoreLin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onMoreItemClick != null)
                            onMoreItemClick.moreItem(peacetime);
                    }
                });
                ((ViewHolderTwo) holder).mMoreId.setText((position - 1) + "");
                ((ViewHolderTwo) holder).mMoreWork.setGravity(Gravity.CENTER_VERTICAL);
                ((ViewHolderTwo) holder).mMoreEffect.setGravity(Gravity.CENTER_VERTICAL);
            }
            ((ViewHolderTwo) holder).mMoreName.setText(peacetime.getFullname());
            ((ViewHolderTwo) holder).mMoreWork.setText(peacetime.getWorkUnit());
            ((ViewHolderTwo) holder).mMoreCard.setText(peacetime.getBirthday());
            ((ViewHolderTwo) holder).mMoreSex.setText(peacetime.getGender());
            ((ViewHolderTwo) holder).mMoreDate.setText(peacetime.getDate());
            ((ViewHolderTwo) holder).mMoreEffect.setText(peacetime.getEffect());
            ((ViewHolderTwo) holder).mMoreType.setText(peacetime.getType());
            ((ViewHolderTwo) holder).mMoreEvaluate.setText(peacetime.getEvaluation());
        }

    }

    @Override
    public int getItemCount() {
        return peacetimeList.size();
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        private final TextView more_item_one_text;

        public ViewHolderOne(View itemView) {
            super(itemView);
            more_item_one_text = itemView.findViewById(R.id.more_item_one_text);
        }
    }

    static class ViewHolderTwo extends RecyclerView.ViewHolder {
        private final LinearLayout mMoreLin;
        private final TextView mMoreId;
        private final TextView mMoreName;
        private final TextView mMoreCard;
        private final TextView mMoreWork;
        private final TextView mMoreSex;
        private final TextView mMoreDate;
        private final TextView mMoreEffect;
        private final TextView mMoreType;
        private final TextView mMoreEvaluate;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            mMoreLin = itemView.findViewById(R.id.more_item_lin);
            mMoreId = itemView.findViewById(R.id.more_item_id);
            mMoreName = itemView.findViewById(R.id.more_item_name);
            mMoreCard = itemView.findViewById(R.id.more_item_card);
            mMoreSex = itemView.findViewById(R.id.more_item_sex);
            mMoreWork = itemView.findViewById(R.id.more_item_work);
            mMoreDate = itemView.findViewById(R.id.more_item_date);
            mMoreEffect = itemView.findViewById(R.id.more_item_effect);
            mMoreType = itemView.findViewById(R.id.more_item_type);
            mMoreEvaluate = itemView.findViewById(R.id.more_item_evaluate);
        }
    }

    public void setPeacetimeList(List<Peacetime> peacetimeList) {
        this.peacetimeList = peacetimeList;
        notifyDataSetChanged();
    }

    public void setOnMoreItemClick(OnMoreItemClick onMoreItemClick) {
        this.onMoreItemClick = onMoreItemClick;
    }

    public interface OnMoreItemClick {
        void moreItem(Peacetime peacetime);
    }
}

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
import com.hsy.thisdb.eitity.Inspection;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.adapter
 * @创始人: hsy
 * @创建时间: 2019/2/14 9:42
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/2/14 9:42
 * @修改描述:
 */
public class InspectionAdapter extends RecyclerView.Adapter {
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;

    private Context context;
    private List<Inspection> inspectionList = new ArrayList<>();
    private OnInspectionItemClick onInspectionItemClick;

    public InspectionAdapter(Context context) {
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
            view = inflater.inflate(R.layout.inspection_item_view_one, parent, false);
            return new ViewHolderOne(view);
        } else {
            view = inflater.inflate(R.layout.inspection_item_view, parent, false);
            return new ViewHolderTwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Inspection inspection = inspectionList.get(position);
        if (holder instanceof ViewHolderOne) {
            if (TextUtils.isEmpty(inspection.getFullName())) {
                ((ViewHolderOne) holder).more_item_one_text.setText("督查信息");
            } else {
                ((ViewHolderOne) holder).more_item_one_text.setText("督查信息(" + inspection.getFullName() + ")");
            }
        }
        if (holder instanceof ViewHolderTwo) {
            if (position == 1) {
                ((ViewHolderTwo) holder).institutionsItemId.setText("");
                ((ViewHolderTwo) holder).institutionsItemName.setGravity(Gravity.CENTER);
                ((ViewHolderTwo) holder).institutionsItemWork.setGravity(Gravity.CENTER);
                ((ViewHolderTwo) holder).institutionsItemDate.setGravity(Gravity.CENTER);
                ((ViewHolderTwo) holder).institutionsItemHouse.setGravity(Gravity.CENTER);
                ((ViewHolderTwo) holder).institutionsItemStock.setGravity(Gravity.CENTER);
                ((ViewHolderTwo) holder).institutionsItemCompany.setGravity(Gravity.CENTER);
                ((ViewHolderTwo) holder).institutionsItemContent.setGravity(Gravity.CENTER);
                ((ViewHolderTwo) holder).institutionsItemPetition.setGravity(Gravity.CENTER);
            } else {
                ((ViewHolderTwo) holder).institutionsItemId.setText(((position - 1) + ""));
                ((ViewHolderTwo) holder).institutionsItemName.setGravity(Gravity.CENTER_VERTICAL);
                ((ViewHolderTwo) holder).institutionsItemWork.setGravity(Gravity.CENTER_VERTICAL);
                ((ViewHolderTwo) holder).institutionsItemDate.setGravity(Gravity.CENTER_VERTICAL);
                ((ViewHolderTwo) holder).institutionsItemHouse.setGravity(Gravity.CENTER_VERTICAL);
                ((ViewHolderTwo) holder).institutionsItemStock.setGravity(Gravity.CENTER_VERTICAL);
                ((ViewHolderTwo) holder).institutionsItemCompany.setGravity(Gravity.CENTER_VERTICAL);
                ((ViewHolderTwo) holder).institutionsItemContent.setGravity(Gravity.CENTER_VERTICAL);
                ((ViewHolderTwo) holder).institutionsItemPetition.setGravity(Gravity.CENTER_VERTICAL);
                ((ViewHolderTwo) holder).institutionsItemLin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onInspectionItemClick != null)
                            onInspectionItemClick.InspectionItem(inspection);
                    }
                });
            }
            ((ViewHolderTwo) holder).institutionsItemName.setText(inspection.getFullName());
            ((ViewHolderTwo) holder).institutionsItemWork.setText(inspection.getWorkUnit());
            ((ViewHolderTwo) holder).institutionsItemDate.setText(inspection.getDate());
            ((ViewHolderTwo) holder).institutionsItemHouse.setText(inspection.getHouse().replace("<br/>", "\n"));
            ((ViewHolderTwo) holder).institutionsItemStock.setText(inspection.getStock());
            ((ViewHolderTwo) holder).institutionsItemCompany.setText(inspection.getCompany());
            ((ViewHolderTwo) holder).institutionsItemContent.setText(inspection.getContent());
            ((ViewHolderTwo) holder).institutionsItemPetition.setText(inspection.getPetition());

        }

    }

    @Override
    public int getItemCount() {
        return inspectionList.size();
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        private final TextView more_item_one_text;

        public ViewHolderOne(View itemView) {
            super(itemView);
            more_item_one_text = itemView.findViewById(R.id.more_item_one_text);
        }
    }

    static class ViewHolderTwo extends RecyclerView.ViewHolder {
        private final LinearLayout institutionsItemLin;
        private final TextView institutionsItemId;
        private final TextView institutionsItemName;
        private final TextView institutionsItemWork;
        private final TextView institutionsItemDate;
        private final TextView institutionsItemHouse;
        private final TextView institutionsItemStock;
        private final TextView institutionsItemCompany;
        private final TextView institutionsItemContent;
        private final TextView institutionsItemPetition;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            institutionsItemLin = itemView.findViewById(R.id.inspection_item_lin);
            institutionsItemId = itemView.findViewById(R.id.inspection_item_id);
            institutionsItemName = itemView.findViewById(R.id.inspection_item_name);
            institutionsItemWork = itemView.findViewById(R.id.inspection_item_work);
            institutionsItemDate = itemView.findViewById(R.id.inspection_item_date);
            institutionsItemHouse = itemView.findViewById(R.id.inspection_item_house);
            institutionsItemStock = itemView.findViewById(R.id.inspection_item_stock);
            institutionsItemCompany = itemView.findViewById(R.id.inspection_item_company);
            institutionsItemContent = itemView.findViewById(R.id.inspection_item_content);
            institutionsItemPetition = itemView.findViewById(R.id.inspection_item_petition);
        }
    }

    public void setInspectionList(List<Inspection> inspectionList) {
        this.inspectionList = inspectionList;
    }

    public interface OnInspectionItemClick {
        void InspectionItem(Inspection inspection);
    }

    public void setOnInspectionItemClick(OnInspectionItemClick onInspectionItemClick) {
        this.onInspectionItemClick = onInspectionItemClick;
    }
}

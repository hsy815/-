package com.hsy.thisdb.adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.thisdb.R;
import com.hsy.thisdb.eitity.AgeAndGender;
import com.hsy.thisdb.eitity.Institutions;

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
public class NewInstitutionsAdapter extends RecyclerView.Adapter {
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;

    private Context context;
    private List<Institutions> institutionsList = new ArrayList<>();
    private LayoutInflater inflater;
    private OnInstitutionsItemClick onInstitutionsItemClick;

    public NewInstitutionsAdapter(Context context) {
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
            view = inflater.inflate(R.layout.institutions_item_view_one, parent, false);
            return new ViewHolderOne(view);
        } else {
            view = inflater.inflate(R.layout.institutions_item_view, parent, false);
            return new ViewHolderTwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Institutions institutions = institutionsList.get(position);
        if (holder instanceof ViewHolderOne) {

        }
        if (holder instanceof ViewHolderTwo) {
            ((ViewHolderTwo) holder).institutionsItemId.setText((position + ""));
            ((ViewHolderTwo) holder).institutionsItemWork.setText(institutions.getWorkUnit());
            ((ViewHolderTwo) holder).institutionsItemZZB.setText(institutions.getHdLeadership());
            ((ViewHolderTwo) holder).institutionsItemFZB.setText(institutions.getHdLeaderDeputy());
            ((ViewHolderTwo) holder).institutionsItemZCB.setText(institutions.getHdMiddleLvel());
            ((ViewHolderTwo) holder).institutionsItemZZR.setText(institutions.getSjLeadership());
            ((ViewHolderTwo) holder).institutionsItemFCR.setText(institutions.getSjLeaderDeputy());
            ((ViewHolderTwo) holder).institutionsItemZCR.setText(institutions.getSjMiddleLvel());

            setStyle(institutions.getIsSign(), ((ViewHolderTwo) holder).institutionsItemZZC, ((ViewHolderTwo) holder).institutionsItemZZCB, institutions.getLs() + "");
            setStyle(institutions.getIdSign(), ((ViewHolderTwo) holder).institutionsItemFZC, ((ViewHolderTwo) holder).institutionsItemFZCB, institutions.getLd() + "");
            setStyle(institutions.getMlSign(), ((ViewHolderTwo) holder).institutionsItemZCC, ((ViewHolderTwo) holder).institutionsItemZCCB, institutions.getMl() + "");

        }

    }

    @Override
    public int getItemCount() {
        return institutionsList.size();
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public ViewHolderOne(View itemView) {
            super(itemView);
        }
    }

    static class ViewHolderTwo extends RecyclerView.ViewHolder {
        private final TextView institutionsItemId;
        private final TextView institutionsItemWork;
        private final TextView institutionsItemZZB;
        private final TextView institutionsItemFZB;
        private final TextView institutionsItemZCB;
        private final TextView institutionsItemZZR;
        private final TextView institutionsItemFCR;
        private final TextView institutionsItemZCR;
        private final TextView institutionsItemZZC;
        private final View institutionsItemZZCB;
        private final TextView institutionsItemFZC;
        private final View institutionsItemFZCB;
        private final TextView institutionsItemZCC;
        private final View institutionsItemZCCB;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            institutionsItemId = itemView.findViewById(R.id.institutions_item_id);
            institutionsItemWork = itemView.findViewById(R.id.institutions_item_work);
            institutionsItemZZB = itemView.findViewById(R.id.institutions_item_zzb);
            institutionsItemFZB = itemView.findViewById(R.id.institutions_item_fzb);
            institutionsItemZCB = itemView.findViewById(R.id.institutions_item_zcb);
            institutionsItemZZR = itemView.findViewById(R.id.institutions_item_zzr);
            institutionsItemFCR = itemView.findViewById(R.id.institutions_item_fzr);
            institutionsItemZCR = itemView.findViewById(R.id.institutions_item_zcr);
            institutionsItemZZC = itemView.findViewById(R.id.institutions_item_zzc);
            institutionsItemZZCB = itemView.findViewById(R.id.institutions_item_zzcb);
            institutionsItemFZC = itemView.findViewById(R.id.institutions_item_fzc);
            institutionsItemFZCB = itemView.findViewById(R.id.institutions_item_fzcb);
            institutionsItemZCC = itemView.findViewById(R.id.institutions_item_zcc);
            institutionsItemZCCB = itemView.findViewById(R.id.institutions_item_zccb);
        }
    }

    public void setInstitutionsList(List<Institutions> institutionsList) {
        this.institutionsList = institutionsList;
    }

    public void setOnInstitutionsItemClick(OnInstitutionsItemClick onInstitutionsItemClick) {
        this.onInstitutionsItemClick = onInstitutionsItemClick;
    }

    public interface OnInstitutionsItemClick {
        void InstitutionsItem(AgeAndGender ageAndGender);
    }

    private void setStyle(int a, TextView textView, View view, String content) {
        switch (a) {
            case 0:
                textView.setText(content);
                textView.setTextColor(Color.BLACK);
                view.setBackgroundColor(Color.WHITE);
                break;
            case 1:
                textView.setText(content);
                textView.setTextColor(Color.RED);
                view.setBackgroundColor(Color.GRAY);
                break;
            case -1:
                textView.setText("-" + content);
                textView.setTextColor(Color.WHITE);
                view.setBackgroundColor(Color.BLUE);
                break;
        }
    }
}

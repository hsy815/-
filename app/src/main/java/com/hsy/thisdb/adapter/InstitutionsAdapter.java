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
import com.hsy.thisdb.eitity.Institutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.adapter
 * @创始人: hsy
 * @创建时间: 2018/11/28 10:26
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/28 10:26
 * @修改描述:
 */
public class InstitutionsAdapter extends RecyclerView.Adapter<InstitutionsAdapter.ViewHolder> {

    private Context context;
    private List<Institutions> institutionsList = new ArrayList<>();
    private LayoutInflater inflater;

    public InstitutionsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.institutions_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Institutions institutions = institutionsList.get(position);
        holder.institutionsItemWork.setText(institutions.getWorkUnit());
        holder.institutionsItemZZB.setText(institutions.getHdLeadership());
        holder.institutionsItemFZB.setText(institutions.getHdLeaderDeputy());
        holder.institutionsItemZCB.setText(institutions.getHdMiddleLvel());
        holder.institutionsItemZZR.setText(institutions.getSjLeadership());
        holder.institutionsItemFCR.setText(institutions.getSjLeaderDeputy());
        holder.institutionsItemZCR.setText(institutions.getSjMiddleLvel());

        setStyle(institutions.getIsSign(), holder.institutionsItemZZC, holder.institutionsItemZZCB, institutions.getLs() + "");
        setStyle(institutions.getIdSign(), holder.institutionsItemFZC, holder.institutionsItemFZCB, institutions.getLd() + "");
        setStyle(institutions.getMlSign(), holder.institutionsItemZCC, holder.institutionsItemZCCB, institutions.getMl() + "");
    }

    @Override
    public int getItemCount() {
        return institutionsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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

        public ViewHolder(View itemView) {
            super(itemView);
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
        notifyDataSetChanged();
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

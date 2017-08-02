package org.collapsed.ssuparty_android.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.NewPartyInfo;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{

    private List<NewPartyInfo> mNewPartyInfoList;
    private Context mContext;

    public RVAdapter(List<NewPartyInfo> items, Context context) {
        mNewPartyInfoList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partylist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        NewPartyInfo item = mNewPartyInfoList.get(position);
        viewHolder.textTitle.setText(item.getTitle());
        viewHolder.textMember.setText(item.getMemberNum());
        viewHolder.itemView.setTag(item);

    }

    @Override
    public int getItemCount() {
        return this.mNewPartyInfoList.size();
    }

    public void addItem(List<NewPartyInfo> newPartyInfoList){
        mNewPartyInfoList = newPartyInfoList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textTitle;
        public TextView textMember;

        public ViewHolder(View itemView){
            super(itemView);

            textTitle = itemView.findViewById(R.id.partylist_title_txt);
            textMember = itemView.findViewById(R.id.partylist_member_num_txt);
        }

    }
}

package org.collapsed.ssuparty_android.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.Party;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{

    private List<Party> mPartyList;
    private Context mContext;

    public RVAdapter(List<Party> items, Context context) {
        mPartyList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Party item = mPartyList.get(position);
        viewHolder.textTitle.setText(item.getTitle());
        viewHolder.textMember.setText(item.getMemberNum());
        viewHolder.img.setBackgroundResource(item.getImage());
        viewHolder.itemView.setTag(item);

    }

    @Override
    public int getItemCount() {
        return this.mPartyList.size();
    }

    public void addItem(List<Party> partyList){
        mPartyList = partyList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView textTitle;
        public TextView textMember;

        public ViewHolder(View itemView){
            super(itemView);

            img = itemView.findViewById(R.id.partylist_main_image_img);
            textTitle = itemView.findViewById(R.id.partylist_title_txt);
            textMember = itemView.findViewById(R.id.partylist_member_num_txt);
        }

    }
}

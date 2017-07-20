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
    private int lastPosition = -1;

    public RVAdapter(List<Party> items, Context context) {
        mPartyList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
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
        return mPartyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView textTitle;
        public TextView textMember;

        public ViewHolder(View itemView){
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.partyImg);
            textTitle = (TextView) itemView.findViewById(R.id.partyTitle);
            textMember = (TextView) itemView.findViewById(R.id.partyMember);
        }

    }
}

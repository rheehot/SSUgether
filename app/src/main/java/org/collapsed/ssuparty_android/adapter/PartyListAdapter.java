package org.collapsed.ssuparty_android.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.PartyData;

import java.util.List;

public class PartyListAdapter extends RecyclerView.Adapter<PartyListAdapter.ViewHolder> {

    private List<PartyData> mPartyDataList;

    private Context mContext;

    public PartyListAdapter(List<PartyData> items, Context context) {
        mPartyDataList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_party, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        PartyData item = mPartyDataList.get(position);

        viewHolder.titleText.setText(item.getTitle());
        viewHolder.categoryText.setText(item.getCategory());
        viewHolder.deadlineText.setText(item.getDeadline());
        viewHolder.memberText.setText("0/" + item.getMemberNum());

        List<String> tagList = item.getTags();

        if (tagList == null) {
            viewHolder.tagText.setText("");
        } else {
            String tagText = "";

            for (String tag : tagList) {
                tagText += "#" + tag + " ";
                viewHolder.tagText.setText(tagText);
            }
        }

        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, position + " yes!!!!!!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.itemView.setTag(item);

    }

    @Override
    public int getItemCount() {
        return this.mPartyDataList.size();
    }

    public void addItem(List<PartyData> partyDataList) {
        mPartyDataList = partyDataList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText, memberText, categoryText, deadlineText, tagText;
        public LinearLayout rootView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.party_item_title_txt);
            memberText = itemView.findViewById(R.id.party_item_member_num_txt);
            categoryText = itemView.findViewById(R.id.party_item_category_txt);
            deadlineText = itemView.findViewById(R.id.party_item_deadline_txt);
            tagText = itemView.findViewById(R.id.party_item_tag_txt);

            rootView = itemView.findViewById(R.id.party_item_root_layout);
        }

    }
}

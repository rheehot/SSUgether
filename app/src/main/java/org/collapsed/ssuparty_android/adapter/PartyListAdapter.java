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
import org.collapsed.ssuparty_android.model.NewPartyInfo;

import java.util.List;

public class PartyListAdapter extends RecyclerView.Adapter<PartyListAdapter.ViewHolder> {

    private List<NewPartyInfo> mNewPartyInfoList;

    private Context mContext;

    public PartyListAdapter(List<NewPartyInfo> items, Context context) {
        mNewPartyInfoList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partylist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        NewPartyInfo item = mNewPartyInfoList.get(position);

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

        viewHolder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, position + " yes!!!!!!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.itemView.setTag(item);

    }

    @Override
    public int getItemCount() {
        return this.mNewPartyInfoList.size();
    }

    public void addItem(List<NewPartyInfo> newPartyInfoList) {
        mNewPartyInfoList = newPartyInfoList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText, memberText, categoryText, deadlineText, tagText;
        public LinearLayout mRootView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.partylist_title_txt);
            memberText = itemView.findViewById(R.id.partylist_member_num_txt);
            categoryText = itemView.findViewById(R.id.partylist_category);
            deadlineText = itemView.findViewById(R.id.partylist_deadline_txt);
            tagText = itemView.findViewById(R.id.partylist_tag_txt);

            mRootView = itemView.findViewById(R.id.item_root_layout);
        }

    }
}

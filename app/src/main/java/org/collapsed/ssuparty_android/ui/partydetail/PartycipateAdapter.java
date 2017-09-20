package org.collapsed.ssuparty_android.ui.partydetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;
import org.collapsed.ssuparty_android.ui.customview.CircleImageView;
import org.collapsed.ssuparty_android.utils.ImageUtil;

import java.util.ArrayList;

public class PartycipateAdapter extends RecyclerView.Adapter<PartycipateAdapter.ViewHolder> {

    private ArrayList<UserInfoData> mMembers;
    private PartyData mPartyData;
    private Context mContext;
    private int mParticipateNum;
    private PartyDetailPresenter mPresenter;

    public PartycipateAdapter(Context context, PartyDetailPresenter presenter, PartyData partyData, int participateNum) {
        this.mMembers = new ArrayList<>();
        this.mPartyData = partyData;
        this.mContext = context;
        this.mParticipateNum = participateNum;
        this.mPresenter = presenter;
    }

    @Override
    public PartycipateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participant, null);
        PartycipateAdapter.ViewHolder holder = new PartycipateAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ImageUtil.loadUrlImage(viewHolder.profileImage, mMembers.get(i).getImgUrl());
        viewHolder.layout.setOnClickListener(view -> {
            UserInfoData data = mMembers.get(i);
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            String[] address = {data.getEmail()};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SSUgether를 통해서 " + data.getName() + "님께 연락드립니다.");
            mContext.startActivity(emailIntent);
        });

        viewHolder.nameText.setText(mMembers.get(i).getName());
        viewHolder.emailText.setText(mMembers.get(i).getEmail());
        viewHolder.allowBtn.setOnClickListener(v -> {
            mPresenter.allowJoinParty(mPartyData, mMembers.get(i));
        });
        viewHolder.denyBtn.setOnClickListener(v -> {
            mPresenter.denyJoinParty(mPartyData, mMembers.get(i));
        });

        if (mPartyData.getFounder().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            if (i < mParticipateNum) {
                viewHolder.allowBtn.setVisibility(View.GONE);
                viewHolder.denyBtn.setVisibility(View.GONE);
            } else {
                viewHolder.allowBtn.setVisibility(View.VISIBLE);
                viewHolder.denyBtn.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    public void addItem(UserInfoData info) {
        mMembers.add(info);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout layout;
        public CircleImageView profileImage;
        public TextView emailText;
        public TextView nameText;
        public Button allowBtn;
        public Button denyBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_participant);
            profileImage = itemView.findViewById(R.id.item_participant_profile_img);
            nameText = itemView.findViewById(R.id.item_participant_name_txt);
            emailText = itemView.findViewById(R.id.item_participant_email_txt);
            allowBtn = itemView.findViewById(R.id.item_participant_consent_btn);
            denyBtn = itemView.findViewById(R.id.item_participant_deny_btn);
        }
    }
}

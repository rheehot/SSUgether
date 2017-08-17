package org.collapsed.ssuparty_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.widget.ProfilePictureView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.ProfileData;

import java.util.List;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ViewHolder> {

    private List<ProfileData> mProfileList;

    private Context mContext;

    public ProfileListAdapter(List<ProfileData> items, Context context) {
        mProfileList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileListAdapter.ViewHolder viewHolder, final int position) {

        ProfileData item = mProfileList.get(position);

        Glide.with(mContext).load(item.getProfileImageId()).into(viewHolder.profileImgae);
        viewHolder.nicknameText.setText(item.getNickname());
        viewHolder.educationText.setText(item.getEducation());
        viewHolder.carrerText.setText(item.getCarrer());

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
        return this.mProfileList.size();
    }

    public void addItem(List<ProfileData> profileDataList) {
        mProfileList = profileDataList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nicknameText, educationText, carrerText;
        public ImageView profileImgae;
        public LinearLayout rootView;

        public ViewHolder(View itemView) {
            super(itemView);

            nicknameText = itemView.findViewById(R.id.profile_item_nickname_txt);
            educationText = itemView.findViewById(R.id.profile_item_education_txt);
            carrerText = itemView.findViewById(R.id.profile_item_carrer_txt);
            profileImgae = itemView.findViewById(R.id.profile_item_image);

            rootView = itemView.findViewById(R.id.profile_item_root_layout);
        }

    }
}


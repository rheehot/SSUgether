package org.collapsed.ssuparty_android.ui.partydetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.collapsed.ssuparty_android.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;

public class PartyDetailActivity extends AppCompatActivity {

    @BindView(R.id.party_detail_tag_layout)
    TagGroup mTagLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        List<String> tagList = new ArrayList<>();
        tagList.add("IT");
        tagList.add("컴퓨터학부");
        tagList.add("소공전");
        mTagLayout.setTags(tagList);
    }
}

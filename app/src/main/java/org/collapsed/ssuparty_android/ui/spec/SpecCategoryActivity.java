package org.collapsed.ssuparty_android.ui.spec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.collapsed.ssuparty_android.AppConfig;
import org.collapsed.ssuparty_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_ABILITY;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_CONTEST;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_INTERN;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_LANGUAGE;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_MYSPEC;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_PRIZE;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_PROJECT;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_SUBJECT;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_SUPPORT;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_THESIS;
import static org.collapsed.ssuparty_android.AppConfig.INDEX_SPEC_VOLUNTEER;


public class SpecCategoryActivity extends AppCompatActivity {

    @BindView(R.id.spec_subject_layout)
    View mSubjectLayout;
    @BindView(R.id.spec_project_layout)
    View mProjectLayout;
    @BindView(R.id.spec_contest_layout)
    View mContestLayout;
    @BindView(R.id.spec_intern_layout)
    View mInternLayout;
    @BindView(R.id.spec_support_layout)
    View mSupportLayout;
    @BindView(R.id.spec_volunteer_layout)
    View mVolunteerLayout;
    @BindView(R.id.spec_prize_layout)
    View mPrizeLayout;
    @BindView(R.id.spec_thesis_layout)
    View mThesisLayout;
    @BindView(R.id.spec_language_layout)
    View mLanguageLayout;
    @BindView(R.id.spec_ability_layout)
    View mAbilityLayout;
    @BindView(R.id.spec_myspec_layout)
    View mMyspecLayout;
    @BindView(R.id.spec_close_btn)
    ImageButton mCloseButton;

    private TextView mSubjectTitle, mProjectTitle, mContestTitle, mInternTitle, mSupportTitle, mMyspecTitle;
    private TextView mVolunteerTitle, mPrizeTitle, mThesisTitle, mLanguageTitle, mAbilityTitle;
    private TextView mSubjectContent, mProjectContent, mContestContent, mInternContent, mSupportContent, mMyspecContent;
    private TextView mVolunteerContent, mPrizeContent, mThesisContent, mLanguageContent, mAbilityContent;
    private ImageView mSubjectSymbol, mProjectSymbol, mContestSymbol, mInternSymbol, mSupportSymbol, mMyspecSymbol;
    private ImageView mVolunteerSymbol, mPrizeSymbol, mThesisSymbol, mLanguageSymbol, mAbilitySymbol;

    private View.OnClickListener mSpecClickListener;
    private Intent mSpecFormActivityIntent;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_category);
        ButterKnife.bind(this);

        mUnbinder = ButterKnife.bind(this);

        initView();
    }

    public void initView() {

        String[] title = getResources().getStringArray(R.array.spec_title);
        String[] content = getResources().getStringArray(R.array.spec_content);

        mSubjectTitle = (TextView) mSubjectLayout.findViewById(R.id.spec_layout_title_txt);
        mProjectTitle = (TextView) mProjectLayout.findViewById(R.id.spec_layout_title_txt);
        mContestTitle = (TextView) mContestLayout.findViewById(R.id.spec_layout_title_txt);
        mInternTitle = (TextView) mInternLayout.findViewById(R.id.spec_layout_title_txt);
        mSupportTitle = (TextView) mSupportLayout.findViewById(R.id.spec_layout_title_txt);
        mVolunteerTitle = (TextView) mVolunteerLayout.findViewById(R.id.spec_layout_title_txt);
        mPrizeTitle = (TextView) mPrizeLayout.findViewById(R.id.spec_layout_title_txt);
        mThesisTitle = (TextView) mThesisLayout.findViewById(R.id.spec_layout_title_txt);
        mLanguageTitle = (TextView) mLanguageLayout.findViewById(R.id.spec_layout_title_txt);
        mAbilityTitle = (TextView) mAbilityLayout.findViewById(R.id.spec_layout_title_txt);
        mMyspecTitle = (TextView) mMyspecLayout.findViewById(R.id.spec_layout_title_txt);

        mSubjectTitle.setText(title[0]);
        mProjectTitle.setText(title[1]);
        mContestTitle.setText(title[2]);
        mInternTitle.setText(title[3]);
        mSupportTitle.setText(title[4]);
        mVolunteerTitle.setText(title[5]);
        mPrizeTitle.setText(title[6]);
        mThesisTitle.setText(title[7]);
        mLanguageTitle.setText(title[8]);
        mAbilityTitle.setText(title[9]);
        mMyspecTitle.setText(title[10]);

        mSubjectContent = (TextView) mSubjectLayout.findViewById(R.id.spec_layout_content_txt);
        mProjectContent = (TextView) mProjectLayout.findViewById(R.id.spec_layout_content_txt);
        mContestContent = (TextView) mContestLayout.findViewById(R.id.spec_layout_content_txt);
        mInternContent = (TextView) mInternLayout.findViewById(R.id.spec_layout_content_txt);
        mSupportContent = (TextView) mSupportLayout.findViewById(R.id.spec_layout_content_txt);
        mVolunteerContent = (TextView) mVolunteerLayout.findViewById(R.id.spec_layout_content_txt);
        mPrizeContent = (TextView) mPrizeLayout.findViewById(R.id.spec_layout_content_txt);
        mThesisContent = (TextView) mThesisLayout.findViewById(R.id.spec_layout_content_txt);
        mLanguageContent = (TextView) mLanguageLayout.findViewById(R.id.spec_layout_content_txt);
        mAbilityContent = (TextView) mAbilityLayout.findViewById(R.id.spec_layout_content_txt);
        mMyspecContent = (TextView) mMyspecLayout.findViewById(R.id.spec_layout_content_txt);

        mSubjectContent.setText(content[0]);
        mProjectContent.setText(content[1]);
        mContestContent.setText(content[2]);
        mInternContent.setText(content[3]);
        mSupportContent.setText(content[4]);
        mVolunteerContent.setText(content[5]);
        mPrizeContent.setText(content[6]);
        mThesisContent.setText(content[7]);
        mLanguageContent.setText(content[8]);
        mAbilityContent.setText(content[9]);
        mMyspecContent.setText(content[10]);

        mSubjectSymbol = (ImageView) mSubjectLayout.findViewById(R.id.spec_layout_symbol_img);
        mProjectSymbol = (ImageView) mProjectLayout.findViewById(R.id.spec_layout_symbol_img);
        mContestSymbol = (ImageView) mContestLayout.findViewById(R.id.spec_layout_symbol_img);
        mInternSymbol = (ImageView) mInternLayout.findViewById(R.id.spec_layout_symbol_img);
        mSupportSymbol = (ImageView) mSupportLayout.findViewById(R.id.spec_layout_symbol_img);
        mVolunteerSymbol = (ImageView) mVolunteerLayout.findViewById(R.id.spec_layout_symbol_img);
        mPrizeSymbol = (ImageView) mPrizeLayout.findViewById(R.id.spec_layout_symbol_img);
        mThesisSymbol = (ImageView) mThesisLayout.findViewById(R.id.spec_layout_symbol_img);
        mLanguageSymbol = (ImageView) mLanguageLayout.findViewById(R.id.spec_layout_symbol_img);
        mAbilitySymbol = (ImageView) mAbilityLayout.findViewById(R.id.spec_layout_symbol_img);
        mMyspecSymbol = (ImageView) mMyspecLayout.findViewById(R.id.spec_layout_symbol_img);

        mSubjectSymbol.setImageResource(R.drawable.grade);
        mProjectSymbol.setImageResource(R.drawable.pro);
        mContestSymbol.setImageResource(R.drawable.pro);
        mInternSymbol.setImageResource(R.drawable.intern);
        mSupportSymbol.setImageResource(R.drawable.sup);
        mVolunteerSymbol.setImageResource(R.drawable.vol);
        mPrizeSymbol.setImageResource(R.drawable.pri);
        mThesisSymbol.setImageResource(R.drawable.thesis);
        mLanguageSymbol.setImageResource(R.drawable.lang);
        mAbilitySymbol.setImageResource(R.drawable.ability);
        mMyspecSymbol.setImageResource(R.drawable.spec);

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSpecClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpecFormActivityIntent = new Intent(SpecCategoryActivity.this, SpecFormActivity.class);

                switch (view.getId()){
                    case R.id.spec_subject_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_SUBJECT);
                        break;

                    case R.id.spec_project_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_PROJECT);
                        break;

                    case R.id.spec_contest_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_CONTEST);
                        break;

                    case R.id.spec_intern_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_INTERN);
                        break;

                    case R.id.spec_prize_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_PRIZE);
                        break;

                    case R.id.spec_volunteer_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_VOLUNTEER);
                        break;

                    case R.id.spec_thesis_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_THESIS);
                        break;

                    case R.id.spec_language_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_LANGUAGE);
                        break;

                    case R.id.spec_support_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_SUPPORT);
                        break;

                    case R.id.spec_ability_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_ABILITY);
                        break;

                    case R.id.spec_myspec_layout:
                        mSpecFormActivityIntent.putExtra("specCategory", INDEX_SPEC_MYSPEC);
                        break;
                }
                startActivity(mSpecFormActivityIntent);
            }
        };

        mSubjectLayout.setOnClickListener(mSpecClickListener);
        mProjectLayout.setOnClickListener(mSpecClickListener);
        mContestLayout.setOnClickListener(mSpecClickListener);
        mInternLayout.setOnClickListener(mSpecClickListener);
        mPrizeLayout.setOnClickListener(mSpecClickListener);
        mVolunteerLayout.setOnClickListener(mSpecClickListener);
        mPrizeLayout.setOnClickListener(mSpecClickListener);
        mThesisLayout.setOnClickListener(mSpecClickListener);
        mLanguageLayout.setOnClickListener(mSpecClickListener);
        mSupportLayout.setOnClickListener(mSpecClickListener);
        mAbilityLayout.setOnClickListener(mSpecClickListener);
        mMyspecLayout.setOnClickListener(mSpecClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}

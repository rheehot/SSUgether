package org.collapsed.ssuparty_android.ui.specform;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class SpecFormActivity extends AppCompatActivity {

    private static final int INDEX_SPEC_SUBJECT = 11;
    private static final int INDEX_SPEC_PROJECT = 12;
    private static final int INDEX_SPEC_CONTEST = 13;
    private static final int INDEX_SPEC_INTERN = 14;
    private static final int INDEX_SPEC_SUPPORT = 15;
    private static final int INDEX_SPEC_VOLUNTEER = 16;
    private static final int INDEX_SPEC_PRIZE = 17;
    private static final int INDEX_SPEC_THESIS = 18;
    private static final int INDEX_SPEC_LANGUAGE = 19;
    private static final int INDEX_SPEC_ABILITY = 20;
    private static final int INDEX_SPEC_MYSPEC = 21;

    //ActionBarView
    @BindView(R.id.specform_actionbar_title_txt)
    TextView mActionbarToorbarText;
    @BindView(R.id.specform_actionbar_cancel_btn)
    ImageButton mActionbarCancelButton;
    @BindView(R.id.specform_actionbar_save_btn)
    Button mActionbarSaveButton;

    //CommonView - ProjectView, ContestView, InternView, VolunteerView, ThesisView, SupportView
    // SubjectView, PrizeView, LanguageView
    @BindView(R.id.specform_common_layout)
    RelativeLayout mCommonLayout;
    @BindView(R.id.specform_common_first_title_txt)
    TextView mCommonFirstTitleText;
    @BindView(R.id.specform_common_second_title_txt)
    TextView mCommonSecondTitleText;
    @BindView(R.id.specform_common_third_title_txt)
    TextView mCommonThirdTitleText;
    @BindView(R.id.specform_common_fourth_title_txt)
    TextView mCommonFourthTitleText;
    @BindView(R.id.specform_common_third_edittxt)
    EditText mCommonThirdEditText;
    @BindView(R.id.specform_common_date_layout)
    RelativeLayout mCommonDateLayout;
    @BindView(R.id.specform_common_fourth_text_layout)
    LinearLayout mCommonFourthTextLayout;

    //SubjectView
    @BindView(R.id.specform_common_spinner_layout)
    LinearLayout mCommonSpinnerLayout;
    @BindView(R.id.specform_common_spinner_txt)
    TextView mCommonSpinnerText;
    @BindView(R.id.specform_common_subject_spinner)
    Spinner mCommonSubjectSpinner;

    //PrizeView
    @BindView(R.id.specform_common_single_date_layout)
    LinearLayout mCommonSingleDateLayout;
    @BindView(R.id.specform_common_dual_date_layout)
    RelativeLayout mCommonDualDataLayout;

    //AbilityView
    @BindView(R.id.specform_ability_layout)
    LinearLayout mAbilityLayout;
    @BindView(R.id.specform_ability_input_btn)
    Button mAbilityInputButton;
    @BindView(R.id.specform_ability_edittext)
    EditText mAbilityEditText;
    @BindView(R.id.specform_ability_tag_layout)
    TagContainerLayout mAbilityTagLayout;

    //MySpecView
    @BindView(R.id.specform_myspec_layout)
    LinearLayout mMyspecLayout;
    @BindView(R.id.specform_myspec_title_edit)
    EditText mMyspecTitleEditText;
    @BindView(R.id.specform_myspec_content_edit)
    EditText mMyspecContentEditText;

    private Intent mIntentForCheck;
    private Unbinder mUnbinder;
    private int mCategoryNumber;
    private boolean mIsfirstTimeInAbility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specform);
        ButterKnife.bind(this);

        mUnbinder = ButterKnife.bind(this);
        mIntentForCheck = getIntent();
        mCategoryNumber = mIntentForCheck.getIntExtra("specCategory",1);

        initView();
    }

    public void initView() {
        setInvisibleView();
        initViewForSpecCategory(mCategoryNumber);
        mActionbarToorbarText.setFocusableInTouchMode(true);

        mActionbarCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mActionbarSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
    }

    public void initViewForSpecCategory(int categoryNumber) {
        switch (categoryNumber){
            case INDEX_SPEC_SUBJECT:
                initCommonView(getResources().getStringArray(R.array.spec_subject) ,categoryNumber);
                break;

            case INDEX_SPEC_PROJECT:
                initCommonView(getResources().getStringArray(R.array.spec_project) ,categoryNumber);
                break;

            case INDEX_SPEC_CONTEST:
                initCommonView(getResources().getStringArray(R.array.spec_contest) ,categoryNumber);
                break;

            case INDEX_SPEC_INTERN:
                initCommonView(getResources().getStringArray(R.array.spec_intern) ,categoryNumber);
                break;

            case INDEX_SPEC_PRIZE:
                initCommonView(getResources().getStringArray(R.array.spec_prize) ,categoryNumber);
                break;

            case INDEX_SPEC_SUPPORT:
                initCommonView(getResources().getStringArray(R.array.spec_support) ,categoryNumber);
                break;

            case INDEX_SPEC_VOLUNTEER:
                initCommonView(getResources().getStringArray(R.array.spec_valenteer) ,categoryNumber);
                break;

            case INDEX_SPEC_THESIS:
                initCommonView(getResources().getStringArray(R.array.spec_thesis) ,categoryNumber);
                break;

            case INDEX_SPEC_LANGUAGE:
                initCommonView(getResources().getStringArray(R.array.spec_language) ,categoryNumber);
                break;

            case INDEX_SPEC_ABILITY:
                initAbilityView();
                break;
            case INDEX_SPEC_MYSPEC:
                initMyspecView();
                break;
        }
    }

    public void setInvisibleView() {
        mCommonLayout.setVisibility(View.GONE);
        mAbilityLayout.setVisibility(View.GONE);
        mMyspecLayout.setVisibility(View.GONE);
        mCommonSpinnerLayout.setVisibility(View.GONE);
    }

    public void initCommonView(String[] specTextData, int categoryNumber) {

        mCommonLayout.setVisibility(View.VISIBLE);
        mActionbarToorbarText.setText(specTextData[0]);
        mCommonFirstTitleText.setText(specTextData[1]);
        mCommonSecondTitleText.setText(specTextData[2]);
        mCommonThirdTitleText.setText(specTextData[3]);

        switch(categoryNumber){
            case INDEX_SPEC_SUBJECT:
                mCommonSpinnerLayout.setVisibility(View.VISIBLE);
                mCommonFourthTextLayout.setVisibility(View.GONE);
                mCommonDateLayout.setVisibility(View.GONE);
                ArrayAdapter adapter = ArrayAdapter.createFromResource(SpecFormActivity
                        .this, R.array.spec_category_data, R.layout.item_spinner);
                mCommonSubjectSpinner.setAdapter(adapter);
                mCommonSpinnerText.setText(specTextData[4]);
                break;

            case INDEX_SPEC_PRIZE:
                mCommonFourthTitleText.setText(specTextData[4]);
                mCommonSingleDateLayout.setVisibility(View.VISIBLE);
                mCommonDualDataLayout.setVisibility(View.GONE);
                break;

            case INDEX_SPEC_LANGUAGE:
                mCommonDateLayout.setVisibility(View.GONE);
                mCommonFourthTextLayout.setVisibility(View.GONE);
                mCommonThirdEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;

            default:
                mCommonFourthTitleText.setText(specTextData[4]);
                mCommonSingleDateLayout.setVisibility(View.GONE);
                break;
        }
    }

    public void initAbilityView() {
        mAbilityLayout.setVisibility(View.VISIBLE);
        mActionbarToorbarText.setText("보유 기술");

        mIsfirstTimeInAbility = true;
        mAbilityTagLayout.addTag("보유 기술");

        mAbilityInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIsfirstTimeInAbility){
                    mIsfirstTimeInAbility = false;
                    mAbilityTagLayout.removeTag(0);
                }

                mAbilityTagLayout.addTag(mAbilityEditText.getText().toString());
                mAbilityEditText.setText("");
            }
        });

        mAbilityTagLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                AlertDialog dialog = new AlertDialog.Builder(SpecFormActivity.this)
                        .setTitle("정보 삭제")
                        .setMessage("이 정보를 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (position < mAbilityTagLayout.getChildCount()) {
                                    mAbilityTagLayout.removeTag(position);
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }

            @Override
            public void onTagCrossClick(int position) {
            }
        });
    }

    public void initMyspecView() {
        mMyspecLayout.setVisibility(View.VISIBLE);
        mActionbarToorbarText.setText("나만의 스펙");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}

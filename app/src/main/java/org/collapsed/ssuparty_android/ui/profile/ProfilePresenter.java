package org.collapsed.ssuparty_android.ui.profile;

import android.net.Uri;
import android.support.annotation.NonNull;

import org.collapsed.ssuparty_android.model.ProfileDB;
import org.collapsed.ssuparty_android.model.ProfileData;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProfilePresenter {

    //테스트용 ID
    String id = "kingjihoon123";

    private ProfileFragment mView;
    private ProfileDB mModel;
    ProfileData data;

    public ProfilePresenter(@NonNull ProfileFragment view) {
        this.mView = checkNotNull(view);
        this.mModel = new ProfileDB();

        //처음에 필수로 태그랑 소개 넣어주기 event bus 오류시 다시 확인, 추후에 회원가입 정보 처리할때 제거
        /*List<String> tags = new ArrayList<>();
        tags.add("숭실대학교");
        data = new ProfileData(id,"킹", "컴퓨터","","정보를 입력해주세요!",tags);
        mModel.writeNewUser(id, data);*/

    }

    public void onChangedTags(List<String> tags) {
        mModel.writeNewTags(id, tags);
    }

    public void onChangedIntroduction(String introduction) {
        mModel.writeNewIntroduction(id, introduction);
    }

    public void onChangedProfileImage(Uri imageUri){
        mModel.writeNewProfileImage(imageUri);
    }

    public void loadImageUrlFromFirebase(){
        mModel.passImageUrl();
    }
}

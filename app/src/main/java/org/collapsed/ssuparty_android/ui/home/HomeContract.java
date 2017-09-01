package org.collapsed.ssuparty_android.ui.home;

import org.collapsed.ssuparty_android.model.profile.UserProfileVO;

import java.util.ArrayList;

public interface HomeContract {
    interface View {
        void changeSearchResultState();

        void animateToDefaultState();

        void animateToSearchResultState();

        void showAllProfiles();

    }

    interface UserActionListener {
        ArrayList<UserProfileVO> fetchAllProfiles();
    }
}

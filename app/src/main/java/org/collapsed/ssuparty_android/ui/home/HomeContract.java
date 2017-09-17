package org.collapsed.ssuparty_android.ui.home;

public interface HomeContract {
    interface View {
        void changeSearchResultState();

        void animateToDefaultState();

        void animateToSearchResultState();
    }

    interface UserActionListener {
        void performSearch(CharSequence text);
    }
}

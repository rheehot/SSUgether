package org.collapsed.ssuparty_android.ui.account;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class AccountPresenterTest {
    @Mock
    private AccountActivity mView;

    private AccountPresenter mPresenter;

    @Before
    public void setUpTest() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new AccountPresenter(mView);
    }
}

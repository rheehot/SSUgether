package org.collapsed.ssuparty_android.ui.myparty;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;

public class MyPartyPresenterTest {

    @Mock
    private MyPartyFragment mView;

    private MypartyPresenter mPresenter;

    @Before
    public void setUpTest() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new MypartyPresenter(mView);
    }

    @Test
    public void defalutTest() {

    }
}

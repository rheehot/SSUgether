package org.collapsed.ssuparty_android.ui.home;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;

public class HomePresenterTest {

    @Mock
    private HomeFragment mView;

    private HomePresenter mPresenter;

    @Before
    public void setUpTest() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new HomePresenter(mView);
    }

    @Test
    public void defalutTest() {

    }
}

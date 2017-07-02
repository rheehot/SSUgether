package org.collapsed.ssuparty_android.ui.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;

public class MainPresenterTest {

    @Mock
    private MainActivity mView;

    private MainPresenter mPresenter;

    @Before
    public void setUpTest() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new MainPresenter(mView);
    }

    @Test
    public void defaultTest() {
        assertEquals(3, 1 + 2);
    }
}

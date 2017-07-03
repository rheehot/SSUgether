package org.collapsed.ssuparty_android.ui.findparty;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;

public class FindPartyPresenterTest {
    @Mock
    private FindPartyFragment mView;

    private FindPartyPresenter mPresenter;

    @Before
    public void setUpTest() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new FindPartyPresenter(mView);
    }

    @Test
    public void defaultTest() {
        assertEquals(3, 1 + 2);
    }
}

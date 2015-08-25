package io.mstream.boardgameengine.move

import io.mstream.boardgameengine.board.*
import org.junit.*

class SideTest {

    @Test fun canGetOppositeSide() {
        Assert.assertEquals("opposite to A should be B", Side.B, Side.A.opposite())
        Assert.assertEquals("opposite to B should be A", Side.A, Side.B.opposite())
    }
}

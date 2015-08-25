package io.mstream.boardgameengine.move

import org.junit.*

class SelectTest {

    @Test fun canCreateFromCords() {
        val select = Select.fromCords(2, 4)
        val position = select.position
        Assert.assertEquals("x cord of position should be 2",
                2, position.x)
        Assert.assertEquals("y cord of position should be 4",
                4, position.y)
    }
}

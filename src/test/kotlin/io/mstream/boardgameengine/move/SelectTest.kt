package io.mstream.boardgameengine.move

import org.junit.Assert
import org.junit.Test

class SelectTest {

    @Test fun canCreateFromCords() {
        val select = Select.fromCords(5, 10)
        val position = select.position
        Assert.assertEquals("x cord of position should be 5", 5, position.x)
        Assert.assertEquals("y cord of position should be 10", 10, position.y)
    }
}

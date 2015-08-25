package io.mstream.boardgameengine

import org.junit.*

class PositionTest {

    @Test fun canCreateFromCords() {
        val position = Position.fromCords(2, 4)
        Assert.assertEquals("x cord of position should be 2",
                2, position.x)
        Assert.assertEquals("y cord of position should be 4",
                4, position.y)
    }

    @Test fun canCachePositions() {
        val positionA = Position.fromCords(2, 4)
        val positionB = Position.fromCords(2, 4)
        Assert.assertSame("only one position should be created",
                positionA, positionB)
    }
}

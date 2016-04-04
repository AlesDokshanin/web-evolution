package web_kotlin

import java.awt.Point

internal class PolarPoint(angle: Double, distance: Int) : Comparable<PolarPoint> {

    var angle = angle
    var distance = distance

    constructor(pt: Point) : this(polarAngleFromCartesian(pt.x, pt.y), polarDistanceFromCartesian(pt.x, pt.y))

    constructor(ppt: PolarPoint) : this(ppt.angle, ppt.distance)

    override fun compareTo(other: PolarPoint): Int {
        return angle.compareTo(other.angle)
    }

    fun cartesianPoint(): Point {
        return Point((Math.cos(angle) * distance).toInt(), (Math.sin(angle) * distance).toInt());
    }

}


private fun polarAngleFromCartesian(x: Int, y: Int): Double {
    return Math.atan(y.toDouble() / x)
}

private fun polarDistanceFromCartesian(x: Int, y: Int): Int {
    return Math.sqrt(x.toDouble() * x + y * y).toInt()
}
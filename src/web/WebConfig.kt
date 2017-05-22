package web

object WebConfig {

    var drawFlies = true
    var dynamicFlies = false
    var normalFliesDistribution = true


    internal var maxTrappingNetLength = 5 * 1000
        @Throws(IllegalArgumentException::class)
        set(value) {
            when (value) {
                in MAX_TRAPPING_NET_LENGTH_LOWER..MAX_TRAPPING_NET_LENGTH_UPPER -> field = value
                else -> throw IllegalArgumentException("Invalid max trapping net length.")
            }
        }

    var sidesCount = 15
        @Throws(IllegalArgumentException::class)
        set(value) =
        when (value) {
            in MIN_SIDES..MAX_SIDES -> field = value
            else -> throw IllegalArgumentException("Invalid web sides count: $value (should be in range [$MIN_SIDES, $MAX_SIDES].")
        }

    var fliesCount = 100
        @Throws(IllegalArgumentException::class)
        set(value) {
            if (value < MIN_FLIES_COUNT || value > MAX_FLIES_COUNT) {
                throw IllegalArgumentException("Flies count should be in range [$MIN_FLIES_COUNT, $MAX_FLIES_COUNT]")
            }
            field = value
        }

    internal val minAngleBetweenSkeletonLines = Math.PI / (5 * WebConfig.sidesCount)

}
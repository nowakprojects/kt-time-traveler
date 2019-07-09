package com.github.nowakprojects.kttimetraveler.test


import com.github.nowakprojects.kttimetraveler.core.*
import java.time.*

class TestClockTimeProvider private constructor(private var clock: Clock) : CurrentTimeProvider(clock), TimeProvider {

    fun timeTravelTo(localTime: LocalTime) {
        clock = Clock.fixed(currentInstantWithTime(localTime, clock.zone), clock.zone)
    }

    companion object {

        @JvmOverloads
        @JvmStatic
        fun withFixedTime(localTime: LocalTime, zoneId: ZoneId = ZoneId.systemDefault()): TestClockTimeProvider {
            return TestClockTimeProvider(Clock.fixed(currentInstantWithTime(localTime, zoneId), zoneId))
        }

        fun currentInstantWithTime(localTime: LocalTime, zoneId: ZoneId): Instant {
            return ZonedDateTime.of(LocalDate.now().atTime(localTime), zoneId).toInstant()
        }
    }
}

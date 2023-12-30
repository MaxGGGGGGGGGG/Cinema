package info

import java.time.LocalDateTime

interface Info {
    var information: MutableMap<String, Triple<LocalDateTime, LocalDateTime, Array<Int>>?>
}

data class InfoImpl(override var information: MutableMap<String,
        Triple<LocalDateTime, LocalDateTime, Array<Int>>?>) : Info
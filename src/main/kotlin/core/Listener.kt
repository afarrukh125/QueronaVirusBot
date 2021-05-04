package core

import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.*

class Listener : ListenerAdapter() {
    private val MINUTES: Long = 5
    private val DELAY: Long = 1000 * 60 * MINUTES

    override fun onReady(evt: ReadyEvent) {
        val timer = Timer()
        val queryTimer = QueryTimer(evt)
        timer.schedule(queryTimer, 0L, DELAY)
    }
}
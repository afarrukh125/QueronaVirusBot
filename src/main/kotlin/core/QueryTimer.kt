package core

import net.dv8tion.jda.api.events.ReadyEvent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.net.ConnectException
import java.util.*

class QueryTimer(var evt: ReadyEvent) : TimerTask() {
    val USER_ID: String = "<ENTER ID HERE>"
    private val URL: String = "http://localhost:8888"

    override fun run() {
        val client = OkHttpClient();
        val request: Request = Request.Builder().url(URL).build()
        try {
            val response: Response = client.newCall(request).execute();
            val rawJson = response.body?.string()
            val jsonObject: JSONObject = (JSONParser().parse(rawJson) as JSONObject)
            val date = Date()
            val eligible: Boolean = jsonObject["eligible"] as Boolean
            val age = jsonObject["age"]
            println("$date: $jsonObject")
            if(eligible)
                evt.jda.openPrivateChannelById(USER_ID).queue { channel ->
                    channel.sendMessage("$date: Age is now $age so you can register for the vaccine").queue()
                }
        } catch (connectException:ConnectException) {
            return
        }

    }

}
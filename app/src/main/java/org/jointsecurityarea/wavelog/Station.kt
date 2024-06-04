package org.jointsecurityarea.wavelog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.gson.annotations.*

data class Station (
    @SerializedName("station_id") val id: Long,
    @SerializedName("station_profile_name") val name: String,
    @SerializedName("station_gridsquare") val gridsquare: String,
    @SerializedName("station_callsign") val callsign: String,
    @SerializedName("station_active") val active: Boolean = false
)

@Composable
fun StationCard(s: Station) {
    Text(s.id.toString())
    Text(s.name)
}

/*
[
{
station_id: "1",
station_profile_name: "JO30oo / DJ7NT",
station_gridsquare: "JO30OO",
station_callsign: "DJ7NT",
station_active: "1"
},
{
station_id: "2",
station_profile_name: "JO30oo / DO7INT",
station_gridsquare: "JO30OO",
station_callsign: "DO7INT",
station_active: null
}
]
 */
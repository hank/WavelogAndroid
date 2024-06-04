package org.jointsecurityarea.wavelog

import com.google.gson.annotations.*

data class Station (
    @SerializedName("station_id") val id: Long,
    @SerializedName("station_profile_name") val name: String,
    @SerializedName("station_gridsquare") val gridsquare: String,
    @SerializedName("station_callsign") val callsign: String,
    @SerializedName("station_active") val active: Boolean = false
)

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
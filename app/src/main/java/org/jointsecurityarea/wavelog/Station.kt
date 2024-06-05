package org.jointsecurityarea.wavelog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.annotations.*

data class Station (
    @SerializedName("station_id") val id: Long,
    @SerializedName("station_profile_name") val name: String,
    @SerializedName("station_gridsquare") val gridsquare: String,
    @SerializedName("station_callsign") val callsign: String,
    @SerializedName("station_active") val active: String?
)

@Composable
fun StationCard(s: Station) {
    ElevatedCard(elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.fillMaxWidth()) {
        Row {
            Box(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Text(s.id.toString(), style = MaterialTheme.typography.headlineMedium)
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            ) {
                Text(s.name, style = MaterialTheme.typography.headlineLarge)
                Row {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Row {
                            Text("Call: ", style = MaterialTheme.typography.bodyMedium)
                            Text(
                                s.callsign,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Column {
                        Row {
                            Text("Grid: ", style = MaterialTheme.typography.bodyMedium)
                            Text(
                                s.gridsquare,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                if(s.active != null) Icon(Icons.Filled.Check, contentDescription = "Check mark")
            }
        }
    }
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
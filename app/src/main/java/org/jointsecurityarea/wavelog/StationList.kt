package org.jointsecurityarea.wavelog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@Composable
fun StationsList(viewModel: StationViewModel, innerPadding: PaddingValues) {
    val agentsData = viewModel.stationsData.collectAsState()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = innerPadding)
    ) {
        items(count = agentsData.value.size) {
            StationCard(agentsData.value[it])
        }

    }
}

class StationViewModel : ViewModel() {
    val TAG = "StationList"
    private val _stationsData : MutableStateFlow<List<Station>> = MutableStateFlow(listOf())
    val stationsData : StateFlow<List<Station>> = _stationsData

    init{
        retrieveStationData()
    }

    private fun retrieveStationData(){
        viewModelScope.launch {
            val call : Call<List<Station>> = ApiClient.apiService.getStations(RetrofitClient.API_KEY)
            call.enqueue(object : Callback<List<Station>> {
                override fun onResponse(
                    call: Call<List<Station>>,
                    response: Response<List<Station>>
                ) {
                    if (response.isSuccessful) {
                        val responseData: List<Station>? = response.body()
                        if (responseData != null) {
                            _stationsData.value = responseData
                        }
                    } else {
                        // Handle error
                        Log.i(TAG, "Error: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                    // Handle failure
                    if (t is IOException) {
                        Log.i(TAG, "Network failure")
                    } else {
                        Log.i(TAG, "Conversion Error")
                    }
                }
            })
        }
    }
}
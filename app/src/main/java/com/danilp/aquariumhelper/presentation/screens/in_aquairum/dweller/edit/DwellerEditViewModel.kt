package com.danilp.aquariumhelper.presentation.screens.in_aquairum.dweller.edit

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.domain.dweller.model.Dweller
import com.danilp.aquariumhelper.domain.dweller.repository.DwellerRepository
import com.danilp.aquariumhelper.domain.use_case.validation.Validate
import com.danilp.aquariumhelper.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DwellerEditViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: DwellerRepository,
    private val savedStateHandle: SavedStateHandle,
    private val validate: Validate
) : ViewModel() {

    var state by mutableStateOf(DwellerEditState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<Int>("dwellerId") ?: return@launch
            state = state.copy(isLoading = true)
            val dwellerInfoResult = repository.findDwellerById(id)

            dwellerInfoResult.collect { result ->
                state = when (result) {
                    is Resource.Success -> {
                        state.copy(
                            dweller = result.data ?: Dweller.createEmpty(),
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Loading -> {
                        state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Error -> {
                        state.copy(isLoading = false, error = result.message)
                    }
                }
            }

            state = state.copy(
                name = state.dweller.name,
                genus = state.dweller.genus,
                amount = if (state.dweller.amount == 0) "" else state.dweller.amount.toString(),
                minTemperature = if (state.dweller.minTemperature == 0.0) "" else state.dweller.minTemperature.toString(),
                maxTemperature = if (state.dweller.maxTemperature == 0.0) "" else state.dweller.maxTemperature.toString(),
                liters = if (state.dweller.liters == 0.0) "" else state.dweller.liters.toString(),
                minPh = if (state.dweller.minPh == 0.0) "" else state.dweller.minPh.toString(),
                maxPh = if (state.dweller.maxPh == 0.0) "" else state.dweller.maxPh.toString(),
                minGh = if (state.dweller.minGh == 0.0) "" else state.dweller.minGh.toString(),
                maxGh = if (state.dweller.maxGh == 0.0) "" else state.dweller.maxGh.toString(),
                minKh = if (state.dweller.minKh == 0.0) "" else state.dweller.minKh.toString(),
                maxKh = if (state.dweller.maxKh == 0.0) "" else state.dweller.maxKh.toString(),
                description = state.dweller.description
            )

            val sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.in_aquarium_info_shared_preferences_key),
                Context.MODE_PRIVATE
            )
            state = state.copy(
                dweller = state.dweller.copy(
                    aquariumId = sharedPreferences.getInt(
                        context.getString(R.string.saved_aquarium_id_key),
                        0
                    )
                )
            )
        }
    }

    fun onEvent(event: DwellerEditEvent) {
        when (event) {
            is DwellerEditEvent.InsertButtonPressed -> {
                submitData()
            }
            is DwellerEditEvent.DeleteButtonPressed -> {
                viewModelScope.launch {
                    delete(state.dweller)
                    validationEventChannel.send(ValidationEvent.Success)
                }
            }
            is DwellerEditEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }
            is DwellerEditEvent.GenusChanged -> {
                state = state.copy(genus = event.genus)
            }
            is DwellerEditEvent.AmountChanged -> {
                state = state.copy(amount = event.amount)
            }
            is DwellerEditEvent.MinTemperatureChanged -> {
                state = state.copy(minTemperature = event.temp)
            }
            is DwellerEditEvent.MaxTemperatureChanged -> {
                state = state.copy(maxTemperature = event.temp)
            }
            is DwellerEditEvent.LitersChanged -> {
                state = state.copy(liters = event.liters)
            }
            is DwellerEditEvent.MinPhChanged -> {
                state = state.copy(minPh = event.ph)
            }
            is DwellerEditEvent.MaxPhChanged -> {
                state = state.copy(maxPh = event.ph)
            }
            is DwellerEditEvent.MinGhChanged -> {
                state = state.copy(minGh = event.gh)
            }
            is DwellerEditEvent.MaxGhChanged -> {
                state = state.copy(maxGh = event.gh)
            }
            is DwellerEditEvent.MinKhChanged -> {
                state = state.copy(minKh = event.kh)
            }
            is DwellerEditEvent.MaxKhChanged -> {
                state = state.copy(maxKh = event.kh)
            }
            is DwellerEditEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }
            is DwellerEditEvent.ImagePicked -> {
                state = state.copy(
                    dweller = state.dweller.copy(imageUri = event.imageUri.toString())
                )
            }
        }
    }

    private fun insert(dweller: Dweller) = viewModelScope.launch {
        repository.insert(dweller)
    }

    private fun delete(dweller: Dweller) = viewModelScope.launch {
        repository.delete(dweller)
    }

    private fun submitData() {
        val nameResult = validate.string(state.name)
        val amountResult = validate.integer(state.amount)
        val minTemperatureResult = validate.decimal(state.minTemperature, isRequired = true)
        val maxTemperatureResult = validate.decimal(state.maxTemperature, isRequired = true)
        val litersResult = validate.decimal(state.liters, isRequired = true)
        val minPhResult = validate.decimal(state.minPh)
        val maxPhResult = validate.decimal(state.maxPh)
        val minGhResult = validate.decimal(state.minGh)
        val maxGhResult = validate.decimal(state.maxGh)
        val minKhResult = validate.decimal(state.minKh)
        val maxKhResult = validate.decimal(state.maxKh)

        val hasError = listOf(
            nameResult,
            amountResult,
            minTemperatureResult,
            maxTemperatureResult,
            litersResult,
            minPhResult,
            maxPhResult,
            minGhResult,
            maxGhResult,
            minKhResult,
            maxKhResult
        ).any { it.errorMessage != null }

        if (hasError) {
            state = state.copy(
                nameError = nameResult.errorMessage,
                amountError = amountResult.errorMessage,
                minTemperatureError = minTemperatureResult.errorMessage,
                maxTemperatureError = maxTemperatureResult.errorMessage,
                litersError = litersResult.errorMessage,
                minPhError = minPhResult.errorMessage,
                maxPhError = maxPhResult.errorMessage,
                minGhError = minGhResult.errorMessage,
                maxGhError = maxGhResult.errorMessage,
                minKhError = minKhResult.errorMessage,
                maxKhError = maxKhResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            val isTempCorrect = (state.minTemperature.toDouble() < state.maxTemperature.toDouble())
            val isPhCorrect = (((state.minPh.toDoubleOrNull()
                ?: 0.0) < (state.maxPh.toDoubleOrNull() ?: 0.0)))
            val isGhCorrect = (((state.minGh.toDoubleOrNull()
                ?: 0.0) < (state.maxGh.toDoubleOrNull() ?: 0.0)))
            val isKhCorrect = (((state.minKh.toDoubleOrNull()
                ?: 0.0) < (state.maxKh.toDoubleOrNull() ?: 0.0)))

            state = state.copy(
                dweller = state.dweller.copy(
                    name = state.name,
                    genus = state.genus,
                    amount = state.amount.toInt(),
                    minTemperature = if (isTempCorrect) state.minTemperature.toDouble()
                    else state.maxTemperature.toDouble(),
                    maxTemperature = if (isTempCorrect) state.maxTemperature.toDouble()
                    else state.minTemperature.toDouble(),
                    liters = state.liters.toDouble(),
                    minPh = if (isPhCorrect) state.minPh.toDoubleOrNull() ?: 0.0
                    else state.maxPh.toDoubleOrNull() ?: 0.0,
                    maxPh = if (isPhCorrect) state.maxPh.toDoubleOrNull() ?: 0.0
                    else state.minPh.toDoubleOrNull() ?: 0.0,
                    minGh = if (isGhCorrect) state.minGh.toDoubleOrNull() ?: 0.0
                    else state.maxGh.toDoubleOrNull() ?: 0.0,
                    maxGh = if (isGhCorrect) state.maxGh.toDoubleOrNull() ?: 0.0
                    else state.minGh.toDoubleOrNull() ?: 0.0,
                    minKh = if (isKhCorrect) state.minKh.toDoubleOrNull() ?: 0.0
                    else state.maxKh.toDoubleOrNull() ?: 0.0,
                    maxKh = if (isKhCorrect) state.maxKh.toDoubleOrNull() ?: 0.0
                    else state.minKh.toDoubleOrNull() ?: 0.0,
                    description = state.description
                )
            )

            insert(state.dweller)
            validationEventChannel.send(ValidationEvent.Success)
        }

    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }

}
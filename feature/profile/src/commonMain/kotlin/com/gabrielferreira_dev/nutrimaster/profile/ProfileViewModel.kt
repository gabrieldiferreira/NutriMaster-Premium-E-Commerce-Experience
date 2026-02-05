package com.gabrielferreira_dev.nutrimaster.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielferreira_dev.nutrimaster.data.domain.CustomerRepository
import com.nutrimaster.shared.domain.Country
import com.nutrimaster.shared.domain.Customer
import com.nutrimaster.shared.domain.PhoneNumber
import com.nutrimaster.shared.util.RequestState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//onde se comunica e atualiza os dados do firebase e se comunica com o profileScreen

data class ProfileScreenState(
    val id: String = "",
    val photoURL: String? = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val city: String? = null,
    val postalCode: String = "",
    val address: String? = null,
    val country: Country = Country.Australia,
    val phoneNumber: PhoneNumber? = null,
)

class ProfileViewModel(
    private val customerRepository: CustomerRepository
): ViewModel() {
    var screenReady: RequestState<Unit> by mutableStateOf(RequestState.Loading)
    var screenState: ProfileScreenState by mutableStateOf(ProfileScreenState())
        private set

    val isFormValid: Boolean
        get() = with(screenState){
            firstName.length in 3..50 &&
            lastName.length in 3..50 &&
            city?.length in 3..20 &&
            postalCode.length == 8 &&
            address?.length in 5..100 &&
            phoneNumber?.number?.length in 8..15
        }
    init {
        refreshProfile()
        observerCustomer()
    }

    fun refreshProfile(){
        viewModelScope.launch {
            try {
                customerRepository.refreshCustomerInfo()
            } catch (e: Exception) {

            }
        }
    }

    fun observerCustomer(){
        viewModelScope.launch {
            customerRepository.readCustomerFlow().collectLatest { data ->
                if (data.isSuccess()){
                    //variavel buscandoCliente = fetchedCustomer / traducao
                    val fetchedCustomer = data.getSuccessData()
                    screenState = ProfileScreenState(
                        id = fetchedCustomer.id,
                            photoURL = fetchedCustomer.photoURL,
                            firstName = fetchedCustomer.firstName,
                            lastName = fetchedCustomer.lastName,
                            email = fetchedCustomer.email,
                            city = fetchedCustomer.city,
                            postalCode = fetchedCustomer.postalCode,
                            address = fetchedCustomer.address,
                            phoneNumber = fetchedCustomer.phoneNumber,
                            country = Country.entries.firstOrNull{
                                it.dialCode == fetchedCustomer
                                    .phoneNumber?.dialcode }
                                ?: Country.Australia
                    )
                    screenReady = RequestState.Success(Unit)
                } else if(data.isError()){
                    screenReady = RequestState.Error(message = data.getErrorMessage())
                }
            }
        }
    }
    fun updateFirstName(value: String){
        screenState = screenState.copy(firstName = value)
    }
    fun updateLastName(value: String){
        screenState = screenState.copy(lastName = value)
    }
    fun updateCity(value: String){
        screenState = screenState.copy(city = value)
    }
    fun updatePostalCode(value: String){
        val onlyNumbers = value.filter { it.isDigit() }
        if (onlyNumbers.length <= 8){
            screenState = screenState.copy(postalCode = value)
        }
    }
    fun updateAddress(value: String){
        screenState = screenState.copy(address = value)
    }
    fun updateCountry(value: Country){
        screenState = screenState.copy(
            country = value,
            phoneNumber = screenState.phoneNumber?.copy(
                dialcode = value.dialCode
            )
        )
    }
    fun updatePhoneNumber(value: String){
        screenState =screenState
            .copy(phoneNumber = PhoneNumber(
                dialcode = screenState.country.dialCode,
                number = value
            )
        )
    }
    fun updateCustomer(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        viewModelScope.launch {
            customerRepository.updateCustomer(
                customer = Customer(
                    id = screenState.id,
                    photoURL = screenState.photoURL,
                    firstName = screenState.firstName,
                    lastName = screenState.lastName,
                    email = screenState.email,
                    city = screenState.city,
                    postalCode = screenState.postalCode,
                    address = screenState.address,
                    phoneNumber = screenState.phoneNumber,
                ),
                onSuccess = onSuccess,
                onError = onError
            )
        }
    }
}


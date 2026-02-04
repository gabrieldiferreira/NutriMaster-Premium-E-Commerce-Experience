package com.nutrimaster.shared.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.nutrimaster.shared.IconPrimary
import com.nutrimaster.shared.Resources
import com.nutrimaster.shared.dialog.CountryPickerDialog
import com.nutrimaster.shared.domain.Country
import com.nutrimaster.shared.domain.PhoneNumber
import org.jetbrains.compose.resources.DrawableResource


@Composable
fun ProfileForm(
    modifier: Modifier = Modifier,
    firstName: String,
    country: Country,
    onCountrySelect: (Country) -> Unit,
    onFirstNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    email: String,
    photoUrl: String?,
    city: String?,
    onCityChange: (String) -> Unit,
    onPostalCodeChange: (String) -> Unit,
    postalCode: String?,
    address: String?,
    onAddressChange: (String) -> Unit,
    phoneNumber: String?,
    onPhoneNumberChange: (String) -> Unit,
){
    var showCountryDialog by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = showCountryDialog
    ) {
        CountryPickerDialog(
            country = Country.Australia,
            onDismiss = { showCountryDialog = false },
            onConfirmClick = { selectedCountry ->
                showCountryDialog = false
                onCountrySelect(selectedCountry)
            }
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp
            )
            .verticalScroll(state = rememberScrollState())
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Row(
        modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
    ) {
            AsyncImage(
                model = photoUrl ?: Resources.Icon.Person,
                contentDescription = "Foto de Perfíl",
                modifier = Modifier
                    .size(127.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, IconPrimary.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(2.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomTextField(
                    value = firstName,
                    onValueChange = onFirstNameChange,
                    placeholder = "Primeiro Nome",
                    error = firstName.length !in 3..50
                )
                CustomTextField(
                    value = lastName,
                    onValueChange = onLastNameChange,
                    placeholder = "Ultimo Nome",
                    error = lastName.length !in 3..50
                )
            }
    }
            CustomTextField(
                value = email,
                onValueChange = {},
                placeholder = "Email",
                enabled = false
            )
            CustomTextField(
                value = city ?: "",
                onValueChange = onCityChange,
                placeholder = "Cidade",
                error = city?.length !in 3..50
            )
            CustomTextField(
                value = postalCode ?: "",
                onValueChange =  onPostalCodeChange,
                placeholder = "Postal Code",
                error = postalCode?.length !in 3..9,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            CustomTextField(
                value = address ?: "",
                onValueChange = onAddressChange,
                placeholder = "Endereço",
                error = address?.length !in 3..50
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AlertTextField(
                    text = "+${country.dialCode}",
                    icon = country.flag,
                    onClick = { showCountryDialog = true }
                )
                Spacer(modifier = Modifier.width(2.dp))
                CustomTextField(
                    modifier = Modifier.weight(1f),
                    value = phoneNumber ?: "",
                    onValueChange = onPhoneNumberChange,
                    placeholder = "Phone Number",
                    error = phoneNumber.toString().length !in 5..30,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
    }
}
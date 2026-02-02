package com.gabrielferreira_dev.nutrimaster.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nutrimaster.shared.BebasNeueFont
import com.nutrimaster.shared.FontSize
import com.nutrimaster.shared.IconPrimary
import com.nutrimaster.shared.Resources
import com.nutrimaster.shared.Surface
import com.nutrimaster.shared.TextPrimary
import com.nutrimaster.shared.component.PrimaryButton
import com.nutrimaster.shared.component.ProfileForm
import com.nutrimaster.shared.domain.Country
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
     navigateBack: () -> Unit
) {
    var country by remember { mutableStateOf(Country.Australia) }

    Scaffold(
        containerColor = Surface,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Meu Perfil",
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.LARGE,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            painter = painterResource(Resources.Icon.BackArrow),
                            contentDescription = "Back Arrow icon",
                            tint = IconPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Surface,
                    scrolledContainerColor = Surface,
                    navigationIconContentColor = IconPrimary,
                    titleContentColor = TextPrimary,
                    actionIconContentColor = IconPrimary
                )
            )
        }
    )   { padding ->
        Column(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
                .padding(horizontal = 24.dp)
                .padding(
                    top = 12.dp,
                    bottom = 24.dp
                )
        ) {
            ProfileForm(
                modifier = Modifier.weight(1f),
                country = country,
                onCountrySelect = {
                },
                photoUrl = "",
                firstName = "",
                onFirstNameChange = {},
                lastName = "",
                onLastNameChange = {},
                email = "",
                city = "",
                onCityChange = {},
                postalCode = "",
                onPostalCodeChange = {},
                address = "",
                onAddressChange = {},
                phoneNumber = null,
                onPhoneNumberChange = {}
            )
            Spacer(modifier = Modifier.height(12.dp))
            PrimaryButton(
                text = "Atualizar Cadastro",
                icon = Resources.Icon.Checkmark,
                onClick = {}
            )
        }
    }
}
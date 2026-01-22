package com.gabrielferreira_dev.nutrimaster.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gabrielferreira_dev.nutrimaster.home.domain.DrawerItem
import com.nutrimaster.shared.BebasNeueFont
import com.nutrimaster.shared.FontSize
import com.nutrimaster.shared.TextBrand
import com.nutrimaster.shared.TextBrand2
import com.nutrimaster.shared.TextPrimary
import com.nutrimaster.shared.TextSecondary

@Composable
fun CustomDrawer(
    onProfileClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onSignOutClick: () -> Unit,
    onAdminPanelClick: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(70.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = TextBrand)){
                    append("NUTRI")
                }
                withStyle(style = SpanStyle(color = TextBrand2)){
                    append("MASTER")
                }
            },
            textAlign = TextAlign.Center,
            color = TextSecondary,
            fontFamily = BebasNeueFont(),
            fontSize = FontSize.EXTRA_LARGE
        )
        Spacer(modifier = Modifier.height(0.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Saúde Suplementar",
            textAlign = TextAlign.Center,
            color = TextPrimary,
            fontSize = FontSize.REGULAR
        )
        Spacer(modifier = Modifier.height((50.dp)))
        DrawerItem.entries.take(5).forEach { item ->
            DrawerItemCard(
                drawerItem = item,
                onClick = {
                    when(item) {
                        DrawerItem.Profile -> onProfileClick()
                        DrawerItem.Contact -> onContactUsClick()
                        DrawerItem.SignOut -> onSignOutClick()
                        else -> {}
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        Spacer(modifier = Modifier.weight((1f)))
        DrawerItemCard(
            drawerItem = DrawerItem.Admin,
            onClick = onAdminPanelClick
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}
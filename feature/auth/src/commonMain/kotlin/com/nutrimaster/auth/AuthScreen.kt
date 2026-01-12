package com.nutrimaster.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.nutrimaster.auth.component.GoogleButton
import com.nutrimaster.shared.Alpha
import com.nutrimaster.shared.BebasNeueFont
import com.nutrimaster.shared.FontSize
import com.nutrimaster.shared.RobotoCondensedFont
import com.nutrimaster.shared.Surface
import com.nutrimaster.shared.SurfaceBrand
import com.nutrimaster.shared.SurfaceError
import com.nutrimaster.shared.SurfaceSuccess
import com.nutrimaster.shared.TextBrand
import com.nutrimaster.shared.TextBrand2
import com.nutrimaster.shared.TextPrimary
import com.nutrimaster.shared.TextWhite
import rememberMessageBarState


@Composable
fun AuthScreen() {
    val messageBarState = rememberMessageBarState()
    var loadingState by remember { mutableStateOf(false)}

    Scaffold { padding ->
        ContentWithMessageBar(
            contentBackgroundColor = Surface,
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            messageBarState = messageBarState,
            errorMaxLines = 2,
            errorContentColor = SurfaceError,
            errorContainerColor = TextWhite,
            successContentColor = SurfaceBrand,
            successContainerColor = TextPrimary,


        ){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = TextBrand)){
                                append("NUTRI")
                            }
                            withStyle(style = SpanStyle(color = TextBrand2)){
                                append("MASTER")
                            }
                        }
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(Alpha.HALF),
                        text = "Fazer Login",
                        textAlign = TextAlign.Center,
                        fontFamily = RobotoCondensedFont(),
                        fontSize = FontSize.EXTRA_REGULAR,
                        color = TextPrimary
                    )
                }
                GoogleButtonUiContainerFirebase(
                    linkAccount = false,
                    onResult = { result ->
                        result.onSuccess { user ->
                            messageBarState.addSuccess("Autenticao Concluida com sucesso!")
                            loadingState = false //evita que o botton de carregando fique girando pra sempre
                        }.onFailure { error ->
                            if(error.message?.contains("Verifique sua conexao com a internet!") == true){
                                messageBarState.addError("Internet indisponivel")
                            }else if(error.message?.contains("Erro na Autenticacao") == true){
                                messageBarState.addError("Login com o Google Cancelado")
                            }else {
                                messageBarState.addError(error.message ?: "Erro desconhecido!")
                            }
                            loadingState = false //evita que o botton de carregando fique girando pra sempre
                        }
                    }
                ){
                    GoogleButton(
                        loading = loadingState,
                        onClick = {
                            loadingState = true
                            this@GoogleButtonUiContainerFirebase.onClick()
                        }
                    )
                }
            }
        }
    }
}
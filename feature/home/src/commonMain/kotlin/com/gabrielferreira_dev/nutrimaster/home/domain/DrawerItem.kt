package com.gabrielferreira_dev.nutrimaster.home.domain

import com.nutrimaster.shared.Resources
import org.jetbrains.compose.resources.DrawableResource

enum class DrawerItem(
    val title: String,
    val icon: DrawableResource
) {
    Profile(
        title = "Perfil",
        icon = Resources.Icon.Person

    ),
    Blog(
        title = "Blog",
        icon = Resources.Icon.Book
    ),
    Locations(
        title = "Localização",
        icon = Resources.Icon.MapPin
    ),
    Contact(
        title = "Fale Conosco",
        icon = Resources.Icon.Edit
    ),
    SignOut(
        title = "Sair da Conta",
        icon = Resources.Icon.SignOut
    ),
    Admin(
        title = "Painel de Administração",
        icon = Resources.Icon.Unlock
    )

}
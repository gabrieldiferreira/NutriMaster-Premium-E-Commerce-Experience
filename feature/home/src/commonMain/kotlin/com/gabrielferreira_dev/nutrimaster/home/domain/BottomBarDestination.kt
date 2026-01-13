package com.gabrielferreira_dev.nutrimaster.home.domain

import com.nutrimaster.shared.Resources
import com.nutrimaster.shared.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

enum class BottomBarDestination (
    val icon: DrawableResource,
    val title: String,
    val screen: Screen
){
    ProductsOverview(
        icon = Resources.Icon.Home,
        title = "NUTRIMASTER",
        screen = Screen.ProductsOverview
    ),
    Cart(
        icon = Resources.Icon.ShoppingCart,
        title = "CARRINHO",
        screen = Screen.Cart
    ),
    Categories(
        icon = Resources.Icon.Categories,
        title = "CATEGORIAS",
        screen = Screen.Categories
    )
}
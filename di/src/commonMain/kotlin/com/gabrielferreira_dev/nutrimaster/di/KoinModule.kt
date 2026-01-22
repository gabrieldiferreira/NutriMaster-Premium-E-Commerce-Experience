package com.gabrielferreira_dev.nutrimaster.di


import com.gabrielferreira_dev.nutrimaster.data.CustomerRepositoryImpl
import com.gabrielferreira_dev.nutrimaster.data.domain.CustomerRepository
import com.gabrielferreira_dev.nutrimaster.home.HomeGraphViewModel
import com.nutrimaster.auth.AuthViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImpl() }
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeGraphViewModel)
}
fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}
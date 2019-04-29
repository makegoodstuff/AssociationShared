package com.makegoodstuff.native.ChangePassword

import android.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import javax.inject.Qualifier
import javax.inject.Scope

class ChangePasswordBuilder(dependency: ParentComponent) : ViewBuilder<ChangePasswordPresentable, ChangePasswordRouter, ChangePasswordBuilder.ParentComponent>(dependency) {
    fun build(presentable: ChangePasswordPresentable): ChangePasswordRouter {
        val interactor = ChangePasswordInteractor()
        val component = ChangePasswordBuilder_Component.builder()
            .parentComponent(dependency)
            .view(presentable)
            .interactor(interactor)
            .build()
        return component.ChangePasswordRouter()
    }

    interface ParentComponent {
        fun changePasswordListener(): ChangePasswordInteractor.Listener
    }
}
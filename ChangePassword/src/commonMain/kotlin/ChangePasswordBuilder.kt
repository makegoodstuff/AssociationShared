package com.makegoodstuff.native.ChangePassword

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention

class ChangePasswordBuilder(dependency: ParentComponent) : ViewBuilder<ChangePasswordView, ChangePasswordRouter, ChangePasswordBuilder.ParentComponent>(dependency) {
    fun build(parentViewGroup: ViewGroup): ChangePasswordRouter {
        val view = createView(parentViewGroup)
        val interactor = ChangePasswordInteractor()
        val component = ChangePasswordBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.ChangePasswordRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): ChangePasswordView? {
        return
    }

    interface ParentComponent {
        fun changePasswordListener(): ChangePasswordInteractor.Listener
    }

    @dagger.Module
    abstract class Module {
        @ChangePasswordScope
        @Binds
        internal abstract fun presenter(view: ChangePasswordView): ChangePasswordInteractor.ChangePasswordPresenter
    

    }
}

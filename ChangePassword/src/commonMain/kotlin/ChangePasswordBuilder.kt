package com.makegoodstuff.native.ChangePassword

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.native.InteractorBaseComponent
import com.uber.rib.core.native.ViewBuilder
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

    // So this is how you'd do it if the whole RIB were in Android/Kotlin. But instead, this needs to be
    // platform agnostic. I'll come back to this in a sec.
    override fun inflateView(inflater LayoutInflater, parentViewGroup: ViewGroup): ChangePasswordView? {
        return inflater.inflate(R.layout.change_password_rib, parentViewGroup, false) as ChangePasswordView
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
    // now we have @Retention for scope in the view code, which we would normally have in Builder?
}
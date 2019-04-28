package com.makegoodstuff.demoapp.root.changepassword

import com.uber.rib.core.native.ViewRouter

// If we have it in KMP module...
import com.makegoodstuff.demoapp.root.changepassword.SupportBuilder

// If not...?

class ChangePasswordRouter(
        view: ChangePasswordView,
        interactor: ChangePasswordInteractor,
        component: ChangePasswordBuilder.Component,
        private val supportBuilder: SupportBuilder) : ViewRouter<ChangePasswordView, ChangePasswordInteractor, ChangePasswordBuilder.Component>(view, interactor, component) {

    private var supportRouter: SupportRouter? = null
    
    fun attachSupport() {
        if (supportRouter == null) {
            supportRouter = supportBuilder.build(view)
            attachChild(supportRouter)
            view.content.addView(supportRouter?.view)
        }
    }
}

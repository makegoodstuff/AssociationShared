package com.makegoodstuff.native.ChangePassword

import com.uber.rib.core.ViewRouter

class ChangePasswordRouter(
        view: ChangePasswordPresentable,
        interactor: ChangePasswordInteractor,
        component: ChangePasswordBuilder.Component: ViewRouter<ChangePasswordPresentable, ChangePasswordInteractor, ChangePasswordBuilder.Component>(view, interactor, component) {

}

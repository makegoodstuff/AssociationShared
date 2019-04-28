package com.makegoodstuff.native.ChangePassword

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

interface ChangePasswordPresentable: Presentable {
    var listener: ChangePasswordPresentableListener
}

interface ChangePasswordPresentableListener {
    val passwordFieldObservable: Observable<String>
    val didSubmitPasswordObservable: Observable<String>
    val isSubmitEnabled: Boolean
    fun didTapBackButton()
}

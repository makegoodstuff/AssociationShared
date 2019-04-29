package com.makegoodstuff.native.ChangePassword

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ChangePasswordInteractor : Interactor<ChangePasswordPresentable, ChangePasswordRouter>() {
    @Inject lateinit var listener: Listener
    @Inject lateinit var presenter: ChangePasswordPresentable
    @Inject @field:Named("mainThreadScheduler") lateinit var mainThreadScheduler: Scheduler

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        presenter.didSubmitPasswordObservable
                .subscribe {
                    // Handle request
                }
    }

    interface ChangePasswordPresenter {
        val didSubmitPasswordObservable: Completable
    }

    interface Listener {
        fun didFinish()
    }
}

package com.makegoodstuff.demoapp.root.ChangePassword

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

@RibInteractor
class ChangePasswordInteractor : Interactor<ChangePasswordInteractor.ChangePasswordPresenter, ChangePasswordRouter>() {

    @Inject lateinit var listener: Listener
    @Inject lateinit var presenter: ChangePasswordPresenter

    @Inject @field:Named("mainThreadScheduler") lateinit var mainThreadScheduler: Scheduler

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        presenter.didSubmitPasswordObservable
                .subscribe {
                    // Handle request
                }
    }

    override fun didTapBack(): Boolean {
        return router.handleBackNavigation()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface ChangePasswordPresenter {
        val didSubmitPasswordObservable: Completable
    }

    interface Listener {
        fun didFinish()
    }
}

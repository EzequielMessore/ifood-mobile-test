package br.com.ezequiel.twitterhappines.base

import android.arch.core.executor.ArchTaskExecutor
import android.arch.core.executor.TaskExecutor
import br.com.ezequiel.twitterhappines.core.scheduler.ISchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.jetbrains.spek.api.dsl.SpecBody
import org.mockito.Mockito

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

fun SpecBody.addArchExecutor() {
    ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
        override fun executeOnDiskIO(runnable: Runnable) {
            runnable.run()
        }

        override fun isMainThread(): Boolean {
            return true
        }

        override fun postToMainThread(runnable: Runnable) {
            runnable.run()
        }
    })
}

fun SpecBody.cleanArchExecutor() {
    ArchTaskExecutor.getInstance().setDelegate(null)
}


object SchedulerProvider : ISchedulersProvider {
    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler =  Schedulers.trampoline()
}
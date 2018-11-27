package br.com.ezequiel.twitterhappines.core.scheduler

import io.reactivex.Scheduler

interface ISchedulersProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}
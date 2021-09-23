package com.arcanesecurity.resumofinal.services

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("SpecifyJobSchedulerIdRange")
@AndroidEntryPoint
class AppJobService : JobService() {

    @Inject
    lateinit var notificationHandler: NotificationHandler
    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    override fun onStartJob(p0: JobParameters?): Boolean {
        notificationHandler.createNotification("Job rodando", "Testando uma job").run {
            notificationManager.notify((0..999).random(), this)
        }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }
}
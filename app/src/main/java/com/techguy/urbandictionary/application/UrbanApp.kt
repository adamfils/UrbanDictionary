package com.techguy.urbandictionary.application

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.orm.SchemaGenerator
import com.orm.SugarContext
import com.orm.SugarDb
import com.techguy.urbandictionary.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UrbanApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(this)

        //Initialize Sugar ORM
        SugarContext.init(this)

        // create table if not exists
        val schemaGenerator = SchemaGenerator(this)
        schemaGenerator.createDatabase(SugarDb(this).db)

        startKoin {
            androidContext(this@UrbanApp)
            modules(listOf(appModule))
        }

    }
}
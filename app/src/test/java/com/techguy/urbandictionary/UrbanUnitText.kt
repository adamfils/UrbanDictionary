package com.techguy.urbandictionary

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.orm.SugarRecord
import com.techguy.urbandictionary.model.SearchModel
import com.techguy.urbandictionary.utils.SharePrefUtil
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
//@PrepareForTest(Log::class)
class UrbanUnitText {

    val shared = mock(SharePrefUtil::class.java)
    val context  = mock(Context::class.java)


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(shared.getHistory(context)).thenReturn("lol")
    }

    @Test
    fun checkSharedPreference() {
        assertThat(shared.getHistory(context)).isEqualTo("lol")
    }



}
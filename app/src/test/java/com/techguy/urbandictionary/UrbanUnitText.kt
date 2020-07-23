package com.techguy.urbandictionary

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.techguy.urbandictionary.utils.DBUtils
import com.techguy.urbandictionary.utils.SharePrefUtil
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UrbanUnitText {

    val shared = mock(SharePrefUtil::class.java)
    val context  = mock(Context::class.java)
    val dBUtils = mock(DBUtils::class.java)


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        `when`(shared.getHistory(context)).thenReturn("lol")
    }

    @Test
    fun checkSharedPreference() {
        assertThat(shared.getHistory(context)).isEqualTo("lol")
    }

    @Test
    fun checkDB(){
        dBUtils.getDB()
        assertThat(dBUtils.getDB()).isNotNull()
        verify(dBUtils,times(2)).getDB()
    }

    @Test
    fun deleteDB(){
        dBUtils.deleteAll()
        verify(dBUtils,times(1)).deleteAll()
    }



}
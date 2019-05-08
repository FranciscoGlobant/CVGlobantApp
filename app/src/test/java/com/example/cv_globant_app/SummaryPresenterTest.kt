package com.example.cv_globant_app

import android.os.Bundle
import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.model.SummaryModel
import com.example.cv_globant_app.data.sources.GeneralDataSource
import com.example.cv_globant_app.summary.SummaryPresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class SummaryPresenterTest {
    private lateinit var mockedView: GeneralContract.View
    private lateinit var mockedRepo: GeneralDataSource
    private lateinit var summaryPresenter: SummaryPresenter

    @Before
    fun setup() {
        mockedView = mock()
        mockedRepo = mock()
        summaryPresenter = SummaryPresenter(mockedView, mockedRepo)
    }

    @Test
    fun getInfo_whenActivityIsFresh_getSummaryGetsCalled() {
        summaryPresenter.getInfo(true)
        verify(mockedRepo).getSummary(any())
    }

    @Test
    fun getInfo_whenActivityIsRecreated_getSummaryDoesNotGetCalled() {
        summaryPresenter.getInfo(false)
        verify(mockedRepo, never()).getSummary(any())
    }

    @Test
    fun getInfo_whenGetInfoIsSuccesful_paintInfoGetsCalled(){
        `when`(mockedRepo.getSummary(any())).thenAnswer {
            (it.arguments[0] as GeneralDataSource.GeneralLoadCallback).onSuccess(SummaryModel())
        }
        summaryPresenter.getInfo(true)
        verify(mockedView).paintInfo(SummaryModel())
    }

    @Test
    fun getInfo_whenGetInfoFails_notifyErrorGetsCalled(){
        `when`(mockedRepo.getSummary(any())).thenAnswer {
            (it.arguments[0] as GeneralDataSource.GeneralLoadCallback).onFailure(Throwable())
        }
        summaryPresenter.getInfo(true)
        verify(mockedView).notifyError(any())
    }
}
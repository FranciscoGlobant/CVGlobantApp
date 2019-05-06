package com.example.cv_globant_app

import android.os.Bundle
import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.sources.GeneralDataSource
import com.example.cv_globant_app.experience.ExperiencePresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class ExperiencePresenterTest {
    private lateinit var mockedView: GeneralContract.View
    private lateinit var mockedRepo: GeneralDataSource
    private lateinit var summaryPresenter: ExperiencePresenter

    @Before
    fun setup() {
        mockedView = mock()
        mockedRepo = mock()
        summaryPresenter = ExperiencePresenter(mockedView, mockedRepo)
    }

    @Test
    fun getInfo_whenStateIsNull_getExperienceGetsCalled() {
        summaryPresenter.getInfo(null)
        verify(mockedRepo).getExperience(any())
    }

    @Test
    fun getInfo_whenStateIsNotNull_getExperienceDoesNotGetCalled() {
        summaryPresenter.getInfo(Bundle())
        verify(mockedRepo, never()).getExperience(any())
    }
}
package com.example.cv_globant_app

import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.model.ExperienceModel
import com.example.cv_globant_app.data.sources.GeneralDataSource
import com.example.cv_globant_app.experience.ExperiencePresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ExperiencePresenterTest {
    private lateinit var mockedView: GeneralContract.View
    private lateinit var mockedRepo: GeneralDataSource
    private lateinit var experiencePresenter: ExperiencePresenter

    @Before
    fun setup() {
        mockedView = mock()
        mockedRepo = mock()
        experiencePresenter = ExperiencePresenter(mockedView, mockedRepo)
    }

    @Test
    fun getInfo_whenActivityIsFresh_getExperienceGetsCalled() {
        experiencePresenter.getInfo(true)
        verify(mockedRepo).getExperience(any())
    }

    @Test
    fun getInfo_whenActivityIsRecreated_getExperienceDoesNotGetCalled() {
        experiencePresenter.getInfo(false)
        verify(mockedRepo, never()).getExperience(any())
    }

    @Test
    fun getInfo_whenGetInfoIsSuccesful_paintInfoGetsCalled(){
        Mockito.`when`(mockedRepo.getExperience(any())).thenAnswer {
            (it.arguments[0] as GeneralDataSource.GeneralLoadCallback).onSuccess(ExperienceModel().experienceItems)
        }
        experiencePresenter.getInfo(true)
        verify(mockedView).paintInfo(ExperienceModel().experienceItems)
    }

    @Test
    fun getInfo_whenGetInfoFails_notifyErrorGetsCalled(){
        Mockito.`when`(mockedRepo.getExperience(any())).thenAnswer {
            (it.arguments[0] as GeneralDataSource.GeneralLoadCallback).onFailure(Throwable())
        }
        experiencePresenter.getInfo(true)
        verify(mockedView).notifyError(any())
    }
}
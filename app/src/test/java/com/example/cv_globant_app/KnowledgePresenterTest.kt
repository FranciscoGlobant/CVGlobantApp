package com.example.cv_globant_app

import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.model.KnowledgeModel
import com.example.cv_globant_app.data.sources.GeneralDataSource
import com.example.cv_globant_app.knowledge.KnowledgePresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class KnowledgePresenterTest {
    private lateinit var mockedView: GeneralContract.View
    private lateinit var mockedRepo: GeneralDataSource
    private lateinit var knowledgePresenter: KnowledgePresenter

    @Before
    fun setup() {
        mockedView = mock()
        mockedRepo = mock()
        knowledgePresenter = KnowledgePresenter(mockedView, mockedRepo)
    }

    @Test
    fun getInfo_whenActivityIsFresh_getKnowledgeGetsCalled() {
        knowledgePresenter.getInfo(true)
        verify(mockedRepo).getKnowledge(any())
    }

    @Test
    fun getInfo_whenActivityIsRecreated_getKnowledgeDoesNotGetCalled() {
        knowledgePresenter.getInfo(false)
        verify(mockedRepo, never()).getKnowledge(any())
    }

    @Test
    fun getInfo_whenGetInfoIsSuccesful_paintInfoGetsCalled(){
        Mockito.`when`(mockedRepo.getKnowledge(any())).thenAnswer {
            (it.arguments[0] as GeneralDataSource.GeneralLoadCallback).onSuccess(KnowledgeModel().knowledgeItems)
        }
        knowledgePresenter.getInfo(true)
        verify(mockedView).paintInfo(KnowledgeModel().knowledgeItems)
    }

    @Test
    fun getInfo_whenGetInfoFails_notifyErrorGetsCalled(){
        Mockito.`when`(mockedRepo.getKnowledge(any())).thenAnswer {
            (it.arguments[0] as GeneralDataSource.GeneralLoadCallback).onFailure(Throwable())
        }
        knowledgePresenter.getInfo(true)
        verify(mockedView).notifyError(any())
    }
}
package com.example.cv_globant_app

import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.sources.GeneralDataSource
import com.example.cv_globant_app.knowledge.KnowledgePresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class KnowledgePresenterTest {
    private lateinit var mockedView: GeneralContract.View
    private lateinit var mockedRepo: GeneralDataSource
    private lateinit var summaryPresenter: KnowledgePresenter

    @Before
    fun setup() {
        mockedView = mock()
        mockedRepo = mock()
        summaryPresenter = KnowledgePresenter(mockedView, mockedRepo)
    }

    @Test
    fun getInfo_whenActivityIsFresh_getKnowledgeGetsCalled() {
        summaryPresenter.getInfo(true)
        verify(mockedRepo).getKnowledge(any())
    }

    @Test
    fun getInfo_whenActivityIsRecreated_getKnowledgeDoesNotGetCalled() {
        summaryPresenter.getInfo(false)
        verify(mockedRepo, never()).getKnowledge(any())
    }
}
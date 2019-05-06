package com.example.cv_globant_app.data.model

data class ExperienceItem (val companyName: String = "",
                     val role: String = "",
                     val fromDate: String = "",
                     val toDate: String = "",
                     val responsabilities: List<String> = listOf(),
                     val companyLogo: String = "")
package com.example.rickandmortycharacters.domain.models

data class Info(val next: String = "",
                val pages: Int = 0,
                val prev: Int? = null,
                val count: Int = 0)
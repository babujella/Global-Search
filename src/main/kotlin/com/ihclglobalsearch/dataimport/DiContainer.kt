package com.ihclglobalsearch.dataimport

import com.ihclglobalsearch.service.SpatialSearchService
import com.ihclglobalsearch.service.SpellCheckService
import com.ihclglobalsearch.service.SuggestionDestinationService
import com.ihclglobalsearch.service.SuggestionHotelsRestaurantsSpa
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val kodein=DI{
    bind<SpatialSearchService>() with singleton { SpatialSearchService() }
    bind <SpellCheckService>() with singleton{ SpellCheckService() }
    bind <SuggestionDestinationService>() with singleton{ SuggestionDestinationService() }
    bind <SuggestionHotelsRestaurantsSpa>() with singleton{SuggestionHotelsRestaurantsSpa()  }
}

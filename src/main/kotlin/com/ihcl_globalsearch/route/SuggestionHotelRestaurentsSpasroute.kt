import com.ihcl_globalsearch.model.FinalHotelRestaurantSpa
import com.ihcl_globalsearch.service.SuggestedUserTerms
import com.ihcl_globalsearch.service.SuggestionDestinationService
import com.ihcl_globalsearch.service.Suggestions
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureHotelRestaurantSpaRouting() {
    routing {
        get("{Taj}") {
            val params = call.request.uri
            val response = Suggestions().suggestions(params)
            val usersuggestions = SuggestedUserTerms().extracted(response, params)
            var listOfSuggetions = usersuggestions?.suggestions
            val resp= SuggestionDestinationService().suggestionDestinations().response.docs

            var hoteList = ArrayList<Any>()
            var spaList = ArrayList<Any>()
            var restaurantList=ArrayList<Any>()
            var destinationList=ArrayList<Any>()
            destinationList.add(resp)
            if (listOfSuggetions != null) {
                for (item in listOfSuggetions) {
                    var s = item.term
                    if (s != null) {
                        if (s.contains("Hotel")) {
                            hoteList.add(item)
                        } else if (s.contains("Spa")) {
                            spaList.add(item)
                            restaurantList.add(item)
                        } else {
                            hoteList.add(item)
                        }
                    }
                }
            }
            val s: Set<Any> = LinkedHashSet(destinationList)
            var finalHotelRestaurantSpa= FinalHotelRestaurantSpa(hoteList,spaList,restaurantList,s)
            call.respond(finalHotelRestaurantSpa)
        }
    }
}
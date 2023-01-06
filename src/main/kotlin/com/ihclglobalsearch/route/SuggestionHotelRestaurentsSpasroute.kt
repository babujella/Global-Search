
import com.ihclglobalsearch.dataimport.kodein
import com.ihclglobalsearch.service.SuggestionHotelsRestaurantsSpa
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.uri
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import org.kodein.di.instance

fun Application.configureHotelRestaurantSpaRouting() {

    routing {
        get("{Taj}") {
            val params = call.request.uri
            val response = SuggestionHotelsRestaurantsSpa().suggestions(params)
            val finalHotelRestaurantSpa = SuggestionHotelsRestaurantsSpa().extracted(response, params)
            call.respond(finalHotelRestaurantSpa!!)
        }
    }
}
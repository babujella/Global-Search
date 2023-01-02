
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
            val finalHotelRestaurantSpa = Suggestions().extracted(response, params)
            call.respond(finalHotelRestaurantSpa!!)
        }
    }
}
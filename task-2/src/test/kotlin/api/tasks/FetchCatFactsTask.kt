package api.tasks

import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.rest.interactions.Get

object FetchCatFactsTask {

    fun withLimit(limit: Int): Task =
        Task.where(
            "{0} retrieves up to $limit cat facts",
            Get.resource("/facts").with { request ->
                request.queryParam("limit", limit)
            }
        )
}
package api.tests

import api.dto.CatFactsResponseDto
import api.tasks.FetchCatFactsTask
import net.serenitybdd.junit5.SerenityJUnit5Extension
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.rest.abilities.CallAnApi
import net.serenitybdd.screenplay.rest.questions.LastResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SerenityJUnit5Extension::class)
class CatFactTest {

    @Test
    fun `returns five facts with correct length for the first fact`() {
        val user = Actor.named("Ailurophile")
            .whoCan(CallAnApi.at("https://catfact.ninja"))

        user.attemptsTo(FetchCatFactsTask.withLimit(5))

        val response = user.asksFor(LastResponse.received())
        Assertions.assertEquals(200, response.statusCode())

        val body = response.`as`(CatFactsResponseDto::class.java)
        Assertions.assertEquals(5, body.data.size)

        val firstFact = body.data.first()
        Assertions.assertEquals(firstFact.fact.length, firstFact.length)
    }
}
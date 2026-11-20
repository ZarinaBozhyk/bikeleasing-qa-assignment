package api.tests

import api.dto.CatFactsResponseDto
import api.tasks.FetchCatFactsTask
import config.TestConfig
import net.serenitybdd.junit5.SerenityJUnit5Extension
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.Question
import net.serenitybdd.screenplay.rest.abilities.CallAnApi
import net.serenitybdd.screenplay.rest.questions.LastResponse
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SerenityJUnit5Extension::class)
class CatFactTest {

    @Test
    fun `returns five facts with correct length for the first fact`() {
        val user = Actor.named("Ailurophile")
            .whoCan(CallAnApi.at(TestConfig.apiBaseUrl))

        user.attemptsTo(FetchCatFactsTask.withLimit(FACT_COUNT))

        user.should(
            seeThatResponse("the API responds with status 200") { response ->
                response.statusCode(200)
            }
        )

        val facts = user.asksFor(LastResponse.received())
            .`as`(CatFactsResponseDto::class.java)
            .data
        val firstFact = facts.first()

        user.should(
            seeThat("the number of returned facts", Question { facts.size }, equalTo(FACT_COUNT)),
            seeThat(
                "the stated length of the first fact",
                Question { firstFact.length },
                equalTo(firstFact.fact.length)
            )
        )
    }

    companion object {
        private const val FACT_COUNT = 5
    }
}
package br.com.phellipe.functional

import br.com.phellipe.JavalinApp
import br.com.phellipe.controller.UserController
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import io.javalin.plugin.json.JavalinJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FunctionalTest {
    private val app = JavalinApp()
    private val userJson = JavalinJson.toJson(UserController.users)

    @BeforeEach
    fun setUp() {
        app.start(1234)
    }

    @AfterEach
    fun setDown() {
        app.stop()
    }

    @Test
    fun `GET to fetch user return list of users`() {
        val response: HttpResponse<String>? = Unirest.get("http://localhost:1234/users").asString()
        assertThat(response?.status).isEqualTo(200)
        assertThat(response?.body).isEqualTo(userJson)
    }
}

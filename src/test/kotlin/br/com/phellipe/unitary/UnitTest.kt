package br.com.phellipe.unitary

import br.com.phellipe.controller.UserController
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UnitTest {
    private val ctx = mockk<Context>(relaxed = true)

    @Test
    fun `POST to create users gives 201 from valid username`() {
        every { ctx.queryParam("username") } returns "Roland"
        UserController.create(ctx)
        verify { ctx.status(201) }
    }

    @Test
    fun `POST to create users throws for invalid username`() {
        every { ctx.queryParam("username") } returns null
        Assertions.assertThrows(
            BadRequestResponse::class.java
        ) { UserController.create(ctx) }
    }
}

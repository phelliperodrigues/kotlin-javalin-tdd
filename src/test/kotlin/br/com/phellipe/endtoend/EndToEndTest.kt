package br.com.phellipe.endtoend

import br.com.phellipe.JavalinApp
import io.github.bonigarcia.wdm.WebDriverManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class EndToEndTest {

    private val app = JavalinApp()

    @BeforeEach
    fun setUp() {
        app.start(1234)
    }

    @AfterEach
    fun setDown() {
        app.stop()
    }

    @Test
    fun `UI contails correct heading`() {
        WebDriverManager.chromedriver().setup()
        val driver: WebDriver = ChromeDriver(
            ChromeOptions().apply {
                addArguments("--headless")
                addArguments("--disable-gpu")
            }
        )

        driver.get("http://localhost:1234/ui")
        assertThat(driver.pageSource).contains("<h1>User UI</h1>")
        driver.quit()
    }
}

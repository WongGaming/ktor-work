// Task 4.5.1: Ktor application testing

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication

@Suppress("Unused")
class ApplicationTest : FreeSpec({
    "Path / yields 'Hello World!' greeting" {
        testApplication {
            configure()
            val response = client.get("/")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/plain")
            response.bodyAsText() shouldBe "Hello World!"
        }
    }

    "Path /hello/joe yields 'Hello joe!' greeting" {
        testApplication {
            configure()
            val response = client.get("/hello/joe")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/plain")
            response.bodyAsText() shouldBe "Hello joe!"
        }
    }

    "Path /hello/jane yields 'Hello jane!' greeting" {
        testApplication {
            configure()
            val response = client.get("/hello/jane")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/plain")
            response.bodyAsText() shouldBe "Hello jane!"
        }
    }

    "Path /hello/ without a name returns 404" {
        testApplication {
            configure()
            val response = client.get("/hello/")
            response.status shouldBe HttpStatusCode.NotFound
        }
    }

    // Add more tests here
})

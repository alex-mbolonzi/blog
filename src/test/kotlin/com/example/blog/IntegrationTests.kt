package com.example.blog

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.junit.jupiter.api.Test

//import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Assertions.assertEquals


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

  @Test
  fun `Assert blog page title, content and status code`() {
	  
    val entity = restTemplate.getForEntity("/", String::class.java)
	  
//    assertThat(entity.statusCode.toString()).isEqualTo(HttpStatus.OK)
	  assertEquals(HttpStatus.OK, entity.statusCode)
//    assertThat(entity.body).contains("<h1>Blog</h1>")
	  assertEquals("<h1>Blog</h1>", entity.body)
  }
	
  @Test
  fun `Assert article page title, content and status code`() {
    println(">> Assert article page title, content and status code")
    val title = "Reactor Aluminium has landed"
    val entity = restTemplate.getForEntity("/article/${title.toSlug()}", String::class.java)
//    assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
	assertEquals(HttpStatus.OK, entity.statusCode)
//    assertThat(entity.body).contains(title, "Lorem ipsum", "dolor sit amet")
	assertEquals("Lorem ipsum", "dolor sit amet", title)
  }

}
package com.example.apiconsumer


import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

@ExtendWith(MockitoExtension.class)
class GithubApiClientTest extends Specification {

    def "should return list all transport service"() {
        when:
        def a = 2
        def b = 3

        then:
        def c = a + b

        and:
        c == 5
    }
}

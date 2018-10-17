package com.example.part2.ex01;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServer;

/* 메서드 레퍼런스 */
public class SSuber05 {

	public static Mono<ServerResponse> helloHandler(ServerRequest req) {
		return ServerResponse.ok().syncBody("Hello " + req.pathVariable("name"));
	}

	public static void main(String[] args) throws Exception {

		HttpServer.create("localhost", 8088)
		.newHandler(new ReactorHttpHandlerAdapter(toHttpHandler(route(path("/hello/{name}"), SSuber05::helloHandler)))).subscribe();
		
		System.in.read();
	}
}
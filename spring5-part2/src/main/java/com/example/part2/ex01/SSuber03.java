package com.example.part2.ex01;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServer;

public class SSuber03 {

	public static void main(String[] args) throws Exception {
		HandlerFunction<ServerResponse> helloHandler = req -> ok().syncBody("Hello " + req.pathVariable("name"));
		RouterFunction<ServerResponse> routerFunction = req -> path("/hello/{name}").test(req) ? Mono.just(helloHandler) : Mono.empty();

		HttpServer.create("localhost", 8088)
		.newHandler(new ReactorHttpHandlerAdapter(toHttpHandler(routerFunction))).subscribe();

		System.in.read();
	}
}
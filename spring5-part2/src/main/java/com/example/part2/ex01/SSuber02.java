package com.example.part2.ex01;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServer;

/* 람다 */
public class SSuber02 {

	public static void main(String[] args) throws Exception {
		
		HandlerFunction<ServerResponse> helloHandler = (ServerRequest req) -> ServerResponse.ok().syncBody("Hello " + req.pathVariable("name"));
		RouterFunction<ServerResponse> routerFunction = (ServerRequest req) -> RequestPredicates.path("/hello/{name}").test(req) ? Mono.just(helloHandler) : Mono.empty();
				
		HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction);
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

		HttpServer server = HttpServer.create("localhost", 8088);
		server.newHandler(adapter).subscribe();

		System.in.read();	
	}
}
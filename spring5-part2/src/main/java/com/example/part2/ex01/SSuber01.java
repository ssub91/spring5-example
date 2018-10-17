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

/* 익명 클래스 */
public class SSuber01 {

	public static void main(String[] args) throws Exception {
		
		HandlerFunction<ServerResponse> helloHandler = new HandlerFunction<ServerResponse>(){
			@Override
			public Mono<ServerResponse> handle(ServerRequest req) {
					return ServerResponse.ok().syncBody("Hello " + req.pathVariable("name"));
			}
		};
		
		RouterFunction<ServerResponse> routerFunction = new RouterFunction<ServerResponse>() {
			@Override
			public Mono<HandlerFunction<ServerResponse>> route(ServerRequest request) {
				return RequestPredicates.path("/hello/{name}").test(request) ? Mono.just(helloHandler) : Mono.empty();
			}
		};
				
		HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction);
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

		HttpServer server = HttpServer.create("localhost", 8088);
		server.newHandler(adapter).subscribe();

		System.in.read();		
	}
}
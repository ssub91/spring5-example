package com.example.part2.ex01;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import reactor.ipc.netty.http.server.HttpServer;

/* 유틸 메서드 정적 임포트 */
public class SSuber04 {

	public static void main(String[] args) throws Exception {
		
		HttpServer.create("localhost", 8088)
		.newHandler(new ReactorHttpHandlerAdapter(toHttpHandler(route(path("/hello/{name}"), req -> ok().syncBody("Hello " + req.pathVariable("name")))))).subscribe();
		
		System.in.read();
	}
}
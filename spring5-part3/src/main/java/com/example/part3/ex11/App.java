package com.example.part3.ex11;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.example.part3.ex10.Apple;


public class App {
	
	public static void ex01() {
		Predicate<Apple> p = (Apple a) -> a.getWeight() > 150;
		System.out.println(p.test(new Apple(200, "green")));
	}

	public static void ex02() {
		Function<String, Integer> f = (String s) -> s.length();
		System.out.println(f.apply("Hello World"));
	}

	public static void ex03() {
		Consumer<Apple> c = (Apple a) -> System.out.println(a);
		c.accept(new Apple(200, "green"));
	}

	public static void ex04() {
		Supplier<Integer> s = () -> 2018;
		System.out.println(s.get());
	}

	public static void ex05() {
		BiPredicate<Apple, Apple> bp = (Apple a1, Apple a2) -> a1.getWeight() == a2.getWeight();
		System.out.println(bp.test(new Apple(300, "red"), new Apple(200, "red")));
	}
	
	public static void ex06() {
		BiFunction<Apple, Apple, Integer> bf = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
		System.out.println(bf.apply(new Apple(300, "red"), new Apple(200, "red")));
	}

	public static void ex07() {
		BiConsumer<Integer, Integer> bc = (Integer x, Integer y) -> System.out.println(x + " + " + y + " = " + (x + y));
		bc.accept(100, 200);
	}
	
	
	public static void ex08() {
		Consumer<Integer> c = (Integer year) -> {
			System.out.print("올해는 ");
			System.out.print(year + "년 입니다.");
			System.out.print("\n");
		};
		
		c.accept(2018);		
	}
	
	public static void main(String[] args) {
		ex01();
		ex02();
		ex03();
		ex04();
		ex05();
		ex06();
		ex07();
		ex08();
	}
}

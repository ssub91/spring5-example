package com.example.part3.ex07;

public class RedApplePredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {
		return "red".equals(apple.getColor());
	}
}
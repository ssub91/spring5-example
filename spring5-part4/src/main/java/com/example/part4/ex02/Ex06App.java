package com.example.part4.ex02;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Ex06App {
	public static void main(String[] args) throws Exception {
		Publisher<Integer> p = iterPub(Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList()));
		
		Publisher<Integer> op1 = mapPub(p, s -> s * s);
		Publisher<Integer> op2 = mapPub(op1, s -> -s);		
		Publisher<Integer> op3 = reducePub(op2, 0, (a, b) -> a + b);
		
		Subscriber<Integer> s = logSub();

		op3.subscribe(s);
	}


	public static <T> Publisher<T> reducePub(Publisher<T> publisher, T init, BiFunction<T, T, T> f){
		return new Publisher<T>() {
			
			@Override
			public void subscribe(Subscriber<? super T> subscriber) {
				publisher.subscribe(new DelegateSub<T>(subscriber) {
					T sum = init;
					
					@Override
					public void onNext(T t) {
						sum = f.apply(sum, t);
					}

					@Override
					public void onComplete() {
						super.onNext(sum);
						super.onComplete();
					}
					
				});
			}
		};
	}
	
	public static <T> Publisher<T> mapPub(Publisher<T> publisher, Function<T, ? extends T> f){
		return new Publisher<T>() {
			
			@Override
			public void subscribe(Subscriber<? super T> subscriber) {
				
				publisher.subscribe(new DelegateSub<T>(subscriber) {
					@Override
					public void onNext(T t) {
						super.onNext(f.apply(t));
					}
					
				});
			}
		};
	}

	private static class DelegateSub<T> implements Subscriber<T> {
		private final Subscriber<? super T> subscriber;

		private DelegateSub(Subscriber<? super T> subscriber) {
			this.subscriber = subscriber;
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			subscriber.onSubscribe(subscription);
		}

		@Override
		public void onNext(T t) {
			subscriber.onNext(t);
		}

		@Override
		public void onError(Throwable t) {
			subscriber.onError(t);
		}

		@Override
		public void onComplete() {
			subscriber.onComplete();
		}
	}
	
	public static <T> Subscriber<T> logSub() {
		return new Subscriber<T>() {
			
			@Override
			public void onSubscribe(Subscription s) {
				System.out.println("onSubscription");
				s.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(T t) {
				System.out.println("onNext: " + t);
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("onError: " + t);
			}

			@Override
			public void onComplete() {
				System.out.println("onComplete");
			}
		};
	}
	
	public static <T> Publisher<T> iterPub(Iterable<T> iter) {
		
		return new Publisher<T>() {
			
			@Override
			public void subscribe(Subscriber<? super T> sub) {

				sub.onSubscribe(new Subscription() {

					@Override
					public void request(long n) {

						try {
							iter.forEach(i -> sub.onNext(i));
							sub.onComplete();
						} catch (Exception e) {
							sub.onError(e);
						}
					}

					@Override
					public void cancel() {
					}

				});
			}
		};
	}
}

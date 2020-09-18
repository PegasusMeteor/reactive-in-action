package tech.selinux.reactor;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import tech.selinux.reactor.subscription.SampleSubscriber;


public class FluxTest {

	@Test
	public void fluxTest(){
		Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

		List<String> iterable = Arrays.asList("foo", "bar", "foobar");
		Flux<String> seq2 = Flux.fromIterable(iterable);
		seq1.subscribe(System.out::println);
		seq2.subscribe(System.out::println);
	}

	@Test
	public void subscribeTest01(){
		Flux<Integer> ints = Flux.range(1, 4)
				.map(i -> {
					if (i <= 3) return i;
					throw new RuntimeException("Got to 4");
				});
		ints.subscribe(System.out::println,
				error -> System.err.println("Error: " + error));
	}

	@Test
	public void subscribeTest02(){
		Flux<Integer> ints = Flux.range(1, 4);
		ints.subscribe(System.out::println,
				error -> System.err.println("Error: " + error),
				() -> System.out.println("Done"));
	}

	@Test
	public void subscribeTest03(){
		Flux<Integer> ints = Flux.range(1, 4);
		ints.subscribe(System.out::println,
				error -> System.err.println("Error: " + error),
				() -> System.out.println("Done"),
				sub -> sub.request(10));
	}


	@Test
	public void subscribeTest04(){
		SampleSubscriber<Integer> ss = new SampleSubscriber<>();
		Flux<Integer> ints = Flux.range(1, 4);
		ints.subscribe(i -> System.out.println(i),
				error -> System.err.println("Error " + error),
				() -> System.out.println("Done"),
				s -> s.request(10));
		ints.subscribe(ss);
	}

	@Test
	public void subscribeTest05() {
		Flux.range(1, 10)
				.doOnRequest(r -> System.out.println("request of " + r))
				.subscribe(new BaseSubscriber<Integer>() {

					@Override
					public void hookOnSubscribe(Subscription subscription) {
						request(2);
					}

					@Override
					public void hookOnNext(Integer integer) {
						System.out.println("Cancelling after having received " + integer);
						cancel();
					}
				});
	}

}

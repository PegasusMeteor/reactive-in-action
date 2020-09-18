package tech.selinux.reactor.subscription;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * hookOnSubscribe 和 hookOnNext 必须要重写
 * 其他的可选
 * @param <T>
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {

	@Override
	public void hookOnSubscribe(Subscription subscription) {
		System.out.println("Subscribed");
		request(1);
	}

	@Override
	public void hookOnNext(T value) {
		System.out.println(value);
		request(1);
	}
}

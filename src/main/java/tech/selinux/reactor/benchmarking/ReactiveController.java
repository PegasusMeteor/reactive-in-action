package tech.selinux.reactor.benchmarking;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reactive")
public class ReactiveController {


	@GetMapping("/string")
	public Mono<String> string(int duration) {
		return  Mono.delay(Duration.ofMillis(duration)).thenReturn("Hello SpringBoot Reactive");
	}

	@GetMapping("/json")
	public Mono<Object> json(int duration) {
		Map map = new HashMap<String,String>();
		map.put("hello","SpringBoot Reactive");
		return Mono.delay(Duration.ofMillis(duration)).thenReturn(map);
	}

}

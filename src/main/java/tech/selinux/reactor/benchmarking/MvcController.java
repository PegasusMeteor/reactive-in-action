package tech.selinux.reactor.benchmarking;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mvc")
public class MvcController {

	@GetMapping("/string")
	public String string(int duration) {
		try {
			TimeUnit.MILLISECONDS.sleep(duration);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Hello SpringBoot MVC";
	}

	@GetMapping("/json")
	public Object json(int duration) {
		try {
			TimeUnit.MILLISECONDS.sleep(duration);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map map = new HashMap<String, String>();
		map.put("hello", "SpringBoot MVC");
		return map;
	}
}

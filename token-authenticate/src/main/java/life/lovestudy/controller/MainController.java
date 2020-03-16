package life.lovestudy.controller;

import life.lovestudy.utils.ResponseResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MainController {
	
	@GetMapping("list")
	public String getData(){
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "香蕉");
		data.put("price", "3.5");
		data.put("stock", "100");
		return ResponseResultUtil.success(data);
	}
	
}

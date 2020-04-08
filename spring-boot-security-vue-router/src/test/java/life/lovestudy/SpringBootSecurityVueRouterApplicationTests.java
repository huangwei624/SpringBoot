package life.lovestudy;

import life.lovestudy.entity.Menu;
import life.lovestudy.entity.Meta;
import life.lovestudy.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootSecurityVueRouterApplicationTests {
	
	@Autowired
	MenuMapper menuMapper;
	
	@Test
	void contextLoads() {
		Menu menu = new Menu();
		menu.setPath("/emp/train");
		menu.setComponent("EmpTrain");
		menu.setIconClz("io class");
		menu.setName("员工培训");
		menu.setParentId(1);
		menu.setEnable(true);
		Meta meta = new Meta();
		meta.setKeepAlive(false);
		meta.setRequireAuth(true);
		menu.setMeta(meta);
		menuMapper.saveOne(menu);
	}

}

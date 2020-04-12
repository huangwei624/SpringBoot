package life.lovestudy;

import life.lovestudy.entity.Menu;
import life.lovestudy.entity.Meta;
import life.lovestudy.mapper.MenuMapper;
import life.lovestudy.service.MenuService;
import life.lovestudy.utils.TreeMenu;
import life.lovestudy.vo.MenuVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringBootSecurityVueRouterApplicationTests {
	
	@Autowired
	MenuMapper menuMapper;
	
	@Autowired
	private MenuService menuService;
	
	@Test
	void contextLoads() {
		Menu menu = new Menu();
		menu.setPath("/emp/train");
		menu.setComponent("EmpTrain");
		menu.setIconClz("io class");
		menu.setName("员工培训");
		menu.setParentId(1);
		menu.setIsEnable(true);
		Meta meta = new Meta();
		meta.setKeepAlive(false);
		meta.setRequireAuth(true);
		menu.setMeta(meta);
		menuMapper.saveOne(menu);
	}
	
	@Test
	void test2(){
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String encode = bcpe.encode("123456");
		System.out.println("encode="+encode);
	}
	
	@Test
	void test3(){
		List<MenuVO> allMenu = menuMapper.getAllMenu();
		allMenu.forEach(item -> {System.out.println(item.getName());});
	}
	
	@Test
	void test4(){
		List<TreeMenu> treeMenus = menuService.treeMenu(2);
		treeMenus.forEach(item -> {
			System.out.print(item.getName() + "   {");
			List<TreeMenu> children = item.getChildren();
			children.forEach(item2 -> {
				System.out.print(item2.getName() + "   ");
			});
			System.out.print("} \n");
		});
	}
	
	
}

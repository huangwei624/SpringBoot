package life.lovestudy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SimpleTest {
	@Test
	void test1(){
		int[] arr = {1, 4, 6, 7, 9};
		boolean is = Arrays.stream(arr).boxed().collect(Collectors.toList()).contains(4);
		System.out.println("is="+is);
		System.out.println(Arrays.asList(arr).contains(4));;
	}
}

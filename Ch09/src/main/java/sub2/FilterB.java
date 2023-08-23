package sub2;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterB implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//필터 처리될 로직 수행
		System.out.println("FilterB doFilter()...");
		
		//인코딩 처리(개별페이지마다 인코딩처리 안해줘도 됨)
		request.setCharacterEncoding("UTF-8");
		
		//다음 필터 호출, 필터가 없으면 최종 자원 요청함
		chain.doFilter(request, response);
		
	}

}

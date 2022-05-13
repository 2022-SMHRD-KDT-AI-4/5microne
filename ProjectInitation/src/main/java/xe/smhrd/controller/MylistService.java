package xe.smhrd.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xe.smhrd.model.BoardDAO;
import xe.smhrd.model.BoardVO;
import xe.smhrd.model.InviteDAO;
import xe.smhrd.model.InviteVO;
import xe.smhrd.model.MemberVO;

@WebServlet("/MylistService")
public class MylistService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		로그인 정보 불러오기
		HttpSession session = request.getSession();
		
//		아이디 가져오기
		MemberVO vo = (MemberVO) session.getAttribute("vo");
		String m_id;
		if(vo==null) {
			m_id = null;
		}else {
			m_id = vo.getM_id();
		}

		InviteDAO dao = new InviteDAO();	
		
		if(m_id != null) {
			List<InviteVO> list = dao.selectMylist(m_id);
			System.out.println("------" + m_id + "의 작성 목록------");
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			session.setAttribute("mylist", list);
//		List<InviteVO> mylist = (List<InviteVO>) session.getAttribute("mylist");
//		System.out.println("------테스트2-------");
//		for(int i = 0; i < mylist.size(); i++) {
//			System.out.println(mylist.get(i));
//		}	
			RequestDispatcher rd = request.getRequestDispatcher("MyPartylist.jsp");
			rd.forward(request, response);
		}else {
			System.out.println("Error");
			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		}
		
	}

}

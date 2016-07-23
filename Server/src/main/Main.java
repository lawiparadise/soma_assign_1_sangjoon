package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

import controll.DAOAll;
import controll.MyComparator;
import model.DTOAll;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public int[] rank;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// System.out.println("signUpTime :
		// "+request.getParameter("signUpTime")+"\nplayTime :
		// "+request.getParameter("playTime")+"\nendTime :
		// "+request.getParameter("endTime"));

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		//
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		
		// 요청에 대한 응답 객체
		PrintWriter out = response.getWriter();

		DAOAll dao = new DAOAll();
		dao.dbConn();
		dao.download();
		dao.close();

		String kind = request.getParameter("kind");
		if (String.valueOf(kind).equals("first")) {
			String col2 = request.getParameter("id");
			String col3 = request.getParameter("name");
			String col4 = request.getParameter("img");
			// System.out.println("name : "+col3);
			// String col3 = "¿ÖÁö¿Ö";
			// String col3 = "모지";
			String col5 = request.getParameter("first_install_time");
			System.out.println("name : " + col3 + ", ins : " + col4);
			dao.dbConn();
			dao.insert(col2, col3, col4, col5);
			dao.close();
		} else if (String.valueOf(kind).equals("rank")) {
//			System.out.println(dao.getModelList().get(0).getName());
//			out.println(dao.getModelList().get(0).getName());
			
			//
			int len = dao.getModelList().size();
			Double[] list = new Double[len];
			for (int i = 0; i < len; i++) {
				list[i] = Double.valueOf(dao.getModelList().get(i).getScore()).doubleValue();
			}
			Arrays.sort(list, new MyComparator());

			rank = new int[len];
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					if (list[i] == Double.valueOf(dao.getModelList().get(j).getScore()).doubleValue()) {
						// System.out.println(i + " : "+j);
						rank[i] = j;

					}
				}
			}
			
			for(int i=0 ; i< len; i++){
				
				out.println((i+1)+"위 : "+dao.getModelList().get(rank[i]).getName()+", 점수 : "+dao.getModelList().get(rank[i]).getScore());
			}
			
		} else if (String.valueOf(kind).equals("friend")) {
			
			
			int len = dao.getModelList().size();
			Gson gson = new Gson();
//			String[] list = new String[len];
			ArrayList<DTOAll> list = new ArrayList<>();
			for(int i=0 ; i<len ; i++){
//				list[i] = dao.getModelList().get(i).getName();
				DTOAll dto = new DTOAll();
				dto.setNum(dao.getModelList().get(i).getNum());
				dto.setId(dao.getModelList().get(i).getId());
				dto.setName(dao.getModelList().get(i).getName());
				dto.setImg(dao.getModelList().get(i).getImg());
				dto.setFriend(dao.getModelList().get(i).getFriend());
				dto.setItem(dao.getModelList().get(i).getItem());
				dto.setMoney(dao.getModelList().get(i).getMoney());
				dto.setReg_id(dao.getModelList().get(i).getReg_id());
				list.add(dto);				
			}
			
			String json = gson.toJson(list);
			
//			out.println((i+1)+"위 : "+dao.getModelList().get(rank[i]).getName()+", 점수 : "+dao.getModelList().get(rank[i]).getScore());
			System.out.println("{\"list\":"+json+"}");
			out.println("{\"list\":"+json+"}");
//			out.println(json);
		}

		/*
		 * for (int i = 0; i < dao.getModelList().size(); i++) {
		 * System.out.println(i + " : " + dao.getModelList().get(i).getName());
		 * }
		 */

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//
		
		//
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		
		// 요청에 대한 응답 객체
		PrintWriter out = response.getWriter();
		
		DAOAll dao = new DAOAll();
		dao.dbConn();
		dao.download();
		dao.close();

		/*
		 * for (int i = 0; i < dao.getModelList().size(); i++) {
		 * System.out.println(i + " : " + dao.getModelList().get(i).getId()); }
		 */

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// debuging
		System.out.println(jb.toString());

		DTOAll dto = new DTOAll();
		Gson gSon = null;
		try {
			gSon = new Gson();
			dto = gSon.fromJson(jb.toString(), DTOAll.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String kind = dto.getKind();
		if (String.valueOf(kind).equals("first")) {
			String col2 = dto.getId();

			boolean existSameId = false;
			for (int i = 0; i < dao.getModelList().size(); i++) {
				if ((dao.getModelList().get(i).getId()).equals(col2)) {
					existSameId = true;
					break;
				}
			}
			if (!existSameId) {
				String col3 = dto.getName();
				String col4 = dto.getImg();
				// String col3 = "상준";
				String col5 = dto.getFirst_install_time();
				String col14 = dto.getReg_id();
				// System.out.println("name : " + col3 + ", ins : " + col4);
				dao.dbConn();
				dao.insert(col2, col3, col4, col5);
				dao.update(col2, "score", "0.0");
				dao.update(col2, "friend", "a,b");
				dao.update(col2, "item", "null");
				dao.update(col2, "money", "10000");
				dao.update(col2, "reg_id", col14);
				dao.close();
			}

		} else if (String.valueOf(kind).equals("score")) {

			String col2 = dto.getId();
			String col6 = dto.getSignup_time();
			String col7 = dto.getPlay_time();
			String col8 = dto.getEnd_time();
			String col9 = dto.getScore();
			// System.out.println("col2 : "+col2);

			for (int i = 0; i < dao.getModelList().size(); i++) {
				if ((dao.getModelList().get(i).getId()).equals(col2)) {
					String tempMon = dao.getModelList().get(i).getMoney();
					System.out.println("tempMOn : "+tempMon);
					
					int temp = Integer.parseInt(tempMon);
					Double dou = Double.valueOf(col9).doubleValue();
					int c = Integer.parseInt(String.valueOf(Math.round(dou)));
//					System.out.println(c*10);
					
					dao.dbConn();
					dao.update(col2, DAOAll.column6, col6);
					dao.update(col2, DAOAll.column7, col7);
					dao.update(col2, DAOAll.column8, col8);
					dao.update(col2, DAOAll.column9, col9);
					
					dao.update(col2, DAOAll.column13, Integer.toString(temp+(c*10)));
					dao.close();

					break;
				}
			}

			//
			int len = dao.getModelList().size();
			Double[] list = new Double[len];
			for (int i = 0; i < len; i++) {
				list[i] = Double.valueOf(dao.getModelList().get(i).getScore()).doubleValue();
			}
			Arrays.sort(list, new MyComparator());

			rank = new int[len];
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					if (list[i] == Double.valueOf(dao.getModelList().get(j).getScore()).doubleValue()) {
						// System.out.println(i + " : "+j);
						rank[i] = j;

					}
				}
			}

			dao.dbConn();
			for (int i = 0; i < len; i++) {
				dao.update(dao.getModelList().get(rank[i]).getId(), DAOAll.column10, Integer.toString(i + 1));
			}
			dao.close();
			/*
			 * for(int i=0 ; i<len ; i++){ System.out.println("rank : "+i+", "
			 * +dao.getModelList().get(rank[i]).getId()+", score : "+
			 * dao.getModelList().get(rank[i]).getScore()); }
			 */

			//
			/*
			 * double max =
			 * Double.valueOf(dao.getModelList().get(0).getScore()).doubleValue(
			 * ); // double max = Double.valueOf("0.0").doubleValue(); for (int
			 * i = 0; i < dao.getModelList().size(); i++) { double temp =
			 * Double.valueOf(dao.getModelList().get(i).getScore()).doubleValue(
			 * ); // double temp = Double.valueOf("0.5").doubleValue(); if (temp
			 * > max) { max = temp; } } System.out.println(max);
			 */
		} else if (String.valueOf(kind).equals("addFriend")) {
			String col2 = dto.getId();
			String col11 = dto.getFriend();
			
			String name = "";
			for (int i = 0; i < dao.getModelList().size(); i++) {
				if ((dao.getModelList().get(i).getId()).equals(col2)) {
					name = dao.getModelList().get(i).getName();
					break;
				}
			}
		
			for (int i = 0; i < dao.getModelList().size(); i++) {
				if ((dao.getModelList().get(i).getId()).equals(col11)) {
					push(dao.getModelList().get(i).getReg_id(), "addFriend", name);
					System.out.println("push"+dao.getModelList().get(i).getReg_id());
					break;
				}

			}
			for (int i = 0; i < dao.getModelList().size(); i++) {
				if ((dao.getModelList().get(i).getId()).equals(col2)) {
					String temp = dao.getModelList().get(i).getFriend();
					dao.dbConn();
					dao.update(col2, DAOAll.column11, temp+","+col11);
					dao.close();
					break;
				}
				
			}
		} else if (String.valueOf(kind).equals("present")) {
			String col2 = dto.getId();
			String col11 = dto.getFriend();
			
			System.out.println("col2 : "+col2+", col11 : "+col11);
			
			String name = "";
			for (int i = 0; i < dao.getModelList().size(); i++) {
				if ((dao.getModelList().get(i).getId()).equals(col2)) {
					name = dao.getModelList().get(i).getName();
					System.out.println("name : "+name);
					break;
				}
			}
			
			String temp = "";
			
			for (int i = 0; i < dao.getModelList().size(); i++) {
				System.out.println("for"+i);
				if ((dao.getModelList().get(i).getId()).equals(col11)) {
					push(dao.getModelList().get(i).getReg_id(), "present", name);
					temp = dao.getModelList().get(i).getItem();
					System.out.println("if equls col 11"+name);
					break;
				}

			}
			for (int i = 0; i < dao.getModelList().size(); i++) {
				if ((dao.getModelList().get(i).getId()).equals(col2)) {
					
					String tempMon = dao.getModelList().get(i).getMoney();
					int mon = Integer.parseInt(tempMon);
					mon-=1000;
					dao.dbConn();
					dao.update(col2, DAOAll.column13, Integer.toString(mon) );
					dao.update(col11, DAOAll.column12, temp+",star");
					dao.close();

					break;
				}
			}
			
			dao = new DAOAll();
			dao.dbConn();
			dao.download();
			dao.close();
			
			int len = dao.getModelList().size();
			Gson gson = new Gson();
//			String[] list = new String[len];
			ArrayList<DTOAll> list = new ArrayList<>();
			for(int i=0 ; i<len ; i++){
//				list[i] = dao.getModelList().get(i).getName();
				DTOAll dto2 = new DTOAll();
				dto2.setNum(dao.getModelList().get(i).getNum());
				dto2.setId(dao.getModelList().get(i).getId());
				dto2.setName(dao.getModelList().get(i).getName());
				dto2.setImg(dao.getModelList().get(i).getImg());
				dto2.setFriend(dao.getModelList().get(i).getFriend());
				dto2.setItem(dao.getModelList().get(i).getItem());
				dto2.setMoney(dao.getModelList().get(i).getMoney());
				dto2.setReg_id(dao.getModelList().get(i).getReg_id());
				list.add(dto2);				
			}
			
			String json = gson.toJson(list);
			
//			System.out.println("{\"list\":"+json+"}");
			out.println("{\"list\":"+json+"}");
		}
		
		/*
		 * for(int i=0 ; i<dao.getModelList().size() ; i++){
		 * System.out.println(dao.getModelList().get(i).getName()); }
		 */

	}
	
	public void push(String regId, String con, String name){
		Sender sender = new Sender("AIzaSyAPFUWbqHsU1dC08JCGET-tjxxGL-8paCQ"); // 서버 API Key 입력
//		String regId = "ccqEEqmNi5A:APA91bFNNgvpE3IFxeqfOa3BZ-XDOjZqEe3mSUO3aEEG-HaKEb-wNHxuVC8_EqSeXuvhA7kqE94xj5FN8sEwoc5_1SDSWfKpmIzdBq_Ocw4kk4jFHp2s60yek3WfYCYbfn7LsER58RS4";
		Message.Builder builder = new Message.Builder();
		
		if(String.valueOf(con).equals("addFriend")){
			builder.addData("title", "친구 추가");
			builder.addData("message", name+"님이 당신을 친구로 추가했습니다.");
		} else if(String.valueOf(con).equals("present")){
			builder.addData("title", "선물");
			builder.addData("message", name+"님이 당신에게 별을 보냈습니다.");
		}

		Message message = builder.build();
		
		List<String> list = new ArrayList<String>();
		list.add(regId);
		MulticastResult multiResult;

		try {
			multiResult = sender.send(message, list, 5);
			if (multiResult != null) {
				List<Result> resultList = multiResult.getResults();
				for (Result result : resultList) {
					System.out.println(result.getMessageId());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

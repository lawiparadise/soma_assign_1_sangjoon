package model;

import java.io.Serializable;

/*
 * DTOQuest 교수님으로부터 받은 질문, 보기와 답변에 대한 정보를 가짐
 */
public class DTOAll implements Serializable{
	
	String kind;
	
	int num;
	String id;
	String name;
	String img;
	String first_install_time;
	String signup_time;
	String play_time;
	String end_time;
	String score;
	String rank;
	String friend;
	String item;
	String money;
	String reg_id;
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getFirst_install_time() {
		return first_install_time;
	}
	public void setFirst_install_time(String first_install_time) {
		this.first_install_time = first_install_time;
	}
	public String getSignup_time() {
		return signup_time;
	}
	public void setSignup_time(String signup_time) {
		this.signup_time = signup_time;
	}
	public String getPlay_time() {
		return play_time;
	}
	public void setPlay_time(String play_time) {
		this.play_time = play_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	

	

	
	
}

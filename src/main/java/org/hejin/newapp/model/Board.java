package org.hejin.newapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CMM_BOARD")
public class Board {

	@Id
	@GeneratedValue
	@Getter
	private Integer id;
	@Getter @Setter
	private String title;
	@Getter @Setter
	private String content;
	@Getter @Setter
	private Date wDate;
	@Getter @Setter
	private Date mDate;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Board[")
			.append("id = ").append(id).append(", ")
			.append("title = ").append(title).append(", ")
			.append("wDate = ").append(wDate).append(", ")
			.append("mDate = ").append(mDate)//.append(", ")
		.append("]");
		return sb.toString();
	}
	
}

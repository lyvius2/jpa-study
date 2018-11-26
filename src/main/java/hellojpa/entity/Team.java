package hellojpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "team") //Member.team이 주인이라는 의미.
	private List<Member> members = new ArrayList<Member>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Team{" +
				"id=" + id +
				", name='" + name + '\'' +
				", members=" + members +
				'}';
	}
}

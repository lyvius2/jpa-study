package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
	public static void main(String ars[]) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Member member = new Member();
			//member.setId(100L);
			member.setName("member1234");
			member.setTeam(team);
			member.setMemberType(MemberType.USER);

			em.persist(member);
			team.getMembers().add(member);


			// cache 날림. select 쿼리를 볼 수 있음.
			em.flush(); // DB에 반영
			em.clear(); // cache 삭제

			Member findMember = em.find(Member.class, member.getId());

			//em.close();

			Team findTeam = findMember.getTeam();
			findTeam.getName();
			System.out.println("=======================================");
			System.out.println("findTeam = " + findTeam);
			em.detach(findMember);  // 영속성 관리 안함. (자동 업데이트 안됨) 준영속 상태로 전환

			findMember.setName("T아카데미");
			//Long teamId = findMember.getTeamId();
			//Team findTeam = findMember.getTeam();
			//List<Member> members = findTeam.getMembers();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}

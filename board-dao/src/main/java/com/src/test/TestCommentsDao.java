package com.src.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.src.board.dao.UserDao;

public class TestCommentsDao {	
public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//	Item item=new Item();
//	item.setCategory(CategoryEnum.BUG);
//	item.setColor("123");
//	item.setDescription("desc");
//	item.setPoints("1");
//	item.setTitle("first item");
//	item.setDueDate(new Date());
//	Comment comment=new Comment();
//	comment.setComment("this is My Comment");
//	comment.setMember(1l);
//	comment.setItem(item);
//	List<Comment> comments=Collections.singletonList(comment);
//	item.setComments(comments);
//	
//	Attachment attachment=new Attachment();
//	attachment.setAttachmentOwner(1l);
//	attachment.setItem(item);
//	attachment.setFileName("file.txt");
//	attachment.setSystemFileName("dasdkjhk");
//	attachment.setTitle("firstAttachment");
//	item.setAttachments(Collections.singletonList(attachment));
//	
//	Activity activity=new Activity();
//	activity.setActivityDesc("new Activity");
//	activity.setItem(item);
//	activity.setMemberId(1l);
//	
//	item.setActivities(Collections.singletonList(activity));
	//ItemDao dao=(ItemDao) context.getBean("itemDao");
//	Board board=new Board();
//	board.setName("new Board");
//	board.setStatus(BoardStatusEnum.INACTIVE.toString());
//	dao.addItem(board);
	
//	Member member=new Member();
//	member.setIsOwner(true);
//	MemberIdPk pk=new MemberIdPk();
//	pk.setBoardId(1l);
//	pk.setMemberId(1l);
//	member.setMemberId(pk);
//	dao.addItem(member);
//	System.out.println(dao.getItem(pk));
//	
//	OwnerDao dao1=(OwnerDao) context.getBean("ownerDao");
//	Owner owner=new Owner();
//	OwnerIdPk pk1=new OwnerIdPk();
//	pk1.setBoardId(1l);
//	pk1.setUserId(1l);
//	owner.setOwnerId(pk1);
//	owner.setOwnerName("New_OWNER");
//	dao1.addOwner(owner);
//	System.out.println(dao1.getOwner(pk1));
	
	UserDao dao=(UserDao) context.getBean("userDao");

	dao.find("1");
}

}

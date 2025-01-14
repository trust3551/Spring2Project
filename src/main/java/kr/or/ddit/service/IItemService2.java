package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.Item;
import kr.or.ddit.vo.Item2;

public interface IItemService2 {

	public void register(Item2 item);
	public List<Item2> list();
	public Item2 read(int itemId);
	public void modify(Item2 item);
	public String getPicture(int itemId);
	public String getPicture2(int itemId);
	public void remove(int itemId);

}

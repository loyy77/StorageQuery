package org.storage.biz;

import java.util.List;

import org.storage.dao.StorageDao;
import org.storage.models.Storage;

public class StorageBiz {
	private StorageDao sd=new StorageDao();
	public List<Storage> listOrderByNumber() {
		
		return sd.listOrderByItem("number");
	}

	public List<Storage> listOrderByMakeTime() {
		return sd.listOrderByItem("makeTime");
	}

	public List<Storage> listOrderByName() {
		return sd.listOrderByItem("name");
	}

	public List<Storage> listOrderByPrice() {
		return sd.listOrderByItem("price");
	}

	public List<Storage> listByType(String typeStr) {
	return sd.listByType(typeStr);
	}
	
	public static void main(String[] args) {
		System.out.println(new StorageBiz().listByType("clothing").size());
	
	}

}

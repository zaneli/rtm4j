package com.zaneli.rtm4j.model.lists;

import java.util.ArrayList;

import com.zaneli.rtm4j.model.Model;
import com.zaneli.rtm4j.model.Rsp;

public class ListsGetListRsp extends Rsp {

	private java.util.List<List> lists;

	public ListsGetListRsp() {
		lists = new ArrayList<List>();
	}
	public List[] getLists() {
		return lists.toArray(new List[lists.size()]);
	}
	public void addList(List list) {
		lists.add(list);
	}

	public static class List extends Model {
		private String id;
		private String name;
		private String deleted;
		private String locked;
		private String archived;
		private String position;
		private String smart;

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
		public String getDeleted() {
			return deleted;
		}
		public void setDeleted(String deleted) {
			this.deleted = deleted;
		}
		public String getLocked() {
			return locked;
		}
		public void setLocked(String locked) {
			this.locked = locked;
		}
		public String getArchived() {
			return archived;
		}
		public void setArchived(String archived) {
			this.archived = archived;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getSmart() {
			return smart;
		}
		public void setSmart(String smart) {
			this.smart = smart;
		}
	}
}

package org.ais.hr.fileupload.dto;

import java.util.List;

public class Menu {

	private String label;
	private String icon;
	private String to;

	private String authorizeAllowed;

	private String hideYn;

	private String insertAllowed;

	private String selectAllowed;

	private String updateAllowed;

	private List<Menu> items = null;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<Menu> getItems() {
		return items;
	}

	public void setItems(List<Menu> items) {
		this.items = items;
	}

	public String getAuthorizeAllowed() {
		return authorizeAllowed;
	}

	public void setAuthorizeAllowed(String authorizeAllowed) {
		this.authorizeAllowed = authorizeAllowed;
	}

	public String getHideYn() {
		return hideYn;
	}

	public void setHideYn(String hideYn) {
		this.hideYn = hideYn;
	}

	public String getInsertAllowed() {
		return insertAllowed;
	}

	public void setInsertAllowed(String insertAllowed) {
		this.insertAllowed = insertAllowed;
	}

	public String getSelectAllowed() {
		return selectAllowed;
	}

	public void setSelectAllowed(String selectAllowed) {
		this.selectAllowed = selectAllowed;
	}

	public String getUpdateAllowed() {
		return updateAllowed;
	}

	public void setUpdateAllowed(String updateAllowed) {
		this.updateAllowed = updateAllowed;
	}
}

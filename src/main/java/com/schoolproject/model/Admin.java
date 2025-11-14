package com.schoolproject.model;

import javax.sql.DataSource;

public class Admin {
	private DataSource dataSource;

	public Admin(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}

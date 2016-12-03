package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_supplier database table.
 * 
 */
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String contactPerson;

	private String contactTel;

	private String supplierNum;

	private String supplierAddr;

	private String supplierEmail;

	private String supplierName;

	private String supplierTel;

	public Supplier() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactTel() {
		return this.contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getSupplierNum() {
		return this.supplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
	}

	public String getSupplierAddr() {
		return this.supplierAddr;
	}

	public void setSupplierAddr(String supplierAddr) {
		this.supplierAddr = supplierAddr;
	}

	public String getSupplierEmail() {
		return this.supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierTel() {
		return this.supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

}
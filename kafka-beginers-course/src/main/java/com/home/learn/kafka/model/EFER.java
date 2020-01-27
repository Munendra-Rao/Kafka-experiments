package com.home.learn.kafka.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class EFER {
	
	private String sending_application;
	private String site;
	private LocalDate movementDate;
	private LocalTime movemenTime;
	private long recordID;
	private String movementCode;
	private String caf;
	private String vin;
	private String factoryCode;
	private String psvOrder;
	private LocalDate integrationDate;
	private LocalTime integrationTime;
	private LocalDate eventDate;
	private LocalTime eventTime;
	private String relatedCAFStatus;
	private LocalDate eliadeFunctionalDate;
	private String logisticAddressCode;
	
	
	public String getSending_application() {
		return sending_application;
	}
	public void setSending_application(String sending_application) {
		this.sending_application = sending_application;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public LocalDate getMovementDate() {
		return movementDate;
	}
	public void setMovementDate(LocalDate movementDate) {
		this.movementDate = movementDate;
	}
	public LocalTime getMovemenTime() {
		return movemenTime;
	}
	public void setMovemenTime(LocalTime movemenTime) {
		this.movemenTime = movemenTime;
	}
	public long getRecordID() {
		return recordID;
	}
	public void setRecordID(long recordID) {
		this.recordID = recordID;
	}
	public String getMovementCode() {
		return movementCode;
	}
	public void setMovementCode(String movementCode) {
		this.movementCode = movementCode;
	}
	public String getCaf() {
		return caf;
	}
	public void setCaf(String caf) {
		this.caf = caf;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public String getPsvOrder() {
		return psvOrder;
	}
	public void setPsvOrder(String psvOrder) {
		this.psvOrder = psvOrder;
	}
	public LocalDate getIntegrationDate() {
		return integrationDate;
	}
	public void setIntegrationDate(LocalDate integrationDate) {
		this.integrationDate = integrationDate;
	}
	public LocalTime getIntegrationTime() {
		return integrationTime;
	}
	public void setIntegrationTime(LocalTime integrationTime) {
		this.integrationTime = integrationTime;
	}
	public LocalDate getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
	public LocalTime getEventTime() {
		return eventTime;
	}
	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}
	public String getRelatedCAFStatus() {
		return relatedCAFStatus;
	}
	public void setRelatedCAFStatus(String relatedCAFStatus) {
		this.relatedCAFStatus = relatedCAFStatus;
	}
	public LocalDate getEliadeFunctionalDate() {
		return eliadeFunctionalDate;
	}
	public void setEliadeFunctionalDate(LocalDate eliadeFunctionalDate) {
		this.eliadeFunctionalDate = eliadeFunctionalDate;
	}
	public String getLogisticAddressCode() {
		return logisticAddressCode;
	}
	public void setLogisticAddressCode(String logisticAddressCode) {
		this.logisticAddressCode = logisticAddressCode;
	}
	@Override
	public String toString() {
		return "EFER [sending_application=" + sending_application + ", site=" + site + ", movementDate=" + movementDate
				+ ", movemenTime=" + movemenTime + ", recordID=" + recordID + ", movementCode=" + movementCode
				+ ", caf=" + caf + ", vin=" + vin + ", factoryCode=" + factoryCode + ", psvOrder=" + psvOrder
				+ ", integrationDate=" + integrationDate + ", integrationTime=" + integrationTime + ", eventDate="
				+ eventDate + ", eventTime=" + eventTime + ", relatedCAFStatus=" + relatedCAFStatus
				+ ", eliadeFunctionalDate=" + eliadeFunctionalDate + ", logisticAddressCode=" + logisticAddressCode
				+ "]";
	}
	
	

}

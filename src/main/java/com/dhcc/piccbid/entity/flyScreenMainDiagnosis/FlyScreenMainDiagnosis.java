package com.dhcc.piccbid.entity.flyScreenMainDiagnosis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_FLY_SCREEN_MAIN_DIAGNOSIS")
public class FlyScreenMainDiagnosis implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
	
	@Column(name="MAIN_DIAGNOSIS")
	private String mainDiagnosis;
	
	@Column(name="OPERATOR")
	private String operator;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_ADDITION")
	private Date dateOfAddition;
	
	@Column(name="PROJECT_ID")
	private String projectId;
	
	@Column(name="PROJECT_NAME")
	private String projectName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainDiagnosis() {
		return mainDiagnosis;
	}

	public void setMainDiagnosis(String mainDiagnosis) {
		this.mainDiagnosis = mainDiagnosis;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getDateOfAddition() {
		return dateOfAddition;
	}

	public void setDateOfAddition(Date dateOfAddition) {
		this.dateOfAddition = dateOfAddition;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}

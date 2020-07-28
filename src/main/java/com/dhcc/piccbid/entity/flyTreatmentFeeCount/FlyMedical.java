package com.dhcc.piccbid.entity.flyTreatmentFeeCount;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "T_FLYCHECK_MEDICAL", schema = "FLYCHECK", catalog = "")
public class FlyMedical {
    private String bridgeId;
    private String hisid;
    private String hospitalId;
    private String hospitalName;
    private String pLevel;
    private String bmiAreaId;
    private String bmiAreaName;
    private Time billDate;
    private Long year;
    private Long month;
    private String zyh;
    private String patientId;
    private String socialCardId;
    private String medicalRecordId;
    private String benefitType;
    private String benefitGroupId;
    private String admissionDeptName;
    private String transferDeptName;
    private String dischargeDeptName;
    private String doctorId;
    private String doctorName;
    private String patientName;
    private String patientGender;
    private Time patientBirthday;
    private Long patientAge;
    private String patientCompany;
    private String patientAddress;
    private String bedid;
    private String nbType;
    private Long nbBirthWeight;
    private Long nbInpatientWeight;
    private String claimType;
    private String ifLocalFlag;
    private Time admissionDate;
    private Time dischargeDate;
    private Long zyts;
    private String dischargeStatus;
    private Time preAdmissionDate;
    private Long totalAmount;
    private Long bmiPayAmount;
    private Long dbbx;
    private Long yljz;
    private Long gwybz;
    private Long debc;
    private Long qybc;
    private Long cash;
    private Long selfPayAmount;
    private Long selfPayIn;
    private Long selfPayOut;
    private Long bmiConveredAmount;
    private String admissionDiseaseId;
    private String admissionDiseaseName;
    private String dischargeDiseaseIdMain;
    private String dischargeDiseaseNameMain;
    private String accommodationFee;
    private String diagnosisFee;
    private String inspectionFee;
    private String testFee;
    private String treatmentFee;
    private String nursingFee;
    private String materialFee;
    private String westernMedicineFee;
    private String chineseMedicineYinpian;
    private String chineseMedicineForm;
    private String consultationFee;
    private String registrationFee;
    private String otherFee;
    private Long ybPayType;
    private String drgsCode;
    private String drgsName;
    private String sysStatus;
    private Long stayTimes;

    @Basic
    @Column(name = "BRIDGE_ID", nullable = true, length = 320)
    public String getBridgeId() {
        return bridgeId;
    }

    public void setBridgeId(String bridgeId) {
        this.bridgeId = bridgeId;
    }

    @Id
    @Column(name = "HISID", nullable = false, length = 320)
    public String getHisid() {
        return hisid;
    }

    public void setHisid(String hisid) {
        this.hisid = hisid;
    }

    @Basic
    @Column(name = "HOSPITAL_ID", nullable = true, length = 320)
    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Basic
    @Column(name = "HOSPITAL_NAME", nullable = true, length = 320)
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Basic
    @Column(name = "P_LEVEL", nullable = true, length = 320)
    public String getpLevel() {
        return pLevel;
    }

    public void setpLevel(String pLevel) {
        this.pLevel = pLevel;
    }

    @Basic
    @Column(name = "BMI_AREA_ID", nullable = true, length = 320)
    public String getBmiAreaId() {
        return bmiAreaId;
    }

    public void setBmiAreaId(String bmiAreaId) {
        this.bmiAreaId = bmiAreaId;
    }

    @Basic
    @Column(name = "BMI_AREA_NAME", nullable = true, length = 320)
    public String getBmiAreaName() {
        return bmiAreaName;
    }

    public void setBmiAreaName(String bmiAreaName) {
        this.bmiAreaName = bmiAreaName;
    }

    @Basic
    @Column(name = "BILL_DATE", nullable = true)
    public Time getBillDate() {
        return billDate;
    }

    public void setBillDate(Time billDate) {
        this.billDate = billDate;
    }

    @Basic
    @Column(name = "YEAR", nullable = true, precision = 0)
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    @Basic
    @Column(name = "MONTH", nullable = true, precision = 0)
    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    @Basic
    @Column(name = "ZYH", nullable = true, length = 320)
    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }

    @Basic
    @Column(name = "PATIENT_ID", nullable = true, length = 320)
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "SOCIAL_CARD_ID", nullable = true, length = 320)
    public String getSocialCardId() {
        return socialCardId;
    }

    public void setSocialCardId(String socialCardId) {
        this.socialCardId = socialCardId;
    }

    @Basic
    @Column(name = "MEDICAL_RECORD_ID", nullable = true, length = 320)
    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    @Basic
    @Column(name = "BENEFIT_TYPE", nullable = true, length = 320)
    public String getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }

    @Basic
    @Column(name = "BENEFIT_GROUP_ID", nullable = true, length = 320)
    public String getBenefitGroupId() {
        return benefitGroupId;
    }

    public void setBenefitGroupId(String benefitGroupId) {
        this.benefitGroupId = benefitGroupId;
    }

    @Basic
    @Column(name = "ADMISSION_DEPT_NAME", nullable = true, length = 320)
    public String getAdmissionDeptName() {
        return admissionDeptName;
    }

    public void setAdmissionDeptName(String admissionDeptName) {
        this.admissionDeptName = admissionDeptName;
    }

    @Basic
    @Column(name = "TRANSFER_DEPT_NAME", nullable = true, length = 320)
    public String getTransferDeptName() {
        return transferDeptName;
    }

    public void setTransferDeptName(String transferDeptName) {
        this.transferDeptName = transferDeptName;
    }

    @Basic
    @Column(name = "DISCHARGE_DEPT_NAME", nullable = true, length = 320)
    public String getDischargeDeptName() {
        return dischargeDeptName;
    }

    public void setDischargeDeptName(String dischargeDeptName) {
        this.dischargeDeptName = dischargeDeptName;
    }

    @Basic
    @Column(name = "DOCTOR_ID", nullable = true, length = 320)
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "DOCTOR_NAME", nullable = true, length = 320)
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Basic
    @Column(name = "PATIENT_NAME", nullable = true, length = 320)
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Basic
    @Column(name = "PATIENT_GENDER", nullable = true, length = 320)
    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    @Basic
    @Column(name = "PATIENT_BIRTHDAY", nullable = true)
    public Time getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(Time patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    @Basic
    @Column(name = "PATIENT_AGE", nullable = true, precision = 0)
    public Long getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Long patientAge) {
        this.patientAge = patientAge;
    }

    @Basic
    @Column(name = "PATIENT_COMPANY", nullable = true, length = 320)
    public String getPatientCompany() {
        return patientCompany;
    }

    public void setPatientCompany(String patientCompany) {
        this.patientCompany = patientCompany;
    }

    @Basic
    @Column(name = "PATIENT_ADDRESS", nullable = true, length = 320)
    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    @Basic
    @Column(name = "BEDID", nullable = true, length = 320)
    public String getBedid() {
        return bedid;
    }

    public void setBedid(String bedid) {
        this.bedid = bedid;
    }

    @Basic
    @Column(name = "NB_TYPE", nullable = true, length = 320)
    public String getNbType() {
        return nbType;
    }

    public void setNbType(String nbType) {
        this.nbType = nbType;
    }

    @Basic
    @Column(name = "NB_BIRTH_WEIGHT", nullable = true, precision = 0)
    public Long getNbBirthWeight() {
        return nbBirthWeight;
    }

    public void setNbBirthWeight(Long nbBirthWeight) {
        this.nbBirthWeight = nbBirthWeight;
    }

    @Basic
    @Column(name = "NB_INPATIENT_WEIGHT", nullable = true, precision = 0)
    public Long getNbInpatientWeight() {
        return nbInpatientWeight;
    }

    public void setNbInpatientWeight(Long nbInpatientWeight) {
        this.nbInpatientWeight = nbInpatientWeight;
    }

    @Basic
    @Column(name = "CLAIM_TYPE", nullable = true, length = 320)
    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    @Basic
    @Column(name = "IF_LOCAL_FLAG", nullable = true, length = 320)
    public String getIfLocalFlag() {
        return ifLocalFlag;
    }

    public void setIfLocalFlag(String ifLocalFlag) {
        this.ifLocalFlag = ifLocalFlag;
    }

    @Basic
    @Column(name = "ADMISSION_DATE", nullable = true)
    public Time getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Time admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Basic
    @Column(name = "DISCHARGE_DATE", nullable = true)
    public Time getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Time dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    @Basic
    @Column(name = "ZYTS", nullable = true, precision = 0)
    public Long getZyts() {
        return zyts;
    }

    public void setZyts(Long zyts) {
        this.zyts = zyts;
    }

    @Basic
    @Column(name = "DISCHARGE_STATUS", nullable = true, length = 320)
    public String getDischargeStatus() {
        return dischargeStatus;
    }

    public void setDischargeStatus(String dischargeStatus) {
        this.dischargeStatus = dischargeStatus;
    }

    @Basic
    @Column(name = "PRE_ADMISSION_DATE", nullable = true)
    public Time getPreAdmissionDate() {
        return preAdmissionDate;
    }

    public void setPreAdmissionDate(Time preAdmissionDate) {
        this.preAdmissionDate = preAdmissionDate;
    }

    @Basic
    @Column(name = "TOTAL_AMOUNT", nullable = true, precision = 0)
    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "BMI_PAY_AMOUNT", nullable = true, precision = 0)
    public Long getBmiPayAmount() {
        return bmiPayAmount;
    }

    public void setBmiPayAmount(Long bmiPayAmount) {
        this.bmiPayAmount = bmiPayAmount;
    }

    @Basic
    @Column(name = "DBBX", nullable = true, precision = 0)
    public Long getDbbx() {
        return dbbx;
    }

    public void setDbbx(Long dbbx) {
        this.dbbx = dbbx;
    }

    @Basic
    @Column(name = "YLJZ", nullable = true, precision = 0)
    public Long getYljz() {
        return yljz;
    }

    public void setYljz(Long yljz) {
        this.yljz = yljz;
    }

    @Basic
    @Column(name = "GWYBZ", nullable = true, precision = 0)
    public Long getGwybz() {
        return gwybz;
    }

    public void setGwybz(Long gwybz) {
        this.gwybz = gwybz;
    }

    @Basic
    @Column(name = "DEBC", nullable = true, precision = 0)
    public Long getDebc() {
        return debc;
    }

    public void setDebc(Long debc) {
        this.debc = debc;
    }

    @Basic
    @Column(name = "QYBC", nullable = true, precision = 0)
    public Long getQybc() {
        return qybc;
    }

    public void setQybc(Long qybc) {
        this.qybc = qybc;
    }

    @Basic
    @Column(name = "CASH", nullable = true, precision = 0)
    public Long getCash() {
        return cash;
    }

    public void setCash(Long cash) {
        this.cash = cash;
    }

    @Basic
    @Column(name = "SELF_PAY_AMOUNT", nullable = true, precision = 0)
    public Long getSelfPayAmount() {
        return selfPayAmount;
    }

    public void setSelfPayAmount(Long selfPayAmount) {
        this.selfPayAmount = selfPayAmount;
    }

    @Basic
    @Column(name = "SELF_PAY_IN", nullable = true, precision = 0)
    public Long getSelfPayIn() {
        return selfPayIn;
    }

    public void setSelfPayIn(Long selfPayIn) {
        this.selfPayIn = selfPayIn;
    }

    @Basic
    @Column(name = "SELF_PAY_OUT", nullable = true, precision = 0)
    public Long getSelfPayOut() {
        return selfPayOut;
    }

    public void setSelfPayOut(Long selfPayOut) {
        this.selfPayOut = selfPayOut;
    }

    @Basic
    @Column(name = "BMI_CONVERED_AMOUNT", nullable = true, precision = 0)
    public Long getBmiConveredAmount() {
        return bmiConveredAmount;
    }

    public void setBmiConveredAmount(Long bmiConveredAmount) {
        this.bmiConveredAmount = bmiConveredAmount;
    }

    @Basic
    @Column(name = "ADMISSION_DISEASE_ID", nullable = true, length = 320)
    public String getAdmissionDiseaseId() {
        return admissionDiseaseId;
    }

    public void setAdmissionDiseaseId(String admissionDiseaseId) {
        this.admissionDiseaseId = admissionDiseaseId;
    }

    @Basic
    @Column(name = "ADMISSION_DISEASE_NAME", nullable = true, length = 320)
    public String getAdmissionDiseaseName() {
        return admissionDiseaseName;
    }

    public void setAdmissionDiseaseName(String admissionDiseaseName) {
        this.admissionDiseaseName = admissionDiseaseName;
    }

    @Basic
    @Column(name = "DISCHARGE_DISEASE_ID_MAIN", nullable = true, length = 320)
    public String getDischargeDiseaseIdMain() {
        return dischargeDiseaseIdMain;
    }

    public void setDischargeDiseaseIdMain(String dischargeDiseaseIdMain) {
        this.dischargeDiseaseIdMain = dischargeDiseaseIdMain;
    }

    @Basic
    @Column(name = "DISCHARGE_DISEASE_NAME_MAIN", nullable = true, length = 320)
    public String getDischargeDiseaseNameMain() {
        return dischargeDiseaseNameMain;
    }

    public void setDischargeDiseaseNameMain(String dischargeDiseaseNameMain) {
        this.dischargeDiseaseNameMain = dischargeDiseaseNameMain;
    }

    @Basic
    @Column(name = "ACCOMMODATION_FEE", nullable = true, length = 320)
    public String getAccommodationFee() {
        return accommodationFee;
    }

    public void setAccommodationFee(String accommodationFee) {
        this.accommodationFee = accommodationFee;
    }

    @Basic
    @Column(name = "DIAGNOSIS_FEE", nullable = true, length = 320)
    public String getDiagnosisFee() {
        return diagnosisFee;
    }

    public void setDiagnosisFee(String diagnosisFee) {
        this.diagnosisFee = diagnosisFee;
    }

    @Basic
    @Column(name = "INSPECTION_FEE", nullable = true, length = 320)
    public String getInspectionFee() {
        return inspectionFee;
    }

    public void setInspectionFee(String inspectionFee) {
        this.inspectionFee = inspectionFee;
    }

    @Basic
    @Column(name = "TEST_FEE", nullable = true, length = 320)
    public String getTestFee() {
        return testFee;
    }

    public void setTestFee(String testFee) {
        this.testFee = testFee;
    }

    @Basic
    @Column(name = "TREATMENT_FEE", nullable = true, length = 320)
    public String getTreatmentFee() {
        return treatmentFee;
    }

    public void setTreatmentFee(String treatmentFee) {
        this.treatmentFee = treatmentFee;
    }

    @Basic
    @Column(name = "NURSING_FEE", nullable = true, length = 320)
    public String getNursingFee() {
        return nursingFee;
    }

    public void setNursingFee(String nursingFee) {
        this.nursingFee = nursingFee;
    }

    @Basic
    @Column(name = "MATERIAL_FEE", nullable = true, length = 320)
    public String getMaterialFee() {
        return materialFee;
    }

    public void setMaterialFee(String materialFee) {
        this.materialFee = materialFee;
    }

    @Basic
    @Column(name = "WESTERN_MEDICINE_FEE", nullable = true, length = 320)
    public String getWesternMedicineFee() {
        return westernMedicineFee;
    }

    public void setWesternMedicineFee(String westernMedicineFee) {
        this.westernMedicineFee = westernMedicineFee;
    }

    @Basic
    @Column(name = "CHINESE_MEDICINE_YINPIAN", nullable = true, length = 320)
    public String getChineseMedicineYinpian() {
        return chineseMedicineYinpian;
    }

    public void setChineseMedicineYinpian(String chineseMedicineYinpian) {
        this.chineseMedicineYinpian = chineseMedicineYinpian;
    }

    @Basic
    @Column(name = "CHINESE_MEDICINE_FORM", nullable = true, length = 320)
    public String getChineseMedicineForm() {
        return chineseMedicineForm;
    }

    public void setChineseMedicineForm(String chineseMedicineForm) {
        this.chineseMedicineForm = chineseMedicineForm;
    }

    @Basic
    @Column(name = "CONSULTATION_FEE", nullable = true, length = 320)
    public String getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(String consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Basic
    @Column(name = "REGISTRATION_FEE", nullable = true, length = 320)
    public String getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(String registrationFee) {
        this.registrationFee = registrationFee;
    }

    @Basic
    @Column(name = "OTHER_FEE", nullable = true, length = 320)
    public String getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    @Basic
    @Column(name = "YB_PAY_TYPE", nullable = true, precision = 0)
    public Long getYbPayType() {
        return ybPayType;
    }

    public void setYbPayType(Long ybPayType) {
        this.ybPayType = ybPayType;
    }

    @Basic
    @Column(name = "DRGS_CODE", nullable = true, length = 320)
    public String getDrgsCode() {
        return drgsCode;
    }

    public void setDrgsCode(String drgsCode) {
        this.drgsCode = drgsCode;
    }

    @Basic
    @Column(name = "DRGS_NAME", nullable = true, length = 320)
    public String getDrgsName() {
        return drgsName;
    }

    public void setDrgsName(String drgsName) {
        this.drgsName = drgsName;
    }

    @Basic
    @Column(name = "SYS_STATUS", nullable = true, length = 3)
    public String getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(String sysStatus) {
        this.sysStatus = sysStatus;
    }

    @Basic
    @Column(name = "STAY_TIMES", nullable = true, precision = 0)
    public Long getStayTimes() {
        return stayTimes;
    }

    public void setStayTimes(Long stayTimes) {
        this.stayTimes = stayTimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlyMedical that = (FlyMedical) o;
        return Objects.equals(bridgeId, that.bridgeId) &&
                Objects.equals(hisid, that.hisid) &&
                Objects.equals(hospitalId, that.hospitalId) &&
                Objects.equals(hospitalName, that.hospitalName) &&
                Objects.equals(pLevel, that.pLevel) &&
                Objects.equals(bmiAreaId, that.bmiAreaId) &&
                Objects.equals(bmiAreaName, that.bmiAreaName) &&
                Objects.equals(billDate, that.billDate) &&
                Objects.equals(year, that.year) &&
                Objects.equals(month, that.month) &&
                Objects.equals(zyh, that.zyh) &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(socialCardId, that.socialCardId) &&
                Objects.equals(medicalRecordId, that.medicalRecordId) &&
                Objects.equals(benefitType, that.benefitType) &&
                Objects.equals(benefitGroupId, that.benefitGroupId) &&
                Objects.equals(admissionDeptName, that.admissionDeptName) &&
                Objects.equals(transferDeptName, that.transferDeptName) &&
                Objects.equals(dischargeDeptName, that.dischargeDeptName) &&
                Objects.equals(doctorId, that.doctorId) &&
                Objects.equals(doctorName, that.doctorName) &&
                Objects.equals(patientName, that.patientName) &&
                Objects.equals(patientGender, that.patientGender) &&
                Objects.equals(patientBirthday, that.patientBirthday) &&
                Objects.equals(patientAge, that.patientAge) &&
                Objects.equals(patientCompany, that.patientCompany) &&
                Objects.equals(patientAddress, that.patientAddress) &&
                Objects.equals(bedid, that.bedid) &&
                Objects.equals(nbType, that.nbType) &&
                Objects.equals(nbBirthWeight, that.nbBirthWeight) &&
                Objects.equals(nbInpatientWeight, that.nbInpatientWeight) &&
                Objects.equals(claimType, that.claimType) &&
                Objects.equals(ifLocalFlag, that.ifLocalFlag) &&
                Objects.equals(admissionDate, that.admissionDate) &&
                Objects.equals(dischargeDate, that.dischargeDate) &&
                Objects.equals(zyts, that.zyts) &&
                Objects.equals(dischargeStatus, that.dischargeStatus) &&
                Objects.equals(preAdmissionDate, that.preAdmissionDate) &&
                Objects.equals(totalAmount, that.totalAmount) &&
                Objects.equals(bmiPayAmount, that.bmiPayAmount) &&
                Objects.equals(dbbx, that.dbbx) &&
                Objects.equals(yljz, that.yljz) &&
                Objects.equals(gwybz, that.gwybz) &&
                Objects.equals(debc, that.debc) &&
                Objects.equals(qybc, that.qybc) &&
                Objects.equals(cash, that.cash) &&
                Objects.equals(selfPayAmount, that.selfPayAmount) &&
                Objects.equals(selfPayIn, that.selfPayIn) &&
                Objects.equals(selfPayOut, that.selfPayOut) &&
                Objects.equals(bmiConveredAmount, that.bmiConveredAmount) &&
                Objects.equals(admissionDiseaseId, that.admissionDiseaseId) &&
                Objects.equals(admissionDiseaseName, that.admissionDiseaseName) &&
                Objects.equals(dischargeDiseaseIdMain, that.dischargeDiseaseIdMain) &&
                Objects.equals(dischargeDiseaseNameMain, that.dischargeDiseaseNameMain) &&
                Objects.equals(accommodationFee, that.accommodationFee) &&
                Objects.equals(diagnosisFee, that.diagnosisFee) &&
                Objects.equals(inspectionFee, that.inspectionFee) &&
                Objects.equals(testFee, that.testFee) &&
                Objects.equals(treatmentFee, that.treatmentFee) &&
                Objects.equals(nursingFee, that.nursingFee) &&
                Objects.equals(materialFee, that.materialFee) &&
                Objects.equals(westernMedicineFee, that.westernMedicineFee) &&
                Objects.equals(chineseMedicineYinpian, that.chineseMedicineYinpian) &&
                Objects.equals(chineseMedicineForm, that.chineseMedicineForm) &&
                Objects.equals(consultationFee, that.consultationFee) &&
                Objects.equals(registrationFee, that.registrationFee) &&
                Objects.equals(otherFee, that.otherFee) &&
                Objects.equals(ybPayType, that.ybPayType) &&
                Objects.equals(drgsCode, that.drgsCode) &&
                Objects.equals(drgsName, that.drgsName) &&
                Objects.equals(sysStatus, that.sysStatus) &&
                Objects.equals(stayTimes, that.stayTimes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bridgeId, hisid, hospitalId, hospitalName, pLevel, bmiAreaId, bmiAreaName, billDate, year, month, zyh, patientId, socialCardId, medicalRecordId, benefitType, benefitGroupId, admissionDeptName, transferDeptName, dischargeDeptName, doctorId, doctorName, patientName, patientGender, patientBirthday, patientAge, patientCompany, patientAddress, bedid, nbType, nbBirthWeight, nbInpatientWeight, claimType, ifLocalFlag, admissionDate, dischargeDate, zyts, dischargeStatus, preAdmissionDate, totalAmount, bmiPayAmount, dbbx, yljz, gwybz, debc, qybc, cash, selfPayAmount, selfPayIn, selfPayOut, bmiConveredAmount, admissionDiseaseId, admissionDiseaseName, dischargeDiseaseIdMain, dischargeDiseaseNameMain, accommodationFee, diagnosisFee, inspectionFee, testFee, treatmentFee, nursingFee, materialFee, westernMedicineFee, chineseMedicineYinpian, chineseMedicineForm, consultationFee, registrationFee, otherFee, ybPayType, drgsCode, drgsName, sysStatus, stayTimes);
    }
}

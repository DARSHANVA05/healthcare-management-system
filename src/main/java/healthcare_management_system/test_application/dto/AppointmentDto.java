package healthcare_management_system.test_application.dto;

public class AppointmentDto {

    public int appointmentId;
    public String patientName;
    public String doctorName;
    public String time;
    public String phoneNo;
    public String status;

    public AppointmentDto(int appointmentId,String patientName, String doctorName, String time, String phoneNo, String status) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.time = time;
        this.phoneNo = phoneNo;
        this.appointmentId = appointmentId;
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String date) {
        this.phoneNo = date;
    }


}

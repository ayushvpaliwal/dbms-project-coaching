<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">
    <div class="row justify-content-center mb-3">
        <h2>${submessage1}</h2>
    </div>
    <div class="row shadow bg-white rounded" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <tr>
                <th style="width: 40%;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%; text-align: right;">
                    <a class="btn btn-primary" href="/admin/staffs/ES${staff.employee.employeeId}/edit-staff" role="button">Edit Staff</a>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Employee ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">ES${staff.employee.employeeId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Name</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.firstName} ${staff.employee.user.middleName} ${staff.employee.user.lastName}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Username</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.username}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Email Address</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.emailAddress}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Gender</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.gender}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Date of Birth</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.dateOfBirth}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Address</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.houseNumber}, ${staff.street}, ${staff.city}, ${staff.state} - ${staff.pinCode}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Activated?</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.isActive}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Date Created</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.dateCreated}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Last Login</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.lastLoginDate} ${staff.employee.user.lastLoginTime}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Phone Numbers</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><c:forEach var="userPhoneNumber" items="${userPhoneNumbers}">
                    <div>${userPhoneNumber.phoneNumber}</div>
                </c:forEach></td>
            </tr>
        </table>
        <div class="col-12" style="text-align: center;">
            <hr>
            <h5>Employee Details</h5>
        </div>
        <table class="table table-borderless mt-4">
            <tr>
                <th style="width: 40%; text-align: center;">Basic Salary</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.basicSalary}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Join Date</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.joinDate}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">End Date</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.endDate}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">PAN Number</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.panNumber}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Account Number</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.accountNumber}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Bank Name</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.bankName}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Bank Branch</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.bankBranch}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">IFSC Code</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.ifscCode}</td>
            </tr>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
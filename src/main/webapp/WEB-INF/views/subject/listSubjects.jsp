<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-fluid custom-container">
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="div text-right">
        <a class="btn btn-primary" href="/${role}/academics/subjects/add" role="button" >Add Subject</a>
    </div>
    </sec:authorize>
    <div class="table-responsive">
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>Subject ID</th>
                    <th>Subject Name</th>
                    <th>Description</th>
                    <sec:authorize access="!hasRole('ROLE_STAFF')"><th>Study Materials</th></sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><th>Action</th></sec:authorize>
                </tr>
            </thead>
            <c:forEach items="${subjects}" var="subject">
                <tr>
                    <td>${subject.subjectId}</td>
                    <td>${subject.subjectName}</td>
                    <td>${subject.description}</td>
                    <sec:authorize access="!hasRole('ROLE_STAFF')">
                    <td><a class="btn btn-success" href="/${role}/academics/subjects/${subject.subjectId}/materials" role="button">View</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td>
                        <a class="btn btn-outline-success btn-sm" href="/${role}/academics/subjects/${subject.subjectId}"
                            role="button">View</a>
                        <a class="btn btn-outline-primary btn-sm" href="/${role}/academics/subjects/${subject.subjectId}/edit"
                            role="button">Edit</a>
                        <a class="btn btn-outline-danger btn-sm" onclick="getRequestWithConfirmation('/${role}/academics/subjects/${subject.subjectId}/delete',
                        'Do you want to delete this Subject? \nWarning! This action is destructible!')"
                            role="button">Delete</a>
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>

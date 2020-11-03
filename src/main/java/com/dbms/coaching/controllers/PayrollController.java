package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.EmployeeDao;
import com.dbms.coaching.dao.PayrollDao;
import com.dbms.coaching.models.Payroll;
import com.dbms.coaching.services.SecurityService;
import com.dbms.coaching.models.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PayrollController {
    @Autowired
    private PayrollDao payrollDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/admin/payroll")
    public String listPayrolls(Model model, String employee) {
        model.addAttribute("title", "Payroll Management");
        model.addAttribute("message", "View all the payroll");
        List<Payroll> payroll;
        if (employee != null) {
            String code = employee.substring(0, 2);
            int employeeId = Integer.parseInt(employee.substring(2));
            String role = employeeDao.getRole(employeeId);
            if (!(code == "ES" && role == "ROLE_STAFF") && (code == "ET" && role == "ROLE_TEACHER")) {
                return "redirect:/admin/payroll";
            }
            payroll = payrollDao.getAllByEmployeeId(employeeId);
        }
        else {
            payroll = payrollDao.getAll();
            List<Employee> employees = employeeDao.getAll();
            model.addAttribute("employees", employees);
        }
        model.addAttribute("payrolls", payroll);
        return "payroll/listPayrolls";
    }

    @GetMapping({ "/staff/payroll", "/teacher/payroll" })
    public String listOwnPayrolls(Model model) {
        model.addAttribute("title", "Payroll Management");
        model.addAttribute("message", "View your payroll");
        int userId = securityService.findLoggedInUserId();
        int employeeId = employeeDao.getEmployeeIdByUserId(userId);
        List<Payroll> payroll = payrollDao.getAllByEmployeeId(employeeId);
        model.addAttribute("payrolls", payroll);
        return "payroll/listPayrolls";
    }

    @GetMapping("/admin/payroll/add")
    public String addPayroll(Model model) {
        model.addAttribute("title", "Payroll Management");
        model.addAttribute("message", "Add Payroll");
        model.addAttribute("submessage1", "Add Payroll");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/payroll/add");
        List<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees", employees);
        Payroll payroll = new Payroll();
        model.addAttribute("payroll", payroll);
        return "payroll/addEditPayroll";
    }

    @PostMapping("/admin/payroll/add")
    public String addPayroll(@ModelAttribute("payroll") Payroll payroll, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Payroll Management");
            model.addAttribute("message", "Add Payroll");
            model.addAttribute("submessage1", "Add Payroll");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/payroll/add");
            List<Employee> employees = employeeDao.getAll();
            model.addAttribute("employees", employees);
            return "payroll/addEditPayroll";
        }
        payrollDao.save(payroll);
        return "redirect:/admin/payroll";
    }

    @GetMapping("/admin/payroll/{paymentRefNo}")
    public String viewPayroll(@PathVariable("paymentRefNo") String paymentRefNo, Model model) {
        model.addAttribute("title", "Payroll Management");
        model.addAttribute("message", "View Payroll");
        model.addAttribute("submessage1", "Payroll Details");
        Payroll payroll = payrollDao.get(paymentRefNo);
        model.addAttribute("payroll", payroll);
        return "payroll/viewPayroll";
    }

    @GetMapping("/admin/payroll/{paymentRefNo}/edit")
    public String editPayroll(@PathVariable("paymentRefNo") String paymentRefNo, Model model) {
        model.addAttribute("title", "Payroll Management");
        model.addAttribute("message", "Edit Payroll");
        model.addAttribute("submessage1", "Edit Payroll");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/payroll/" + paymentRefNo + "/edit");
        model.addAttribute("edit", "true");
        List<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees", employees);
        Payroll payroll = payrollDao.get(paymentRefNo);
        model.addAttribute("payroll", payroll);
        return "payroll/addEditPayroll";
    }

    @PostMapping("/admin/payroll/{paymentRefNo}/edit")
    public String editPayroll(@PathVariable("paymentRefNo") String paymentRefNo,
    @ModelAttribute("payroll") Payroll payroll, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Payroll Management");
            model.addAttribute("message", "Edit Payroll");
            model.addAttribute("submessage1", "Edit Payroll");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/payroll/" + paymentRefNo + "/edit");
            List<Employee> employees = employeeDao.getAll();
            model.addAttribute("employees", employees);
            model.addAttribute("edit", "true");
            return "payroll/addEditPayroll";
        }
        payrollDao.update(payroll);
        return "redirect:/admin/payroll";
    }

    @GetMapping("/admin/payroll/{paymentRefNo}/delete")
    public ResponseEntity<Integer> deletePayroll(@PathVariable("paymentRefNo") String paymentRefNo, Model model) {
        payrollDao.delete(paymentRefNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

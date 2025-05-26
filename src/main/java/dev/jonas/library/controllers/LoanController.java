package dev.jonas.library.controllers;

import dev.jonas.library.dtos.loan.LoanCreateDTO;
import dev.jonas.library.dtos.loan.LoanDTO;
import dev.jonas.library.services.LoanService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RestController
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/loans")
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoanDTOs();
    }

    @GetMapping("/loans/{id}")
    public LoanDTO getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @PostMapping("/loans")
    public LoanDTO createLoan(@RequestBody LoanCreateDTO dto) {
        return loanService.addLoan(dto);
    }

    @PutMapping("/loans/{id}/extend")
    public LoanDTO extendLoan(@PathVariable Long id) {
        return loanService.extendLoan(id);
    }

    @PutMapping("/loans/{id}/return")
    public LoanDTO returnLoan(@PathVariable Long id) {
        return loanService.returnLoan(id);
    }

}


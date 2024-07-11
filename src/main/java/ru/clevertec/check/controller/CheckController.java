package ru.clevertec.check.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.check.csvWriter.CheckWriter;
import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.request.CheckRequestDto;
import ru.clevertec.check.entity.response.CheckResponseDto;
import ru.clevertec.check.service.CheckService;
import ru.clevertec.check.entity.request.ProductRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private CheckService checkService;

    @Autowired
    private CheckWriter checkCsvWriter;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProductToCheck(@RequestBody CheckRequestDto requestDto) {
        List<Long> productIds = requestDto.getProducts().stream()
                .map(ProductRequestDto::getId)
                .collect(Collectors.toList());

        List<Integer> quantities = requestDto.getProducts().stream()
                .map(ProductRequestDto::getQuantity)
                .collect(Collectors.toList());

        Check check = checkService.addProductToCheck(productIds, quantities, requestDto.getDiscountCardNumber(), requestDto.getBalanceDebitCard());

        checkCsvWriter.writeCheckToCsv(check);

        return ResponseEntity.ok("Products added to the check successfully.");
    }

    @GetMapping("/{checkId}")
    public ResponseEntity<CheckResponseDto> getCheckDetails(@PathVariable Long checkId) {
        CheckResponseDto responseDto = checkService.getTotalCheckDetails(checkId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/result")
    public List<Check> getAllProducts() {
        return checkService.findAll();
    }
}

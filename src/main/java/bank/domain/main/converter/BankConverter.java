package bank.domain.main.converter;

import bank.controller.response.BankResponse;
import bank.domain.model.Bank;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BankConverter {

    private final ModelMapper mapper;

    public List<BankResponse> toListBankResponse(List<Bank> banks) {
        List<BankResponse> bankResponses = new ArrayList<>();
        banks.forEach(bank -> bankResponses.add(toBankResponse(bank)));
        return bankResponses;
    }

    public BankResponse toBankResponse(Bank bank) {
        return this.mapper.map(bank, BankResponse.class);
    }

}

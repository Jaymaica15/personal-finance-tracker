package com.julia.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.julia.finance.dto.TransactionDTO;
import com.julia.finance.model.Transaction;
import com.julia.finance.model.TransactionType;
import com.julia.finance.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public TransactionDTO toDTO(Transaction t) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(t.getId());
        dto.setDescription(t.getDescription());
        dto.setAmount(t.getAmount());
        dto.setDate(t.getDate());
        dto.setType(t.getType().name());
        dto.setCategory(t.getCategory());
        return dto;
    }

    public List<TransactionDTO> listAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public TransactionDTO create(TransactionDTO dto) {
        Transaction t = new Transaction();
        t.setDescription(dto.getDescription());
        t.setAmount(dto.getAmount());
        t.setDate(dto.getDate());
        t.setType(TransactionType.valueOf(dto.getType()));
        t.setCategory(dto.getCategory());
        repository.save(t);
        return toDTO(t);
    }

    public TransactionDTO findDtoById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public TransactionDTO update(Long id, TransactionDTO dto) {
        Transaction existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setDescription(dto.getDescription());
        existing.setAmount(dto.getAmount());
        existing.setDate(dto.getDate());
        existing.setType(TransactionType.valueOf(dto.getType()));
        existing.setCategory(dto.getCategory());
        repository.save(existing);
        return toDTO(existing);
    }

    public List<Transaction> findAll() {
        return repository.findAllByOrderByIdAsc();
    }
    
    public Transaction findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
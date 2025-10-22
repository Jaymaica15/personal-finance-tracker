package com.julia.finance.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.julia.finance.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByOrderByIdAsc();

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'INCOME'")
    BigDecimal totalIncome();

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'EXPENSE'")
    BigDecimal totalExpense();

    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t GROUP BY t.category")
    List<Object[]> resumeByCategory();
}

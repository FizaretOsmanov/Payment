package com.code.controllers;

import com.code.exception.CustomerNotException;
import com.code.exception.TransactionNotFoundException;
import com.code.exception.UserNotLogedinException;
import com.code.model.Transaction;
import com.code.model.TransactionType;
import com.code.repository.TransactionDao;
import com.code.service.TransactionServiceImpl;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {


	private final TransactionServiceImpl transactionserviceimpl;

	private final TransactionDao dao;
	
	@GetMapping("/transation_histroy/{uniqueId}")
	public ResponseEntity<List<Transaction>> viewAllTransactionHandler( @PathVariable("uniqueId") String uniqueId)
			throws CustomerNotException, UserNotLogedinException, TransactionNotFoundException{
		List<Transaction> allTransaction = transactionserviceimpl.viewAllTransaction(uniqueId);
		return new ResponseEntity<List<Transaction>>(allTransaction,HttpStatus.OK);
	}
	
	
	@GetMapping("/{from}/{to}/{id}")
	public ResponseEntity<List<Transaction>> viewTransactionByDatehandler(@PathVariable("from") String from,
			@PathVariable("to") String to,@PathVariable("id") String uniqueId)
			throws CustomerNotException, UserNotLogedinException, TransactionNotFoundException{
		List<Transaction> historyByDate= transactionserviceimpl.viewTransactionByDate(from,to,uniqueId);
		return new ResponseEntity<List<Transaction>>(historyByDate,HttpStatus.OK);
	}
	
	@GetMapping("/historybytype/{transactiontype}/{uniqueId}")
	public ResponseEntity<List<Transaction>> viewAllTransactionByTypeHandler(@PathParam("transactionType") TransactionType type,
																			 @PathVariable String uniqueId)
			throws CustomerNotException, UserNotLogedinException, TransactionNotFoundException {
		List<Transaction> TransactionType = transactionserviceimpl.viewAllTransactionByTransactionType(uniqueId, type);
		return new ResponseEntity<List<Transaction>>(TransactionType,HttpStatus.OK);
	}
	
	
}

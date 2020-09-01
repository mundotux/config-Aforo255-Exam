package com.aforo255.ms.test.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aforo255.ms.test.invoice.entity.Invoice;
import com.aforo255.ms.test.invoice.repository.InvoiceRepository;

@Service
public class InvoiceService implements IInvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepo;

	@Override
	public List<Invoice> findAll() {
		// TODO Auto-generated method stub
		return (List<Invoice>) invoiceRepo.findAll();
	}

	@Override
	public Invoice findById(Integer id) {
		// TODO Auto-generated method stub
		return invoiceRepo.findById(id).orElse(null);
	}

	@Override
	public Invoice save(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoiceRepo.save(invoice);
	}

}

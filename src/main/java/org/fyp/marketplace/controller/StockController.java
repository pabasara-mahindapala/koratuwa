/**
 * 
 */
package org.fyp.marketplace.controller;

import java.util.ArrayList;
import java.util.List;

import org.fyp.marketplace.dtos.StockDto;
import org.fyp.marketplace.model.Product;
import org.fyp.marketplace.model.Stock;
import org.fyp.marketplace.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/stock")
public class StockController {

	final StockService stockService;

	/**
	 * @param stockService 
	 */
	public StockController(StockService stockService) {
		super();
		this.stockService = stockService ; 
	}

	@GetMapping("all")
	public List<StockDto> listAllStock(@RequestParam(required = false) Long productId, @RequestParam(required = false) Long producerId) {
		try {
			return this.stockService.getAllStocksFiltered(productId, producerId);
		} catch (Exception e) {
			//Log error
			return new ArrayList<StockDto>();
		}
	}
	
	@GetMapping("/{stockId}")
    public StockDto getStock(@PathVariable long stockId) {

		StockDto stock = stockService.stocksSearchById(stockId);

        // throw exception if null

        if (stock == null) {
            throw new RuntimeException("Stock not found");
        }

        return stock;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) throws Exception {
        ResponseEntity<Stock> result;
        try {
            this.stockService.addStock(stock);
            result = new ResponseEntity<>(stock, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(stock, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public ResponseEntity<Stock> updateStock(@RequestBody Stock stock) throws Exception {
        ResponseEntity<Stock> result;
        try {
            this.stockService.updateStock(stock);
            result = new ResponseEntity<>(stock, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(stock, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @DeleteMapping("/delete/{stockId}")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public String deleteStock(@PathVariable long stockId) {

    	StockDto stock = stockService.stocksSearchById(stockId);

        // throw exception if null

        if (stock == null) {
            throw new RuntimeException("Stock not found");
        }

        stockService.deleteStock(stock.getStock());

        return "Deleted Stock : " + stock.getStock().getQuantity() + stock.getStock().getQuantityUnit();
    }
}

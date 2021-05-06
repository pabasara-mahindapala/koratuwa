/**
 * 
 */
package lk.agrohub.market.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lk.agrohub.market.dtos.StockDto;
import lk.agrohub.market.model.ImageModel;
import lk.agrohub.market.model.Product;
import lk.agrohub.market.model.Stock;
import lk.agrohub.market.repository.ImageRepository;
import lk.agrohub.market.service.StockService;
import lk.agrohub.market.util.FileUploadUtil;

@RestController
@RequestMapping("/rest/stock")
public class StockController {

	final StockService stockService;

	@Autowired
	ImageRepository imageRepository;

	/**
	 * @param stockService
	 */
	public StockController(StockService stockService) {
		super();
		this.stockService = stockService;
	}

	@GetMapping("all")
	public List<StockDto> listAllStock(@RequestParam(required = false) Long productId,
			@RequestParam(required = false) Long producerId) {
		try {
			return this.stockService.getAllStocksFiltered(productId, producerId);
		} catch (Exception e) {
			// Log error
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

	@PostMapping("/images/add")
	public String addImage(@RequestParam("image") MultipartFile multipartFile, @RequestParam Long stockId,
			@RequestParam int order) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		String uploadDir = "images/stock-images/" + stockId;

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		ImageModel image = new ImageModel(order, null, stockId, fileName, multipartFile.getContentType(), new Date(),
				uploadDir + "/" + fileName);
		this.imageRepository.save(image);

		return "Added image : " + fileName;
	}
}

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

import lk.agrohub.market.dtos.ProductDto;
import lk.agrohub.market.model.ImageModel;
import lk.agrohub.market.model.Product;
import lk.agrohub.market.repository.ImageRepository;
import lk.agrohub.market.service.ProductService;
import lk.agrohub.market.util.FileUploadUtil;

@RestController
@RequestMapping("/rest/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@Autowired
	ImageRepository imageRepository;

	@GetMapping("/all")
	public List<ProductDto> listAllProduct(@RequestParam(required = false) Long categoryId,
			@RequestParam(required = false) Long subCategoryId, @RequestParam(required = false) Long producerId) {
		try {
			return this.productService.getAllProductsFiltered(categoryId, subCategoryId, producerId);
		} catch (Exception e) {
			// Log error
			return new ArrayList<ProductDto>();
		}
	}

	@GetMapping("/{productId}")
	public ProductDto getProduct(@PathVariable long productId) {

		ProductDto product = productService.productsSearchById(productId);

		// throw exception if null

		if (product == null) {
			throw new RuntimeException("Product not found");
		}

		return product;
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) throws Exception {
		ResponseEntity<Product> result;
		try {
			this.productService.addProduct(product);
			result = new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<>(product, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception {
		ResponseEntity<Product> result;
		try {
			this.productService.updateProduct(product);
			result = new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<>(product, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@DeleteMapping("/delete/{productId}")
	@PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
	public String deleteProduct(@PathVariable long productId) {

		ProductDto product = productService.productsSearchById(productId);

		// throw exception if null

		if (product == null) {
			throw new RuntimeException("Product not found");
		}

		productService.deleteProduct(product.getProduct());

		return "Deleted Product : " + product.getProduct().getProductName();
	}

	@PostMapping("/images/add")
	public String addImage(@RequestParam("image") MultipartFile multipartFile, @RequestParam Long productId,
			@RequestParam int order) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		String uploadDir = "images/product-images/" + productId;

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		ImageModel image = new ImageModel(order, null, productId, fileName, multipartFile.getContentType(), new Date(),
				uploadDir + "/" + fileName);
		this.imageRepository.save(image);

		return "Added image : " + fileName;
	}
}

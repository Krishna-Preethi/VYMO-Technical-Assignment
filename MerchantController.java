import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantController(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @PostMapping
    public ResponseEntity<String> createMerchant(@RequestBody Merchant merchant) {
        try {
            merchant.setStatus("success");
            merchantRepository.save(merchant);
            return new ResponseEntity<>("Merchant created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create merchant: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMerchantStatus(@PathVariable Long id) {
        Merchant merchant = merchantRepository.findById(id).orElse(null);
        if (merchant != null) {
            return ResponseEntity.ok(merchant.getStatus());
        } else {
            return new ResponseEntity<>("Merchant not found", HttpStatus.NOT_FOUND);
        }
    }
}

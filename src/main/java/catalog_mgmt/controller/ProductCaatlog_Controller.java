package catalog_mgmt.controller;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import catalog_mgmt.dto.ResourceCatalogCompClassesCache;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/productCatalogMgmt")
public class ProductCaatlog_Controller 
{

	private static final Logger logger = LoggerFactory.getLogger(ProductCaatlog_Controller.class);

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private Executor asyncExecutor;
		
	@GetMapping(value = "/getAllResourceCatalogCompClassesFromCache/{resCatSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Flux<ResourceCatalogCompClassesCache>> getAllResourceCatalogCompClassesFromCache(@PathVariable Long resCatSeqNo) 
	{
		Flux<ResourceCatalogCompClassesCache> resourceCatalogDTOs=Flux.create(emitter -> 
		{
			CompletableFuture<List<ResourceCatalogCompClassesCache>> future = CompletableFuture.supplyAsync(() -> 
			{
			List<ResourceCatalogCompClassesCache> specList = new CopyOnWriteArrayList<ResourceCatalogCompClassesCache>();
			Flux<ResourceCatalogCompClassesCache> specs = webClient.get().uri("/resourceCatalogCompClassesCacheManagement/getAllResourceCatalogCompClassesFromCache/"+resCatSeqNo).retrieve().bodyToFlux(ResourceCatalogCompClassesCache.class);
			specList = (List<ResourceCatalogCompClassesCache>) specs.collectList().block(Duration.ofSeconds(10L));			
			logger.info("flux ");
			logger.info("specList size :"+specList.size());
			return specList;
			},asyncExecutor);
			future.whenComplete((resCatDtoList2, exception) -> 
			{
				if (exception == null) {
					resCatDtoList2.forEach(emitter::next);
					emitter.complete();
				} else {
					emitter.complete();
				}
			});
		}); 
		
	return new ResponseEntity<>(resourceCatalogDTOs, HttpStatus.OK);	
	}
}
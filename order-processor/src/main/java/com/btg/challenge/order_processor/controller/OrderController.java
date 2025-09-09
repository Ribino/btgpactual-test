package com.btg.challenge.order_processor.controller;

import com.btg.challenge.order_processor.controller.wrapper.ApiResponse;
import com.btg.challenge.order_processor.dto.output.ClientOrderCount;
import com.btg.challenge.order_processor.exception.NotFoundException;
import com.btg.challenge.order_processor.service.order.output.impl.ClientOrderCountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/order")
public class OrderController {

    private final ClientOrderCountService clientOrderCountService;

    public OrderController (ClientOrderCountService clientOrderCountService) {
        this.clientOrderCountService = clientOrderCountService;
    }

    @GetMapping(value = "/client/count", produces = "application/json")
    public ResponseEntity<ApiResponse<List<ClientOrderCount>>> getClientOrdersCount(
            @RequestParam(name = "pagina", defaultValue = "0") Integer page,
            @RequestParam(name = "tamanhoPagina", defaultValue = "10") Integer pageSize
    ) {

        Page<ClientOrderCount> body = clientOrderCountService.getAllPageable(PageRequest.of(page, pageSize));

        return ResponseEntity.ok(ApiResponse.ok(body));
    }

    @GetMapping(value = "/client/count/{clientCode}", produces = "application/json")
    public ResponseEntity<ApiResponse<ClientOrderCount>> getClientOrdersCountByClientCode(
            @PathVariable(name = "clientCode") Long clientCode,
            @RequestParam(name = "pagina", defaultValue = "0") Integer page,
            @RequestParam(name = "tamanhoPagina", defaultValue = "10") Integer pageSize
    ) {
        var body = clientOrderCountService.getByClientCode(clientCode);
        return ResponseEntity.ok(ApiResponse.ok(body));
    }
}
